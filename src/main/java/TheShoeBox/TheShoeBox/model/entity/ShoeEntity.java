package TheShoeBox.TheShoeBox.model.entity;

import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;

import javax.persistence.*;
import java.math.BigDecimal;

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
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Enumerated(value = EnumType.STRING)
    private ShoeCategoryEnum category;

    @ManyToOne
    private UserEntity creator;


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

    public ShoeCategoryEnum getCategory() {
        return category;
    }

    public ShoeEntity setCategory(ShoeCategoryEnum category) {
        this.category = category;
        return this;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public ShoeEntity setCreator(UserEntity creator) {
        this.creator = creator;
        return this;
    }
}
