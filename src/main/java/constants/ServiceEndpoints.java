package constants;

public class ServiceEndpoints {

    // Base URL for the Petstore API
    public static final String PETSTORE_BASE_URL = "https://petstore.swagger.io/v2";

    // Endpoints for the Petstore API
    public static final String GET_PET_BY_ID = PETSTORE_BASE_URL + "/pet/{petId}";
    public static final String CREATE_PET = PETSTORE_BASE_URL + "/pet";
    public static final String UPDATE_PET = PETSTORE_BASE_URL + "/pet";
    public static final String DELETE_PET = PETSTORE_BASE_URL + "/pet/{petId}";

    public static final String GET_USER = PETSTORE_BASE_URL +"/user/{userName}";
    public static final String CREATE_USER = PETSTORE_BASE_URL +"/user";
    public static final String CREATE_USER_LIST = PETSTORE_BASE_URL +"/user/createWithList";

    // Base URL for another microservice
    public static final String ANOTHER_SERVICE_BASE_URL = "http://api.example.com/v1";

    // Endpoints for another microservice
    public static final String GET_RESOURCE = ANOTHER_SERVICE_BASE_URL + "/resource/{id}";
    public static final String CREATE_RESOURCE = ANOTHER_SERVICE_BASE_URL + "/resource";


}