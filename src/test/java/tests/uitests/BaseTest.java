package tests.uitests;

import com.microsoft.playwright.*;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class BaseTest {
    static Properties properties;
    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;
    static Yaml yaml;
    static InputStream inputStream;
    static Map<String, Object> yamlData;

    public void loadYaml() {
        yaml = new Yaml();
        try {
            inputStream = new FileInputStream("src/test/resources/config.yaml");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        yamlData = yaml.load(inputStream);
    }

    @Attachment(value = "Screenshot")
    public byte[] createAttachment() {
        return page.screenshot();
    }


    @Attachment(value = "Screenshot")
    public void createAttachmentPage(Page pageObj) {
         pageObj.screenshot();
    }

    @Attachment(value = "Screenshot")
    public byte[] createAttachmentByte(byte[] bytes) {
        return bytes;
    }

    @BeforeSuite
    public void startDataPreparation() throws IOException {
        FileUtils.deleteDirectory(new File("allure-results"));
        loadYaml();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)) ;
    }

    @BeforeMethod
    public void startTestCase() throws IOException {
        context = browser.newContext();
        page = context.newPage();
        page.navigate(yamlData.get("base_site").toString());
    }

    @AfterMethod
    public void closeTestCase(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            createAttachment();
        }
        context.close();
    }

    @AfterSuite
    public void addAllureInformation() {
        playwright.close();
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
}