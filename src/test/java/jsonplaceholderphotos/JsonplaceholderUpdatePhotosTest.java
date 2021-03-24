package jsonplaceholderphotos;

import base.BaseTest;
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

public class JsonplaceholderUpdatePhotosTest extends BaseTest {


    @BeforeEach
    public void beforeEach() {
        photos.setAlbumId(faker.number().numberBetween(1, 10));
        photos.setAlbumTitle(faker.lorem().sentence());
        photos.setAlbumUrl(faker.lorem().sentence());
        photos.setAlbumThumbnailUrl(faker.internet().url());
    }

    @Test
    public void jsonplaceholderUpdateAllPhotosFiledTest() {

        JSONObject photosJson = new JSONObject();
        photosJson.put("albumId", photos.getAlbumId());
        photosJson.put("title", photos.getAlbumTitle());
        photosJson.put("url", photos.getAlbumUrl());
        photosJson.put("thumbnailUrl", photos.getAlbumThumbnailUrl());

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
        assertEquals(photos.getAlbumId(), json.get("albumId"));
        assertEquals(photos.getAlbumTitle(), json.get("title"));
        assertEquals(photos.getAlbumUrl(), json.get("url"));
        assertEquals(photos.getAlbumThumbnailUrl(), json.get("thumbnailUrl"));
    }

    @Test
    public void jsonplaceholderUpdatePhotosTitleTest() {

        JSONObject photosJson = new JSONObject();
        photosJson.put("title", photos.getAlbumTitle());

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
        assertEquals(photos.getAlbumTitle(), json.get("title"));
        assertEquals("https://via.placeholder.com/600/92c952", json.get("url"));
        assertEquals("https://via.placeholder.com/150/92c952", json.get("thumbnailUrl"));
    }
}
