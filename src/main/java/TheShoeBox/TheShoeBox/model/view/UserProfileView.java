package TheShoeBox.TheShoeBox.model.view;


import TheShoeBox.TheShoeBox.model.entity.ShoeEntity;
import TheShoeBox.TheShoeBox.model.entity.UserRoleEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class UserProfileView {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Set<UserRoleEntity> roles;
    private LocalDateTime sinceFrom;
    private Set<ShoeEntity> shoes;

    public UserProfileView() {
    }


    public Set<ShoeEntity> getShoes() {
        return shoes;
    }

    public UserProfileView setShoes(Set<ShoeEntity> shoes) {
        this.shoes = shoes;
        return this;
    }

    public LocalDateTime getSinceFrom() {
        return sinceFrom;
    }

    public UserProfileView setSinceFrom(LocalDateTime sinceFrom) {
        this.sinceFrom = sinceFrom;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserProfileView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UserProfileView setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UserProfileView setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileView setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserProfileView setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
