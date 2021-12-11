package TheShoeBox.TheShoeBox.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shoes")
public class ShoeEntity extends BaseEntity{

    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private BigDecimal size;
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String location;
    @Lob
    @Column(nullable = false)
    private String description;

    private String imageUrl;

    private LocalDateTime createdOn;

    private Boolean ordered;


    @OneToMany(mappedBy = "shoe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    private ShoeCategoryEntity shoeCategoryEntity;

    @ManyToOne
    private ShoeConditionEntity shoeConditionEntity;

    @ManyToOne
    private UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private CollectionEntity collection;


    public Boolean getOrdered() {
        return ordered;
    }

    public ShoeEntity setOrdered(Boolean ordered) {
        this.ordered = ordered;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public ShoeEntity setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public CollectionEntity getCollection() {
        return collection;
    }

    public void setCollection(CollectionEntity collection) {
        this.collection = collection;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ShoeEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ShoeEntity setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ShoeEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getSize() {
        return size;
    }

    public ShoeEntity setSize(BigDecimal size) {
        this.size = size;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoeEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ShoeEntity setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public ShoeEntity setCreator(UserEntity creator) {
        this.creator = creator;
        return this;
    }

    public ShoeCategoryEntity getShoeCategoryEntity() {
        return shoeCategoryEntity;
    }

    public ShoeEntity setShoeCategoryEntity(ShoeCategoryEntity shoeCategoryEntity) {
        this.shoeCategoryEntity = shoeCategoryEntity;
        return this;
    }

    public ShoeConditionEntity getShoeConditionEntity() {
        return shoeConditionEntity;
    }

    public ShoeEntity setShoeConditionEntity(ShoeConditionEntity shoeConditionEntity) {
        this.shoeConditionEntity = shoeConditionEntity;
        return this;
    }
}
