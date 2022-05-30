import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierDoubleMakeTest {

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
    @DisplayName("Creatin double courier")
    @Description("Try to create new courier with same name")
    public void courier() {
        Courier courier = Courier.getRandom();
        boolean isCreated = courierClient.create(courier);
        assertTrue(isCreated);
        String errorDoubleLogin = courierClient.doubleCreate(courier);
        assertEquals("Этот логин уже используется. Попробуйте другой.", errorDoubleLogin);
        System.out.println("Courier login is busy");
        courierId = courierClient.login(CourierCredentials.from(courier));
    }

}
