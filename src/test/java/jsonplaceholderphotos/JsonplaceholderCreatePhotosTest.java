package jsonplaceholderphotos;

import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Photos;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ApplicationEndpoints;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonplaceholderCreatePhotosTest {

    private static Photos photos;
    private static Faker faker;

    @BeforeAll
    public static void beforeAll() {
        photos = new Photos();
        faker = new Faker();
    }

    @BeforeEach
    public void beforeEach() {
        photos.albumId = faker.number().numberBetween(1, 10);
        photos.albumTitle = faker.lorem().sentence();
        photos.albumUrl = faker.internet().url();
        photos.albumThumbnailUrl = faker.internet().url();
    }

    @Test
    public void jsonplaceholderCreatePhotos() {

        JSONObject photosJson = new JSONObject();
        photosJson.put("albumId", photos.albumId);
        photosJson.put("title", photos.albumTitle);
        photosJson.put("url", photos.albumUrl);
        photosJson.put("thumbnailUrl", photos.albumThumbnailUrl);

        Response response = given()
                .contentType("application/json")
                .body(photosJson.toString())
                .when()
                .post(ApplicationEndpoints.PHOTOS_URL)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals(photos.albumId, json.get("albumId"));
        assertEquals(photos.albumTitle, json.get("title"));
        assertEquals(photos.albumUrl, json.get("url"));
        assertEquals(photos.albumThumbnailUrl, json.get("thumbnailUrl"));
    }
}
