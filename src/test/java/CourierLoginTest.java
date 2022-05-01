import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CourierLoginTest {

    CourierClient courierClient;
    int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    public void courier() {
        Courier courier = Courier.getRandom();
        boolean isCreated = courierClient.create(courier);
        courierId = courierClient.login(CourierCredentials.from(courier));

        assertTrue(isCreated);
        assertNotEquals(0, courierId);
    }

    /*@Test
    public  void courierCanLoginWithValidCredentials() {
        ValidatableResponse loginResponce = courierClient.login(new CourierCredentials(courier.login, courier.password));
        int statusCode = loginResponce.extract().statusCode();
        courierId = loginResponce.extract().path( "id");

        assertThat("Courier cannot login", statusCode, equalTo(SC_OK));
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
    }*/
}
