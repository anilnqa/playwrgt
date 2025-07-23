package tests.uitests;

import com.microsoft.playwright.Playwright;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class TestNGListener implements ITestListener {

    Playwright playwright;
    Browser browser;
    Page page;

    // Called when a test case starts
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }

    // Called when a test case succeeds
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    // Called when a test case fails
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        captureScreenshot(result.getName());
    }

    // Capture screenshot on failure
    public void captureScreenshot(String testName) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(testName + "_failure.png")));
        System.out.println("Screenshot captured for test case: " + testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("All tests completed!");
    }
}