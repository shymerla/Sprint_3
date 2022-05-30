import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class CourierLoginTest {

    CourierClient courierClient;
    int courierId;
    Courier courier;


    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Courier.getRandom();
        courierClient.create(courier);
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Courier login test")
    @Description("Courier can login with right credentials")
    public void courierCanLoginWithNormCred() {
        ValidatableResponse loginResponse = courierClient.fullLogin(CourierCredentials.from(courier));
        int statusCode = loginResponse.extract().statusCode();
        courierId = loginResponse.extract().path("id");

        assertThat("Courer cannot login", statusCode, equalTo(SC_OK));
        assertThat("Courer ID is incorrect", courierId, is(not(0)));
    }

}
