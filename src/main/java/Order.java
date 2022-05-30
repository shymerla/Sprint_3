import io.qameta.allure.Step;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class Order {
    private final String firstName;
    private final String lastname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String color;


    public Order(String firstName, String lastname, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String color) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Step("Getting order details")
    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"Oscar", "Wild", "Pyshkina 10", "Podbelskogo", "+79379990101", 3, "01.06.2022", "Hello", "GREY"},
                {"Oscar", "Wild", "Pyshkina 10", "Podbelskogo", "+79379990101", 3, "01.06.2022", "Hello", "BLACK"},
                {"Oscar", "Wild", "Pyshkina 10", "Podbelskogo", "+79379990101", 3, "01.06.2022", "Hello", "BLACK GREY"}
        };

    }
}
