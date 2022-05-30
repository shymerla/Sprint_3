import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierBadMakeTest {

    CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Courier is creating without some credentials")
    @Description("Try to create courier without login credentials")
    public void cred1() {
        Courier courier = Courier.builder()
                .password(RandomStringUtils.randomAlphabetic(10))
                .firstName(RandomStringUtils.randomAlphabetic(10))
                .build();
        String errorCred = courierClient.createWithoutCred(courier);
        assertEquals("Недостаточно данных для создания учетной записи", errorCred);

    }
    @Test
    @DisplayName("Courier is creating without some credentials")
    @Description("Try to create courier without password credentials")
    public void cred2() {
        Courier courier = Courier.builder()
                .login(RandomStringUtils.randomAlphabetic(10))
                .firstName(RandomStringUtils.randomAlphabetic(10))
                .build();
        String errorCred = courierClient.createWithoutCred(courier);
        assertEquals("Недостаточно данных для создания учетной записи", errorCred);

    }
    @Test
    @DisplayName("Courier is creating without some credentials")
    @Description("Try to create courier without firstName credentials")
    public void cred3() {
        Courier courier = Courier.builder()
                .login(RandomStringUtils.randomAlphabetic(10))
                .password(RandomStringUtils.randomAlphabetic(10))
                .build();
        String errorCred = courierClient.createWithoutCred(courier);
        assertEquals("Недостаточно данных для создания учетной записи", errorCred);

    }

}
