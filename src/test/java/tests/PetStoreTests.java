package tests;
import constants.ServiceEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import listeners.CustomTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
    @Listeners(CustomTestListener.class)
    public class PetStoreTests {

        @Test
        public void testGetPetById() {
            String endpoint = ServiceEndpoints.GET_PET_BY_ID.replace("{petId}", "12");

            // Send GET request
            Response response = RestAssured.given()
                    .header("api_key", "special-key")
                    .get(endpoint);
            // Validate response
            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.jsonPath().getInt("id"), 12);
            Assert.assertTrue(true);
        }

        @Test
        public void testCreatePet() {
            try {
                String payload = "{ \"id\": 12, \"name\": \"doggie\", \"status\": \"available\" }";

                String endpoint = ServiceEndpoints.CREATE_PET;
                Response response = RestAssured.given()
                        .header("api_key", "special-key")
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload)
                        .post(endpoint);
                System.out.println("Using API Endpoint: " + endpoint);
               Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");

            } catch (AssertionError e) {

                throw e;
            }
        }


    }

