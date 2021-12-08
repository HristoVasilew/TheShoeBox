package TheShoeBox.TheShoeBox.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Column(nullable = false)
    private Boolean approved;
    @Lob
    private String textContent;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;
    @ManyToOne
    private ShoeEntity shoe;
    @ManyToOne
    private UserEntity author;

    public Comment() {
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }


    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public ShoeEntity getShoe() {
        return shoe;
    }

    public Comment setShoe(ShoeEntity shoe) {
        this.shoe = shoe;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public Comment setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}


