package TheShoeBox.TheShoeBox.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedEntityGraph(
        name = "user-roles",
        attributeNodes = {
                @NamedAttributeNode("roles")
        }
)
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<UserRoleEntity> roles;
    @OneToMany(mappedBy = "creator",fetch = FetchType.EAGER)
    private Set<ShoeEntity> shoes;
    @OneToOne
    private CollectionEntity collection;

    private LocalDateTime sinceFrom = LocalDateTime.now();

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

    public LocalDateTime getSinceFrom() {
        return sinceFrom;
    }

    public UserEntity setSinceFrom(LocalDateTime sinceFrom) {
        this.sinceFrom = sinceFrom;
        return this;
    }
}
