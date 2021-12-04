package TheShoeBox.TheShoeBox.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {


    private Long buyerId;
    private String buyerFullName;
    private Long sellerId;
    private String sellerFullName;
    private Long productId;
    private String imageUrl;
    private String brandAndModelProduct;
    private BigDecimal price;


    public Long getBuyerId() {
        return buyerId;
    }

    public OrderEntity setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getBuyerFullName() {
        return buyerFullName;
    }

    public OrderEntity setBuyerFullName(String buyerFullName) {
        this.buyerFullName = buyerFullName;
        return this;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public OrderEntity setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public OrderEntity setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public OrderEntity setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OrderEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBrandAndModelProduct() {
        return brandAndModelProduct;
    }

    public OrderEntity setBrandAndModelProduct(String brandAndModelProduct) {
        this.brandAndModelProduct = brandAndModelProduct;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
