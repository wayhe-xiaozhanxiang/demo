package com.convict.demo;

import com.convict.spider.TianYanCha;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {

        Spider spider = Spider
                .create(new TianYanCha())
                .addUrl("https://www.tianyancha.com/search")
                .thread(1);
        spider.start();

    }

}
