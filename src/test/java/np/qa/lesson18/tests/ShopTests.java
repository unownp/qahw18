package np.qa.lesson18.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static np.qa.lesson18.tests.TestHelper.baseUrl;
import static np.qa.lesson18.tests.TestHelper.email;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;

public class ShopTests {
    TestHelper testHelper = new TestHelper();

    @Test
    void checkLoginWithCookies() {
        String cookie = testHelper.getAuthorizationCookies();
        String str = given()
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .when()
                .get(baseUrl+"info")
                .then()
                .log().all().extract().asString();

        assertThat(str.contains(email)).isTrue();
    }

    @Test
    void checkLoginWithoutCookies() {
        String cookie = testHelper.getAuthorizationCookies();
        String str = given()
             //   .cookie("NOPCOMMERCE.AUTH", cookie)
                .when()
                .get(baseUrl+"info")
                .then()
                .log().all().extract().asString();

        assertThat(str.contains(email)).isFalse();
    }

}