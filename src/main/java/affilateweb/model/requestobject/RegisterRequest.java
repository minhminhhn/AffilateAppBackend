package affilateweb.model.requestobject;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private Integer roleId;
}