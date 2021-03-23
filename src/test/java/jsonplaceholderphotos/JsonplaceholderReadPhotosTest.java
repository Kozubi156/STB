package jsonplaceholderphotos;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.ApplicationEndpoints;

import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonplaceholderReadPhotosTest {

    @Test
    public void jsonplaceholderReadAllPhotos() {

        Response response = given()
                .when()
                .get(ApplicationEndpoints.PHOTOS_URL)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> ids = json.getList("id");
        Assertions.assertEquals(5000, ids.size());
    }

    @Test
    public void jsonplaceholderReadOnePhotos() {

        Response response = given()
                .pathParam("photosId", 1)
                .when()
                .get(ApplicationEndpoints.PHOTOS_URL + "/{photosId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        Integer id = json.get("id");
        Assertions.assertEquals(1, id);
        Assertions.assertEquals("accusamus beatae ad facilis cum similique qui sunt", json.get("title"));
        Assertions.assertEquals("https://via.placeholder.com/600/92c952", json.get("url"));
        Assertions.assertEquals("https://via.placeholder.com/150/92c952", json.get("thumbnailUrl"));
    }
}
