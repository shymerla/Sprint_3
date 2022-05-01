import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends ScooterRestClient {

    private static final String COURIER_PATH = "api/v1/courier";

    @Step("Login with credentials {credentials}")
    public int login(CourierCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(COURIER_PATH + "login")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }

    @Step("Crating of courier {courier}")
    public boolean create(Courier courier) {
        return given()
            .spec(getBaseSpec())
            .body(courier)
            .when()
            .post(COURIER_PATH)
            .then().log().all()
            .assertThat()
            .statusCode(201)
            .extract()
            .path("ok");
    }

    @Step("Deliting of courier {courierId}")
    public boolean delete(int courierId) {
        return given().log().all()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + ":id")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }
}
