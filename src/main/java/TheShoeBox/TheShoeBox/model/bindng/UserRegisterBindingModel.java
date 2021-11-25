package TheShoeBox.TheShoeBox.model.bindng;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    @NotBlank
    @Size(min = 2, max = 20, message = "Username must be between 2 and 20 symbols")
    private String username;
    @NotBlank
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 symbols")
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 symbols")
    private String lastName;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }


}
