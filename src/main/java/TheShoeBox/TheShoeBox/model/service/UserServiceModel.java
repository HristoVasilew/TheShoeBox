package TheShoeBox.TheShoeBox.model.service;

import java.time.LocalDateTime;

public class UserServiceModel {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private LocalDateTime sinceFrom;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username != null ?
                username.trim() :
                null;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserServiceModel setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserServiceModel setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDateTime getSinceFrom() {
        return sinceFrom;
    }

    public UserServiceModel setSinceFrom(LocalDateTime sinceFrom) {
        this.sinceFrom = sinceFrom;
        return this;
    }
}
