package rs.raf.dtos;

public class CouponDto {
    private long id;
    private String product;
    private float discountedPrice;
    private float originalPrice;
    private String validFrom;
    private String validTo;

    public CouponDto() { }

    public CouponDto(long id, String product, float originalPrice, float discountedPrice, String validFrom, String validTo) {
        this.id = id;
        this.product = product;
        this.discountedPrice = discountedPrice;
        this.originalPrice = originalPrice;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public CouponDto(long id, String product, float originalPrice, float discountedPrice, String validFrom) {
        this(id, product, originalPrice, discountedPrice, validFrom, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }
}
