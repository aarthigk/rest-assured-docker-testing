package services;

import base.BaseClient;
import constants.ServiceEndpoints;
import io.restassured.response.Response;

public class PetStoreClient  extends BaseClient {
    public Response getPetById(String petId) {
        return sendGetRequest(ServiceEndpoints.GET_PET_BY_ID, "{petId}", petId);
    }

    public Response createPet(Object payload) {
        return sendPostRequest(ServiceEndpoints.CREATE_PET, payload);
    }


}
