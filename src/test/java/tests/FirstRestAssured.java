package tests;

import com.envision.core.DataProviderArgs;
import com.envision.core.DataProviderUtils;
import com.envision.core.RestAssuredActions;
import com.envision.utils.ApiUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsString;

public class FirstRestAssured {

    @DataProviderArgs(value = "getUploadImageData=contentType,baseUri,endPoint,payload,statusCode,method,file")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testGetUploadImageDataPostAPI(String contentType, String baseUri, String endPoint, String payload, String statusCode, String method, String file) throws IOException {
        String jsonBody = ApiUtils.getStringBody(System.getProperty("user.dir")
                + payload);
        jsonBody = jsonBody.replaceAll("%file%", file);
        Response response = RestAssuredActions.doPostRequest(contentType, baseUri, endPoint, method, jsonBody);
        response.then().and().assertThat().statusCode(Integer.parseInt(statusCode));
    }

    @DataProviderArgs(value = "deletePetById=contentType,baseUri,endPoint,payload,statusCode,method,id")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testDeletePetDetailsByIdAPI(String contentType,String baseUri, String endPoint, String payload, String statusCode, String method, String id) throws IOException {
        String jsonBody = ApiUtils.getStringBody(System.getProperty("user.dir")
                + payload);
        jsonBody = jsonBody.replaceAll("%id%", id);
        Response response = RestAssuredActions.doDeleteRequest(contentType,baseUri, endPoint, method,jsonBody);
        response.then().and().assertThat().statusCode(Integer.parseInt(statusCode));
    }

    @DataProviderArgs(value = "createUserDataWithArray=contentType,baseUri,endPoint,payload,statusCode,method,id,username,firstName,lastName,email,password,phone,userStatus")
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = "jsonDataProvider")
    public void testCreateUserWithArrayPostAPI(String contentType, String baseUri, String endPoint, String payload, String statusCode, String method, String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) throws IOException {
        String jsonBody = ApiUtils.getStringBody(System.getProperty("user.dir")
                + payload);
        jsonBody = jsonBody.replaceAll("%id%", id);
        jsonBody = jsonBody.replaceAll("%username%", username);
        jsonBody = jsonBody.replaceAll("%firstName%", firstName);
        jsonBody = jsonBody.replaceAll("%lastName%", lastName);
        jsonBody = jsonBody.replaceAll("%email%", email);
        jsonBody = jsonBody.replaceAll("%password%", password);
        jsonBody = jsonBody.replaceAll("%phone%", phone);
        jsonBody = jsonBody.replaceAll("%userStatus%", userStatus);
        Response response = RestAssuredActions.doPostRequest(contentType, baseUri, endPoint, method, jsonBody);
        response.then().and().assertThat().statusCode(Integer.parseInt(statusCode))
                .and().assertThat().body(containsString("message"));

    }
}
