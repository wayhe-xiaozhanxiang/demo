package com.convict.spider;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * @author xzx
 * @date 2019/6/24
 */

@Component
public class TianYanCha implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(3000)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
    private static Spider spider;

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        String curUrl = page.getUrl().get();
        Html html = page.getHtml();
        if (curUrl.startsWith("https://www.tianyancha.com/search")) {
            List<String> as = html.xpath("a[@class='name select-none']").links().all();
            page.addTargetRequests(as);
        } else {
            String name = html.xpath("//h1[@class='name']/text()").get();
            // 工商注册号
            String busiRegNo = null;

            Elements tmpTd = html.getDocument().select("table[class='table -striped-col -border-top-none -breakall']")
                    .select("tr")
                    .get(1)
                    .select("td");
            String orgNo = tmpTd.get(tmpTd.size() - 1).text();
            System.out.println(name);
            System.out.println(orgNo);
        }


    }

    public static void main(String[] args) {
        spider = Spider
                .create(new TianYanCha())
                .addUrl("https://www.tianyancha.com/search")
                .thread(1);
        spider.start();
    }
}
