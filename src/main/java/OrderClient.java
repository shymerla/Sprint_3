import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends ScooterRestClient {

    private static final String COURIER_PATH = "api/v1/orders/";

    @Step("Creating of order {order}")
    public ValidatableResponse createOrder (Object[][] order) {
        return given().log().all()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(COURIER_PATH)
                .then();
    }

    @Step("List of orders")
    public ValidatableResponse gettingListOfOrder () {
        return given().log().all()
                .spec(getBaseSpec())
                .when()
                .get(COURIER_PATH)
                .then();
    }

}
