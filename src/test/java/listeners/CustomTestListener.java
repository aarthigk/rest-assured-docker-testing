package listeners;


import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ReportManager;

public class CustomTestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ReportManager.getInstance();
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ReportManager.createTest(result.getMethod().getMethodName()).log(Status.PASS, "Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ReportManager.createTest(result.getMethod().getMethodName()).log(Status.FAIL, "Test Failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ReportManager.createTest(result.getMethod().getMethodName()).log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ReportManager.flushReport();
    }
}