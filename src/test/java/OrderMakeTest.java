import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderMakeTest {

    OrderClient orderClient;
    int track;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Order is creating")
    @Description("Try to create new order")
    public void order() {
        Object[][] order = Order.getOrderData();
        ValidatableResponse orderResponse = orderClient.createOrder(order);
        int statusCode = orderResponse.extract().statusCode();
        track = orderResponse.extract().path("track");

        assertThat("Order cannot make", statusCode, equalTo(SC_CREATED));
        assertThat("Order track is incorrect", track, is(not(0)));
        System.out.println("Track number is: " + track);
    }

}
