import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends ScooterRestClient {

    private static final String COURIER_PATH = "api/v1/courier/";

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

    @Step("Crating of doubleCourier {courier}")
    public String doubleCreate(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all()
                .assertThat()
                .statusCode(409)
                .extract()
                .path("message");
    }

    @Step("Crating of courier without any credentials {courier}")
    public String createWithoutCred(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");
    }

    @Step("Login with credentials")
    public int login(CourierCredentials creds) {
        return given()
                .spec(getBaseSpec())
                .body(creds)
                .when()
                .post(COURIER_PATH + "login")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }

    @Step("Login with credentials with full out JSON")
    public ValidatableResponse fullLogin(CourierCredentials creds) {
        return given()
                .spec(getBaseSpec())
                .body(creds)
                .when()
                .post(COURIER_PATH + "login")
                .then();
    }

    @Step("Deliting of courier {courierId}")
    public boolean delete(int courierId) {
        return given().log().all()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + courierId)
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }
}
