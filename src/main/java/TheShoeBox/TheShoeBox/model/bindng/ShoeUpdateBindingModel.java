package TheShoeBox.TheShoeBox.model.bindng;

import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ShoeUpdateBindingModel {

    private Long id;
    @Size(min = 2, max = 10)
    private String brand;
    @Size(min = 2, max = 10)
    private String model;
    @NotNull
    @Positive
    @Max(50)
    private BigDecimal size;
    @NotNull
    @Positive
    @Max(9999)
    private BigDecimal price;
    @Size(min = 4, max = 140)
    private String location;
    @Size(min = 4, max = 200)
    private String description;
    private String imageUrl;

    private ConditionEnum conditionEnum;

    private ShoeCategoryEnum shoeCategoryEnum;

    public String getBrand() {
        return brand;
    }

    public ShoeUpdateBindingModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ShoeUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public ConditionEnum getConditionEnum() {
        return conditionEnum;
    }

    public ShoeUpdateBindingModel setConditionEnum(ConditionEnum conditionEnum) {
        this.conditionEnum = conditionEnum;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeUpdateBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ShoeUpdateBindingModel setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getSize() {
        return size;
    }

    public ShoeUpdateBindingModel setSize(BigDecimal size) {
        this.size = size;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoeUpdateBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ShoeUpdateBindingModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeUpdateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ShoeCategoryEnum getShoeCategoryEnum() {
        return shoeCategoryEnum;
    }

    public ShoeUpdateBindingModel setShoeCategoryEnum(ShoeCategoryEnum shoeCategoryEnum) {
        this.shoeCategoryEnum = shoeCategoryEnum;
        return this;
    }
}
