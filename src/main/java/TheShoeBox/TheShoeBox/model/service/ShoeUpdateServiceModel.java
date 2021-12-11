package TheShoeBox.TheShoeBox.model.service;

import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShoeUpdateServiceModel {


    private Long id;
    private String brand;
    private String model;
    private BigDecimal size;
    private BigDecimal price;
    private String location;
    private String description;
    private ShoeCategoryEnum shoeCategoryEnum;
    private ConditionEnum conditionEnum;
    private LocalDateTime createdOn;
    private UserEntity user;
    private String imageUrl;
    private Boolean ordered;

    public Boolean getOrdered() {
        return ordered;
    }

    public ShoeUpdateServiceModel setOrdered(Boolean ordered) {
        this.ordered = ordered;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ShoeUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ShoeUpdateServiceModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ShoeUpdateServiceModel setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getSize() {
        return size;
    }

    public ShoeUpdateServiceModel setSize(BigDecimal size) {
        this.size = size;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoeUpdateServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ShoeUpdateServiceModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ShoeCategoryEnum getShoeCategoryEnum() {
        return shoeCategoryEnum;
    }

    public ShoeUpdateServiceModel setShoeCategoryEnum(ShoeCategoryEnum shoeCategoryEnum) {
        this.shoeCategoryEnum = shoeCategoryEnum;
        return this;
    }

    public ConditionEnum getConditionEnum() {
        return conditionEnum;
    }

    public ShoeUpdateServiceModel setConditionEnum(ConditionEnum conditionEnum) {
        this.conditionEnum = conditionEnum;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ShoeUpdateServiceModel setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ShoeUpdateServiceModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeUpdateServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
