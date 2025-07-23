package tests.apitests;

import api.ApiReqHandler;
import api.services.OrderApi;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseApiTest {

    private Logger logger;
    private Playwright playwright;
    private APIRequestContext apiRequestContext;
    private APIResponse response;
    private APIRequest request;
    private ApiReqHandler apiReqHandler;
    private APIRequestContext apiRequestContext1;
    private APIRequestContext apiRequestContext11;

    private AllureTestNg allureTestNg;
    private Allure allure;



    @BeforeSuite
    public void beforeSuite() throws IOException {
        FileUtils.deleteDirectory(new File("allure-results"));
    }

//    @BeforeTest
//    public void beforeTest(ITestResult testResult) throws IOException {
//        logger.info("Test class" + testResult.getTestClass().toString());
//        logger.info("Test Name" + testResult.getTestName());
//        logger.info("Test Method" + testResult.getMethod().toString());
//    }

//    @BeforeMethod
//    public void beforeMethod(ITestResult testResult) throws IOException {
//
//        logger.info("Test class" + testResult.getTestClass().toString());
//        logger.info("Test Name" + testResult.getTestName());
//        logger.info("Test Method" + testResult.getMethod().toString());
//    }

//    @AfterTest
//    public void tearDown(ITestResult testResult) {
//        if (playwright != null) {
//            playwright.close();
//            //logger.info("✅ Playwright instance closed.");
//        }
//    }

//    @AfterMethod
//    public void closeTestCase(ITestResult testResult) {
//        if (testResult.getStatus() == ITestResult.FAILURE) {
//            //logger.error("" + testResult.isSuccess());
//        }
//        if (testResult.getStatus() == ITestResult.SUCCESS) {
//            //logger.info("" + testResult.isSuccess());
//
//        }
//    }

    @AfterSuite
    public void afterSuite() {
        //playwright.close();
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


