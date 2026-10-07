package tests;
import base.BaseTest;
import endpoints.Endpoints;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class UsersTest extends BaseTest {
    @Test void shouldListUsers() {
        given().when().get(Endpoints.USERS).then().statusCode(200).body("$",not(empty()));
    }
    @Test void shouldFindUserById() {
        given().pathParam("id",1).when().get(Endpoints.USER_BY_ID).then()
            .statusCode(200).body("id",equalTo(1)).body("email",containsString("@"));
    }
}