package TheShoeBox.TheShoeBox.model.bindng;

import TheShoeBox.TheShoeBox.model.entity.ShoeCategoryEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ShoeBindingModel {

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
    @Max(1000)
    private BigDecimal price;
    @Size(min = 2, max = 100)
    private String location;
    @Size(min = 2, max = 40)
    private String description;
    private String imageUrl;

    private ShoeCategoryEnum shoeCategoryEnum;

    public String getBrand() {
        return brand;
    }

    public ShoeBindingModel setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShoeBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ShoeBindingModel setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getSize() {
        return size;
    }

    public ShoeBindingModel setSize(BigDecimal size) {
        this.size = size;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ShoeBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ShoeBindingModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ShoeBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public ShoeCategoryEnum getShoeCategoryEnum() {
        return shoeCategoryEnum;
    }

    public ShoeBindingModel setShoeCategoryEnum(ShoeCategoryEnum shoeCategoryEnum) {
        this.shoeCategoryEnum = shoeCategoryEnum;
        return this;
    }
}
