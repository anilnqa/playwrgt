package tests.apitests;

import api.services.OrderApi;
import com.microsoft.playwright.Playwright;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class APITest {

    private Logger logger;
    private Playwright playwright;

    private OrderApi orderApi=new OrderApi();

    @BeforeSuite
    public void startDataPreparation() throws IOException {
        FileUtils.deleteDirectory(new File("allure-results"));
    }


    @AfterTest
    public void tearDown() {
        if (playwright != null) {
            playwright.close();
            logger.info("✅ Playwright instance closed.");
        }
    }

    @AfterMethod
    public void closeTestCase(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            //createAttachment();
        }
        //context.close();
    }

    @AfterSuite
    public void addAllureInformation() {
        //playwright.close();
        Properties allure = new Properties();
        allure.setProperty("Platform", "API");
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

    @Test(description= "Successfully retrieve an order by ID using GET request")
    public void ApiTest_get_1() {
        orderApi.the_store_API_is_available();
        //Given an existing order with ID 1
        orderApi.an_existing_order_with_ID( 1);
//        When a GET request is sent to retrieve the order
        orderApi.a_GET_request_is_sent_to_retrieve_the_order();
//        Then the response status code should be 200
        orderApi.the_response_status_code_should_be(200);
//        And the order details should be displayed
        orderApi.the_order_details_should_be_displayed();
    }
}


