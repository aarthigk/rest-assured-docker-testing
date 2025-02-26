package services;

import config.ConfigManager;
import io.restassured.response.Response;
import utils.RestClient;
import static io.restassured.RestAssured.given;

public class Service2Client {

    private static final String BASE_URL = ConfigManager.getProperty("microservice1.base.url");

    public Response getResource(String resourceId) {
        return RestClient.sendRequest(given(), BASE_URL + "/resources/" + resourceId, "GET");
    }

    public Response createResource(Object payload) {
        return RestClient.sendRequest(given().body(payload), BASE_URL + "/resources", "POST");
    }
}