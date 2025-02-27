package base;

import io.restassured.response.Response;
import utils.RestClient;
import static io.restassured.RestAssured.given;

public class BaseClient {

    protected Response sendGetRequest(String endpoint, String pathParamName, String pathParamValue) {
        String finalEndpoint = endpoint.replace(pathParamName, pathParamValue);
        return RestClient.sendRequest(
                given()
                        .header("api_key", "special-key"),
                finalEndpoint,
                "GET"
        );
    }

    protected Response sendPostRequest(String endpoint, Object payload) {
        return RestClient.sendRequest(
                given()
                        .header("api_key", "special-key")
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload),
                endpoint,
                "POST"
        );
    }
}