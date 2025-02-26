package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import services.Service2Client;
import utils.ReportManager;

public class Service2Tests {

    private Service2Client client = new Service2Client();

  //  @Test
    public void testGetResource() {
        ExtentTest test = ReportManager.createTest("testGetResource");


        try {
            Response response = client.getResource("123");
            Assert.assertEquals(response.getStatusCode(), 200);
            test.log(Status.PASS, "GET request successful");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "GET request failed: " + e.getMessage());
            throw e;
        }
    }

  //  @Test
    public void testCreateResource() {
        ExtentTest test = ReportManager.createTest("testCreateResource");
        try {
            Object payload = new Object(); // Replace with actual payload
            Response response = client.createResource(payload);
            Assert.assertEquals(response.getStatusCode(), 201);
            test.log(Status.PASS, "POST request successful");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "POST request failed: " + e.getMessage());
            throw e;
        }
    }
}