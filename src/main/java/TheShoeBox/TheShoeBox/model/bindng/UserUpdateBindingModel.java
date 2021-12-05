package TheShoeBox.TheShoeBox.model.bindng;

public class UserUpdateBindingModel {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
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
