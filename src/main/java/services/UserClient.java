package services;

import constants.ServiceEndpoints;
import io.restassured.response.Response;
import utils.RestClient;

import static io.restassured.RestAssured.given;

public class UserClient {
    public Response getUserById(String userName) {
        String endpoint = ServiceEndpoints.GET_USER.replace("{userName}", userName);
        return RestClient.sendRequest(
                given()
                        .header("api_key", "special-key"),
                endpoint,
                "GET"
        );
    }

    public Response createUser(Object payload) {
        return RestClient.sendRequest(
                given()
                        .header("api_key", "special-key")
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload),
                ServiceEndpoints.CREATE_USER,
                "POST"
        );
    }

    public Response createUserList(Object payload) {
        return RestClient.sendRequest(
                given()
                        .header("api_key", "special-key")
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload),
                ServiceEndpoints.CREATE_USER_LIST,
                "POST"
        );
    }



}
