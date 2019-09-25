import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.net.URL;

public class Ex3 {
    private AppiumDriver driver;

    @Before
    public void SetUp() throws Exception
    {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/admin/Desktop/AppiumAuto/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After
    public void tearDown()
    {

        driver.quit();
    }
    @Test
    public void firstTest()
    {
        waitforElementAndSendKeys(By.id("org.wikipedia:id/search_container"),"Java","cant find",
                10);
        WebElement element_first = waitforElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannt find first element",
                8);
        WebElement element_sec = waitforElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Island of Indonesia']"),
                "Cannt find first element",
                8);
       waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
               "cannt clear search field", 5);

        waitforElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Cannt find 'exit button", 5);

        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page", 5);






    }

    private void checkText()
    {
        waitforElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "cannt find search field",5);
        WebElement element = waitforElementPresent(By.id("org.wikipedia:id/search_src_text"),
                "cannt find search field",5);
        String text_in_search= element.getAttribute("text");
        System.out.println(text_in_search);

    }

    private WebElement waitforElementPresent(By by, String error_message, long timeoutInSeconds )
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
        wait.withMessage(error_message + "\n" );
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }
    private WebElement waitforElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitforElementPresent(by,error_message,timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitforElementAndSendKeys(By by, String value , String error_message, long timeoutInSeconds)
    {
        WebElement element = waitforElementPresent(by,error_message,timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    private boolean waitForElementNotPresent(By by, String error_message,long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
        wait.withMessage(error_message + "\n" );
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message,long timeoutInSeconds)
    {
        WebElement element = waitforElementPresent(by,error_message,timeoutInSeconds);
        element.clear();
        return element;

    }
}
