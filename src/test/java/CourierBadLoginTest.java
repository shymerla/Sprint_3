import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


public class CourierBadLoginTest {

    CourierClient courierClient;
    Courier courier;


    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Courier.getRandom();
        courierClient.create(courier);
    }

    @After
    public void tearDown() {
        courierClient.delete(courierClient.login(CourierCredentials.from(courier)));
    }

    @Test
    @DisplayName("Courier bad login test 1")
    @Description("Courier cannot login with wrong login credentials")
    public void courierCanNotLoginWithWrongCred() {
        ValidatableResponse loginResponse = courierClient.fullLogin(CourierCredentials.builder()
                .login(courier.getLogin())
                .password(courier.getPassword() + "oops")
                .build());
        int statusCode = loginResponse.extract().statusCode();
        String errorMessage = loginResponse.extract().path("message");

        assertThat("Courier login", statusCode, equalTo(SC_NOT_FOUND));
        assertEquals("Учетная запись не найдена", errorMessage);
    }

    @Test
    @DisplayName("Courier bad login test 2")
    @Description("Courier cannot login without login credentials")
    public void courierCanLoginWithoutLoginCred() {
        ValidatableResponse loginResponse = courierClient.fullLogin(CourierCredentials.builder()
                .password(courier.getPassword())
                .build());
        int statusCode = loginResponse.extract().statusCode();
        String errorMessage = loginResponse.extract().path("message");

        assertThat("Courier login", statusCode, equalTo(SC_BAD_REQUEST));
        assertEquals("Недостаточно данных для входа", errorMessage);
    }

    @Test
    @DisplayName("Courier bad login test 3")
    @Description("Courier cannot login without password credentials")
    public void courierCanLoginWithoutPasswordCred() {
        ValidatableResponse loginResponse = courierClient.fullLogin(CourierCredentials.builder()
                .login(courier.getLogin())
                .build());
        int statusCode = loginResponse.extract().statusCode();
        String errorMessage = loginResponse.extract().path("message");

        assertThat("Courier login", statusCode, equalTo(SC_BAD_REQUEST));
        assertEquals("Недостаточно данных для входа", errorMessage);
    }

}
