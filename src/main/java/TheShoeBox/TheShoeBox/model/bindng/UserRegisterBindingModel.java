package TheShoeBox.TheShoeBox.model.bindng;

import TheShoeBox.TheShoeBox.model.validator.anotations.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
    @NotBlank
    @Size(min=4, max=20)
    private String username;
    @NotNull
    @Size(min=4, max=20)
    private String firstname;
    @NotNull
    @Size(min=4, max=20)
    private String lastname;
    @ValidEmail
    @NotNull
    @Size(min=4, max=20)
    private String email;
    @NotNull
    @Size(min=4, max=20)
    private String password;
    @NotNull
    @Size(min=4, max=20)
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
