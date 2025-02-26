package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    public static Response sendRequest(RequestSpecification request, String endpoint, String method) {
        switch (method.toUpperCase()) {
            case "GET":
                return request.get(endpoint);
            case "POST":
                return request.post(endpoint);
            case "PUT":
                return request.put(endpoint);
            case "DELETE":
                return request.delete(endpoint);
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }
    }
}