package TheShoeBox.TheShoeBox.model.bindng;

import TheShoeBox.TheShoeBox.model.validator.anotations.UniqueEmail;
import TheShoeBox.TheShoeBox.model.validator.anotations.UniqueUsername;
import TheShoeBox.TheShoeBox.model.validator.anotations.ValidEmail;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserUpdateBindingModel {

    private Long id;

    @NotNull
    @Size(min = 4, max = 20, message = "First name should be between 4 and 20 characters.")
    private String firstname;
    @NotNull
    @Size(min = 4 , max = 20, message = "Last name should be between 4 and 20 characters.")
    private String lastname;
    @UniqueUsername
    @NotBlank
    @Size(min = 4, max = 20, message = "Username should be between 4 and 20 characters.")
    private String username;
    @UniqueEmail
    @ValidEmail(message = "Please enter a valid email address.")
    @NotNull
    @Size(min = 6, max = 50, message = "Maximum email length is 50 characters.")
    private String email;


    public Long getId() {
        return id;
    }

    public UserUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserUpdateBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserUpdateBindingModel setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserUpdateBindingModel setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserUpdateBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
