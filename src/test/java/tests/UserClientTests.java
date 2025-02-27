package tests;

import constants.ServiceEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.PetStoreClient;
import services.UserClient;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class UserClientTests {
    private UserClient userClient;

    @BeforeClass
    public void setup() {
        userClient = new UserClient();  // Initialize API Client
    }


    @Test
    public void testCreateSingleUserClient() {
        String requestBody = "{\n" +
                "  \"id\": 10,\n" +
                "  \"username\": \"usser45r\",\n" +
                "  \"firstName\": \"usserr\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        log.println(requestBody);
        Response response = userClient.createUser(requestBody);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200, "Expected 200 OK response");
        log.println(response.prettyPrint());
    }
    @Test
    public void testGetUserByName() {
        String userName = "usser45r";  // Example pet ID
        Response response = userClient.getUserById(userName);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200, "Expected 200 OK response");
    }

    @Test
    public void testUserListClient() {
        String requestBody =
                "[\n" +
                        "  {\n" +
                        "    \"id\": 0,\n" +
                        "    \"username\": \"string\",\n" +
                        "    \"firstName\": \"string\",\n" +
                        "    \"lastName\": \"string\",\n" +
                        "    \"email\": \"string\",\n" +
                        "    \"password\": \"string\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  },\n" +
                        "\n" +
                        "{\n" +
                        "    \"id\": 1,\n" +
                        "    \"username\": \"AAAT\",\n" +
                        "    \"firstName\": \"MINE\",\n" +
                        "    \"lastName\": \"CRAFT\",\n" +
                        "    \"email\": \"string@mail.com\",\n" +
                        "    \"password\": \"string\",\n" +
                        "    \"phone\": \"string\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "\n" +
                        "]";
        log.println("Post Request sent"+requestBody);

        Response response = userClient.createUserList(requestBody);
        log.println("Post Response returned"+response.prettyPrint());
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200, "Expected 200 OK response");

    }





}
