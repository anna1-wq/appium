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

import java.net.URL;

public class overload_methods {

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
    public void FirstTest()
    {
        WebElement element_to_init_search  = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        element_to_init_search.click();

        WebElement element_to_enter_search_line = waitforElementPresentbyXpath("//*[contains(@text,'Search…')]",
                "Cannot find search input",
                7);

        //driver.findElementByXPath("//*[contains(@text,'Search…')]");
        element_to_enter_search_line.sendKeys("Appium");
        System.out.println(" First test run");


    }

    private WebElement waitforElementPresentbyXpath(String xpath,String error_message,long timeoutInSeconds )
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
        wait.withMessage(error_message + "\n" );
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }
}
