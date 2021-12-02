package TheShoeBox.TheShoeBox.model.view;

public class AdminPanelUserViewModel {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;

    public Long getId() {
        return id;
    }

    public AdminPanelUserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AdminPanelUserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public AdminPanelUserViewModel setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public AdminPanelUserViewModel setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AdminPanelUserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
