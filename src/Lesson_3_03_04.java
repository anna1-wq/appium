import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.*;


import java.net.URL;
public class Lesson_3_03_04 {
    private AppiumDriver driver;

    @Before
    public void SetUp() throws Exception
    {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","10");
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
    public void testSwipeArticle()
    {
        waitforElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannt find 'Search Wikipedia'", 5);

        waitforElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Appium",
                "Cannot find search input",
                10);

        waitforElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannt find 'Appium  article'", 5);

        waitforElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannt find title of article",15);

//        WebElement title_element = waitforElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
//                "Cannt find title of article",15);

//        String article_title =title_element.getAttribute("text");
//
//        Assert.assertEquals("We see unexpectedtitle", "Java (programming language)",
//                article_title);

        SwipeUpTofindElemnt(By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20);

    }

    @Test
    public void saveFirstArticleToMyList(){

        waitforElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannt find 'Search Wikipedia'", 5);

        waitforElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                10);

        waitforElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannt find 'Java article'", 10);

        waitforElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                "Cannt find title of article",15);

        waitforElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannt find button to open article options",5);



//        waitforElementAndClick(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView"),
//                "Cannt find option to add article to reading list",
//                5);

        waitforElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannt find option to add article to reading list",
                5);

        waitforElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button GOT IT tip overlay",5);


        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name  of articles folder ",
                5);

        String name_folder="Learning Programming";

        waitforElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_folder,
                "Cannot put text in articles folder input",
                5);



        waitforElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot press OK button",5);

        waitforElementAndClick(By.xpath("*//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannt find X link, cannot close article ",5);

        waitforElementAndClick(By.xpath("*//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannt find navigation button to my list ",5);


        waitforElementAndClick(By.id("org.wikipedia:id/item_title"),
                "Cannt find created folder ",10);


        SwipeElementToleft(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article");

        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",5);



    }




    private WebElement waitforElementAndClick(By by, java.lang.String error_message, long timeoutInSeconds)
    {
        WebElement element = waitforElementPresent(by,error_message,timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitforElementAndSendKeys(By by, java.lang.String value , java.lang.String error_message, long timeoutInSeconds)
    {
        WebElement element = waitforElementPresent(by,error_message,timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitforElementPresent(By by, java.lang.String error_message, long timeoutInSeconds )
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
        wait.withMessage(error_message + "\n" );
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    private WebElement waitforElementPresent(By by, java.lang.String error_message)
    {
        return waitforElementPresent(by , error_message,5);
    }

    private WebElement waitForElementAndClear(By by, java.lang.String error_message, long timeoutInSeconds)
    {
        WebElement element = waitforElementPresent(by,error_message,timeoutInSeconds);
        element.clear();
        return element;

    }


//        public WebElement waitforElementAndSendKeys(By locator, String value, String error_message, long timeoutInSeconds){
//
//            MobileElement element = (MobileElement)waitforElementPresent(locator, error_message,timeoutInSeconds);
//            element.setValue(value);
//            return element;
//        }

    protected void SwipeUp(int timeOfSwipe){

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int)(size.height*0.8);
        int end_y = (int)(size.height*0.2);
        action
                .press(x,start_y)
                .waitAction().moveTo(x,end_y)
                .release()
                .perform();
    }

    protected void SwipeUpQuick(){

        SwipeUp(200);

    }

    protected void SwipeUpTofindElemnt(By by, java.lang.String error_message, int max_swipe){

        int already_swiped = 0;
        while (driver.findElements(by).size()==0){

            if (already_swiped> max_swipe){

                waitforElementPresent(by,"Cannot find element by swiping up.\n"+ error_message,0);
                return;
            }
            SwipeUpQuick();
            ++already_swiped;
        }
    }



    protected void SwipeElementToleft(By by, java.lang.String error_message){

        WebElement element = waitforElementPresent(by,error_message,10);
        int left_x = element.getLocation().getX();
        int right_x = left_x+ element.getSize().getWidth();
        int upper_y= element.getLocation().getY();
        int lower_y = upper_y+element.getSize().getHeight();
        int middle_y=(upper_y+lower_y)/2;
        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
        wait.withMessage(error_message + "\n" );
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
