import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

public class OrderListTest {

    OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Order list is getting")
    @Description("Try to get order list")
    public void orderListGetting() {
        ValidatableResponse orderListResponse = orderClient.gettingListOfOrder();
        int statusCode = orderListResponse.extract().statusCode();
        String orderList = orderListResponse.extract().path("orders").toString();

        assertThat("Order list cannot make", statusCode, equalTo(SC_OK));
        assertNotNull(orderList);

        System.out.println("List order is: " + orderList);
    }

}
