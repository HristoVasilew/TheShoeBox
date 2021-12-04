package TheShoeBox.TheShoeBox.model.bindng;

import TheShoeBox.TheShoeBox.model.validator.anotations.UniqueEmail;
import TheShoeBox.TheShoeBox.model.validator.anotations.UniqueUsername;
import TheShoeBox.TheShoeBox.model.validator.anotations.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    //todo ne validira email size
    @UniqueUsername
    @NotBlank
    @Size(min = 4, max = 20, message = "Username should be between 4 and 20 characters.")
    private String username;
    @NotNull
    @Size(min = 2, max = 20, message = "First name should be between 4 and 20 characters.")
    private String firstname;
    @NotNull
    @Size(min = 2, max = 20, message = "Last name should be between 4 and 20 characters.")
    private String lastname;
    @UniqueEmail
    @ValidEmail(message = "Please enter a valid email address.")
    @NotNull
    @Size(min = 6, max = 50, message = "Maximum email length is 50 characters.")
    private String email;
    @NotNull(message = "Password is required and must not be blank.")
    @Size(min = 4, max = 20, message = "Password should be between 4 and 20 characters.")
    private String password;
    @NotNull(message = "Confirm Password is required and must not be blank.")
    @Size(min = 4, max = 20, message = "Password should be between 4 and 20 characters.")
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserRegisterBindingModel setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserRegisterBindingModel setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
