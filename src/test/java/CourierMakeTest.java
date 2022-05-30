import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CourierMakeTest {

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
    @DisplayName("Courier is creating")
    @Description("Try to create new courier")
    public void courier() {
        Courier courier = Courier.getRandom();
        boolean isCreated = courierClient.create(courier);
        courierId = courierClient.login(CourierCredentials.from(courier));
        assertTrue(isCreated);
        assertNotEquals(0, courierId);
        System.out.println("Courier with id: " + courierId + " have been created");
    }

}
