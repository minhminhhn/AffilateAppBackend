package affilateweb.model.requestobject;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateInfoAccount {
    private String name;
    private String email;
    private String avatar;

    public UpdateInfoAccount() {
    }

    public UpdateInfoAccount(String name, String email, String avatar) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

}
