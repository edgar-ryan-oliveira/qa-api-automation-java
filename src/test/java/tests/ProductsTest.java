package tests;
import base.BaseTest;
import endpoints.Endpoints;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsTest extends BaseTest {
    @Test void shouldListProducts() {
        given().when().get(Endpoints.PRODUCTS).then().statusCode(200).body("$", not(empty()));
    }
    @Test void shouldFindProductById() {
        given().pathParam("id",1).when().get(Endpoints.PRODUCT_BY_ID).then()
            .statusCode(200).body("id",equalTo(1)).body("title",not(emptyOrNullString()));
    }
    @Test void shouldCreateProduct() {
        String body = "{
"title":"Mouse Gamer",
"price":199.90,
"description":"Mouse RGB",
"image":"https://example.com/mouse.png",
"category":"electronics"
}";
        given().contentType("application/json").body(body).when().post(Endpoints.PRODUCTS).then()
            .statusCode(anyOf(equalTo(200),equalTo(201))).body("title",equalTo("Mouse Gamer"));
    }
    @Test void shouldDeleteProduct() {
        given().pathParam("id",1).when().delete(Endpoints.PRODUCT_BY_ID).then().statusCode(200).body("id",equalTo(1));
    }
}