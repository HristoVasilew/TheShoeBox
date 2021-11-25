package TheShoeBox.TheShoeBox.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "creator")
    private Set<ShoeEntity> shoes;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private CollectionEntity collection;

    public CollectionEntity getCollection() {
        return collection;
    }

    public void setCollection(CollectionEntity collection) {
        this.collection = collection;
    }


    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public Set<ShoeEntity> getShoes() {
        return shoes;
    }

    public UserEntity setShoes(Set<ShoeEntity> shoes) {
        this.shoes = shoes;
        return this;
    }
}
