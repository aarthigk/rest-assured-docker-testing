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
    public void testCreatePetClient() {
        String requestBody = "{ \"id\": 123, \"name\": \"Doggie\", \"status\": \"available\" }";
        Response response = petStoreClient.createPet(requestBody);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200, "Expected 200 OK response");
    }


    @Test
    public void testGetPetByIdClient() {
        String petId = "123";  // Example pet ID
        Response response = petStoreClient.getPetById(petId);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200, "Expected 200 OK response");
    }


    @Test
    public void testCreatePetClientRandom() {
        String requestBody = generatePetPayload();

        Response response = petStoreClient.createPet(requestBody);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected 200 OK response");
    }


}
