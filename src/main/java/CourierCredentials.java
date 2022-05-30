import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourierCredentials {

    public String login;
    public String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCredentials from(Courier courier) {
        System.out.println("!!!" + courier.getLogin());
        System.out.println("!!!" + courier.getPassword());
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

}
