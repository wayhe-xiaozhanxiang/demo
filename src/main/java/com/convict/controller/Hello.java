package com.convict.controller;

import com.convict.spider.TianYanCha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

/**
 * @author xzx
 * @date 2019/6/24
 */
@Controller
public class Hello {

    @Autowired
    private TianYanCha tianYanCha;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello () {

        Spider spider = Spider
                .create(tianYanCha)
                .addUrl("https://www.tianyancha.com/search")
                .thread(1);
        spider.start();
        return "asdasd";
    }

}
