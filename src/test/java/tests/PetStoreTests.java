package tests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import constants.ServiceEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import listeners.CustomTestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
    @Listeners(CustomTestListener.class)
    public class PetStoreTests {
        private static final Logger logger = LogManager.getLogger(PetStoreClientTests.class);

        @Test
        public void testGetPetById() {
            logger.info("Starting test: testCreatePetClientRandom");
            String endpoint = ServiceEndpoints.GET_PET_BY_ID.replace("{petId}", "12");

            // Send GET request
            Response response = RestAssured.given()
                    .header("api_key", "special-key")
                    .get(endpoint);
            // Validate response
            Assert.assertEquals(response.getStatusCode(), 200);
            logger.info("Response received with status code: " + response.asPrettyString());

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
                logger.info("Response received with status code: " + response.asPrettyString());

            } catch (AssertionError e) {

                throw e;
            }
        }


    }

