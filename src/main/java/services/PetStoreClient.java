package services;

import constants.ServiceEndpoints;
import io.restassured.response.Response;
import utils.RestClient;
import static io.restassured.RestAssured.given;

public class PetStoreClient {

    public Response getPetById(String petId) {
        String endpoint = ServiceEndpoints.GET_PET_BY_ID.replace("{petId}", petId);
        return RestClient.sendRequest(
                given()
                        .header("api_key", "special-key"),
                endpoint,
                "GET"
        );
    }

    public Response createPet(Object payload) {
        return RestClient.sendRequest(
                given()
                        .header("api_key", "special-key")
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload),
                ServiceEndpoints.CREATE_PET,
                "POST"
        );
    }
}
