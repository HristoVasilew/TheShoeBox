package TheShoeBox.TheShoeBox.model.view;

import TheShoeBox.TheShoeBox.model.entity.ShoeCategoryEntity;
import TheShoeBox.TheShoeBox.model.entity.ShoeConditionEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShoeViewModel {
    private Long id;
    private String brand;
    private String model;
    private BigDecimal price;
    private String imageUrl;
    private String description;
    private LocalDateTime createdOn;
    private BigDecimal size;
    private String location;
    private ShoeCategoryEnum shoeCategoryEnum;
    private ConditionEnum conditionEnum;
    private Boolean canDelete;
    private String sellerFullName;


    public Long getId() {
        return id;
    }

    public ShoeViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public ShoeViewModel setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public ShoeViewModel setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
        return this;
    }

    public BigDecimal getSize() {
        return size;
    }

    public ShoeViewModel setSize(BigDecimal size) {
        this.size = size;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ShoeViewModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public ShoeCategoryEnum getShoeCategoryEnum() {
        return shoeCategoryEnum;
    }

    public ShoeViewModel setShoeCategoryEnum(ShoeCategoryEnum shoeCategoryEnum) {
        this.shoeCategoryEnum = shoeCategoryEnum;
        return this;
    }

    public ConditionEnum getConditionEnum() {
        return conditionEnum;
    }

    public ShoeViewModel setConditionEnum(ConditionEnum conditionEnum) {
        this.conditionEnum = conditionEnum;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public ShoeViewModel setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public ShoeViewModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ShoeViewModel setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoeViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeViewModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
