package TheShoeBox.TheShoeBox.model.bindng;

import java.math.BigDecimal;

public class OrderBindingModel {

    private Long buyerId;

    private String buyerFullName;

    private Long sellerId;

    private String sellerFullName;

    private Long productId;

    private String imageUrl;

    private String brandAndModelProduct;

    private BigDecimal price;

    private Boolean shipped;

    public Boolean getShipped() {
        return shipped;
    }

    public OrderBindingModel setShipped(Boolean shipped) {
        this.shipped = shipped;
        return this;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public OrderBindingModel setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getBuyerFullName() {
        return buyerFullName;
    }

    public OrderBindingModel setBuyerFullName(String buyerFullName) {
        this.buyerFullName = buyerFullName;
        return this;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public OrderBindingModel setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public OrderBindingModel setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public OrderBindingModel setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OrderBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getBrandAndModelProduct() {
        return brandAndModelProduct;
    }

    public OrderBindingModel setBrandAndModelProduct(String brandAndModelProduct) {
        this.brandAndModelProduct = brandAndModelProduct;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
