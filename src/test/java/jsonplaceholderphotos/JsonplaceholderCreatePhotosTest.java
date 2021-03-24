package jsonplaceholderphotos;

import base.BaseTest;
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

public class JsonplaceholderCreatePhotosTest extends BaseTest {


    @BeforeEach
    public void beforeEach() {
        photos.setAlbumId(faker.number().numberBetween(1, 10));
        photos.setAlbumTitle(faker.lorem().sentence());
        photos.setAlbumUrl(faker.lorem().sentence());
        photos.setAlbumThumbnailUrl(faker.internet().url());
    }

    @Test
    public void jsonplaceholderCreatePhotos() {

        JSONObject photosJson = new JSONObject();
        photosJson.put("albumId", photos.getAlbumId());
        photosJson.put("title", photos.getAlbumTitle());
        photosJson.put("url", photos.getAlbumUrl());
        photosJson.put("thumbnailUrl", photos.getAlbumThumbnailUrl());

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
        assertEquals(photos.getAlbumId(), json.get("albumId"));
        assertEquals(photos.getAlbumTitle(), json.get("title"));
        assertEquals(photos.getAlbumUrl(), json.get("url"));
        assertEquals(photos.getAlbumThumbnailUrl(), json.get("thumbnailUrl"));
    }

}
