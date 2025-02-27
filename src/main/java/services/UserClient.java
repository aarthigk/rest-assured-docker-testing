package services;

import base.BaseClient;
import constants.ServiceEndpoints;
import io.restassured.response.Response;

public class UserClient extends BaseClient {


    public Response getUserById(String userName) {
        return sendGetRequest(ServiceEndpoints.GET_USER, "{userName}", userName);
    }

    public Response createUser(Object payload) {
        return sendPostRequest(ServiceEndpoints.CREATE_USER, payload);
    }

    public Response createUserList(Object payload) {
        return sendPostRequest(ServiceEndpoints.CREATE_USER_LIST, payload);
    }



}
