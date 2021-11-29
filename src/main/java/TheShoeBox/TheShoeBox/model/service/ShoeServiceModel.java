package TheShoeBox.TheShoeBox.model.service;

import TheShoeBox.TheShoeBox.model.entity.ShoeCategoryEntity;
import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShoeServiceModel {

    private Long id;
    private String brand;
    private String model;
    private BigDecimal size;
    private BigDecimal price;
    private String location;
    private String description;
    private ShoeCategoryEnum shoeCategoryEnum;
    private LocalDateTime createdOn;
    private UserEntity user;
    private String imageUrl;

    public String getBrand() {
        return brand;
    }

    public ShoeCategoryEnum getShoeCategoryEnum() {
        return shoeCategoryEnum;
    }

    public ShoeServiceModel setShoeCategoryEnum(ShoeCategoryEnum shoeCategoryEnum) {
        this.shoeCategoryEnum = shoeCategoryEnum;
        return this;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public ShoeServiceModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ShoeServiceModel setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getSize() {
        return size;
    }

    public ShoeServiceModel setSize(BigDecimal size) {
        this.size = size;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoeServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ShoeServiceModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ShoeServiceModel setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ShoeServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ShoeServiceModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
