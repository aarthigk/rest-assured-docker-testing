package listeners;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ReportManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CustomTestListener implements ITestListener {

    private static final Map<String, ExtentTest> testMap = new HashMap<>();


    @Override
    public void onStart(ITestContext context) {
        ReportManager.getInstance();

    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = ReportManager.createTest(testName);
        testMap.put(testName, test);
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = testMap.get(result.getMethod().getMethodName());
        test.log(Status.PASS, "Test Passed");
        ReportManager.attachLogsToReport(test);

      //  ReportManager.createTest(result.getMethod().getMethodName()).log(Status.PASS, "Test Passed");
       // ReportManager.attachLogsToReport(ReportManager.createTest(result.getMethod().getMethodName()));
    }


    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testMap.get(result.getMethod().getMethodName());
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        ReportManager.attachLogsToReport(test);
        //ReportManager.createTest(result.getMethod().getMethodName()).log(Status.FAIL, "Test Failed: " + result.getThrowable());
        //ReportManager.attachLogsToReport(ReportManager.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = testMap.get(result.getMethod().getMethodName());
        test.log(Status.SKIP, "Test Skipped");
        ReportManager.attachLogsToReport(test);
      //  ReportManager.createTest(result.getMethod().getMethodName()).log(Status.SKIP, "Test Skipped");
        //ReportManager.attachLogsToReport(ReportManager.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.flushReport();
    }
}