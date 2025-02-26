package tests;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import listeners.CustomTestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ReportManager;
import services.PetStoreClient;

import static utils.TestDataGenerator.generatePetPayload;

@Listeners(CustomTestListener.class)  // Attach Extent Reports Listener
public class PetStoreClientTests {

    private PetStoreClient petStoreClient;

    @BeforeClass
    public void setup() {
        petStoreClient = new PetStoreClient();  // Initialize API Client
    }

    @Test
    public void testGetPetByIdClient() {
        String petId = "12";  // Example pet ID
   //     ExtentTest test = ReportManager.createTest("Retrieve 12 Id dog");
        //  ReportManager.createTest("testGetPetById").info("Sending request to fetch pet with ID: " + petId);

        Response response = petStoreClient.getPetById(petId);
        int statusCode = response.getStatusCode();

        //   ReportManager.getTest("testGetPetById").info("Response Status Code: " + statusCode);
        // ReportManager.getTest("testGetPetById").info("Response Body: " + response.getBody().asString());
//        test.log(Status.INFO, "Response Status Code: " + response.getStatusCode());
//        test.log(Status.INFO, "Response Body: " + response.getBody().asString());

        Assert.assertEquals(statusCode, 200, "Expected 200 OK response");
    }

    @Test
    public void testCreatePetClient() {
        String requestBody = "{ \"id\": 123, \"name\": \"Doggie\", \"status\": \"available\" }";
        // ReportManager.createTest("testCreatePet").info("Sending request to create a new pet: " + requestBody);
    //    ExtentTest test = ReportManager.createTest("Create 123Dog");
        Response response = petStoreClient.createPet(requestBody);
        int statusCode = response.getStatusCode();

        //    ReportManager.getTest("testCreatePet").info("Response Status Code: " + statusCode);
        //  ReportManager.getTest("testCreatePet").info("Response Body: " + response.getBody().asString());

//        test.log(Status.INFO, "Response Status Code: " + response.getStatusCode());
//        test.log(Status.INFO, "Response Body: " + response.getBody().asString());

        Assert.assertEquals(statusCode, 200, "Expected 200 OK response");
    }

    @Test
    public void testCreatePetClientRandom() {
        String requestBody = generatePetPayload();
        // ReportManager.createTest("testCreatePet").info("Sending request to create a new pet: " + requestBody);
     //   ExtentTest test = ReportManager.createTest("Create with randomDog");
        Response response = petStoreClient.createPet(requestBody);
        int statusCode = response.getStatusCode();

        //    ReportManager.getTest("testCreatePet").info("Response Status Code: " + statusCode);
        //  ReportManager.getTest("testCreatePet").info("Response Body: " + response.getBody().asString());

    //    test.log(Status.INFO, "Response Status Code: " + response.getStatusCode());
      //  test.log(Status.INFO, "Response Body: " + response.getBody().asString());

        Assert.assertEquals(statusCode, 200, "Expected 200 OK response");
    }


}
