package jsonplaceholderusers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import utils.ApplicationEndpoints;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class JsonplaceholderReadUsersTest {

    @Test
    public void jsonplaceholderReadAllUsers() {

        Response response = given()
                .when()
                .get(ApplicationEndpoints.USERS_URL)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        List<String> emails = json.getList("email");

        List<String> emailsWithPL = emails.stream()
                .filter(email -> email.endsWith(".pl"))
                .collect(Collectors.toList());

        assertThat(emailsWithPL, is(empty()));
    }
}
