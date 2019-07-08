package com.convict;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author xzx
 * @date 2019/6/24
 */
public class Chrome {

    private static Integer currentPage = 1;
    private static Integer pageCount = 250;

    private static final String account = "13119512709";
    private static final String password = "hkl123456hkl";

    public static WebDriver GetHeadlessDriver (){
        System.getProperties().setProperty("webdriver.Chrome.driver","D:\\IdeaWorkspace\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        //设置headless模式
        options.addArguments("--headless");
        options.addArguments("--incognito");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        cap.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
        cap.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
        System.setProperty("http.nonProxyHosts", "localhost");
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        // 可传cap参数，使之成为无头模式
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static void crawl(WebDriver driver) throws InterruptedException {
        StringBuffer handler = new StringBuffer("https://www.tianyancha.com/search/p");
        driver.manage().window().maximize();
        driver.get("https://www.tianyancha.com/");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@onclick='header.loginLink(event)']")).click();
        driver.findElement(By.xpath("//*[@class='title-tab text-center']/div[@class='title']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class='modulein modulein1 mobile_box  f-base collapse in']" +
                        "/div[@class='pb30 position-rel']" +
                        "/input")).sendKeys(account);
        driver.findElement(By.xpath("//*[@class='modulein modulein1 mobile_box  f-base collapse in']" +
                "/div[@class='input-warp -block']" +
                "/input")).sendKeys(password);
        driver.findElement(By.xpath("//*[@class='modulein modulein1 mobile_box  f-base collapse in']" +
                "/div[@class='btn -hg btn-primary -block']")).click();



    }

    public static void main(String[] args) throws InterruptedException {
        crawl(GetHeadlessDriver());
    }
}
