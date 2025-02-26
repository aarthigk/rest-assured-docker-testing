
package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import constants.ServiceEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import listeners.CustomTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ReportManager;

    @Listeners(CustomTestListener.class)
    public class PetStoreTests {

        @Test
        public void testGetPetById() {
         //   ExtentTest test = ReportManager.createTest("testGetPet");
            // Use the endpoint from ServiceEndpoints
            String endpoint = ServiceEndpoints.GET_PET_BY_ID.replace("{petId}", "12");

            // Send GET request
            Response response = RestAssured.given()
                    .header("api_key", "special-key")
                    .get(endpoint);
    //        test.log(Status.INFO, "Response Status Code: " + response.getStatusCode());
    //        test.log(Status.INFO, "Response Body: " + response.getBody().asString());

            // Validate response
            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.jsonPath().getInt("id"), 12);
          //  test.log(Status.PASS, "GET request successful");
            Assert.assertTrue(true);
        }

        @Test
        public void testCreatePet() {
       //     ExtentTest test = ReportManager.createTest("testCreatePet");
            try {
                // Create a payload for the new pet
                String payload = "{ \"id\": 12, \"name\": \"doggie\", \"status\": \"available\" }";

                // Create a new pet using createPet
              //  Response response = client.createPet(payload);
                String endpoint = ServiceEndpoints.CREATE_PET;
                Response response = RestAssured.given()
                        .header("api_key", "special-key")
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload)
                        .post(endpoint);
                System.out.println("Using API Endpoint: " + endpoint);

           //     test.log(Status.INFO,response.toString());

                // Log response details
             //   test.log(Status.INFO, "Response Status Code: " + response.getStatusCode());
               // test.log(Status.INFO, "Response Body: " + response.getBody().asString());

                // Validate status code
               Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");


           //     test.log(Status.PASS, "POST request successful");
            } catch (AssertionError e) {
             //   test.log(Status.FAIL, "POST request failed: " + e.getMessage());
                throw e;
            }
        }


    }

