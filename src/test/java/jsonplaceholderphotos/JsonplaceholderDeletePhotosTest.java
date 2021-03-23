package jsonplaceholderphotos;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import utils.ApplicationEndpoints;

import static io.restassured.RestAssured.given;

public class JsonplaceholderDeletePhotosTest {

    @Test
    public void jsonplaceholderDeletePhotos() {

        Response response = given()
                .pathParam("photosId", 1)
                .when()
                .delete(ApplicationEndpoints.PHOTOS_URL + "/{photosId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }
}
