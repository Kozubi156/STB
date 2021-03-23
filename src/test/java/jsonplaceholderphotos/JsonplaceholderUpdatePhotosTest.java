package jsonplaceholderphotos;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Photos;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ApplicationEndpoints;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderUpdatePhotosTest {

    private static Photos photos;
    private static Faker faker;

    @BeforeAll
    public static void beforeAll() {
        photos = new Photos();
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach() {
        photos.albumId = faker.number().numberBetween(1,10);
        photos.albumTitle = faker.lorem().sentence();
        photos.albumUrl = faker.internet().url();
        photos.albumThumbnailUrl = faker.internet().url();
    }

    @Test
    public void jsonplaceholderUpdateAllPhotosFiledTest() {

        JSONObject photosJson = new JSONObject();
        photosJson.put("albumId", photos.albumId);
        photosJson.put("title", photos.albumTitle);
        photosJson.put("url", photos.albumUrl);
        photosJson.put("thumbnailUrl", photos.albumThumbnailUrl);

        Response response = given()
                .contentType("application/json")
                .pathParam("photosId", 1)
                .body(photosJson.toString())
                .when()
                .put(ApplicationEndpoints.PHOTOS_URL + "/{photosId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        Integer id = json.get("id");
        Assertions.assertEquals(1,id);
        assertEquals(photos.albumId, json.get("albumId"));
        assertEquals(photos.albumTitle, json.get("title"));
        assertEquals(photos.albumUrl, json.get("url"));
        assertEquals(photos.albumThumbnailUrl, json.get("thumbnailUrl"));
    }

    @Test
    public void jsonplaceholderUpdatePhotosTitleTest() {

        JSONObject photosJson = new JSONObject();
        photosJson.put("title", photos.albumTitle);

        Response response = given()
                .contentType("application/json")
                .pathParam("photosId", 1)
                .body(photosJson.toString())
                .when()
                .patch(ApplicationEndpoints.PHOTOS_URL + "/{photosId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        Integer id = json.get("id");
        Assertions.assertEquals(1,id);
        assertEquals(photos.albumTitle, json.get("title"));
        assertEquals("https://via.placeholder.com/600/92c952", json.get("url"));
        assertEquals("https://via.placeholder.com/150/92c952", json.get("thumbnailUrl"));
    }
}
