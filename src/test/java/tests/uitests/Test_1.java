package tests.uitests;

import browserinstance.BrowserInstance;
import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Test_1 {

//    private Page page;
//    private BrowserInstance browser = new BrowserInstance();


//    @BeforeMethod
//    public void beforeTestMethod(){
//        page = browser.launchBrowserInstance("chrome");
//    }
//    @AfterMethod
//    public void afterTestMethod(){ browser.playwright.close(); }

    @AfterSuite
    public void afterSuite(){
//        browser.playwright.close();
        Properties allure = new Properties();
        allure.setProperty("Platform", "Web");
        allure.setProperty("Library", "Playwright");
        allure.setProperty("Code by", "Anil");
        try {
            File file = new File("./allure-results/environment.properties");
            FileOutputStream fileOut = new FileOutputStream(file);
            allure.store(fileOut, "Allure Report Environment");
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Step("Test1-To Navigate Duckduckgo")
    public void test1(){
        BrowserInstance browser = new BrowserInstance();
        Page page = browser.launchBrowserInstance("chrome");
        page.navigate("http://duckduckgo.com",new Page.NavigateOptions().setTimeout(90000) );
        browser.playwright.close();
    }

    @Test
    @Step("Test1-To Navigate Google")
    public void test2(){
        BrowserInstance browser = new BrowserInstance();
        Page page = browser.launchBrowserInstance("chrome");
        page.navigate("http://www.google.com",new Page.NavigateOptions().setTimeout(90000) );
        browser.playwright.close();
    }

    @Test
    @Step("Test1-To Navigate wikipedia and search")
    public void test3(){
        BrowserInstance browser = new BrowserInstance();
        Page page = browser.launchBrowserInstance("chrome");
        page.navigate("https://www.wikipedia.org",new Page.NavigateOptions().setTimeout(90000) );
        assertThat(page.locator("xpath=//div[@lang='en']/a")).isVisible();
        page.locator("xpath=//div[@lang='en']/a").click();
        assertThat(page.locator("xpath=//form[@id='searchform']//input[@name='search']")).isVisible();
        createAttachmentByte(page.screenshot());
        page.locator("xpath=//form[@id='searchform']//input[@name='search']").fill("ELON MUSK");
        page.locator("//form[@id='searchform']//button[text()='Search']").click();
//      Frame frame1 = page.frame("frame-login");
//      Frame frame2 = page.frameByUrl(Pattern.compile(".*domain.*"));
        browser.playwright.close();
    }

    @Attachment(value = "Screenshot")
    public byte[] createAttachmentByte(byte[] bytes) {
        return bytes;
    }
}
