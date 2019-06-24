package rs.raf.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateCouponRequest {

    @NotNull(message = "Shop id must not be blank")
    @Positive(message = "Shop id must not be blank")
    private long shopId;

    @NotNull(message = "Product must not be blank")
    @NotBlank(message = "Product must not be blank")
    private String product;

    @NotNull(message = "Discounted price must not be blank")
    @PositiveOrZero(message = "Discounted price positive number")
    private float discountedPrice;

    @NotNull(message = "Original price must not be blank")
    @PositiveOrZero(message = "Original price positive number")
    private float originalPrice;

    @NotNull(message = "Valid from must not be blank")
    @NotBlank(message = "Valid from must not be blank")
    private String validFrom;

    private String validTo;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
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

    public Date getValidFromDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(getValidFrom());
        } catch (ParseException e) {
            return null;
        }
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public Date getValidToDate() {
        if (getValidTo() == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(getValidTo());
        } catch (ParseException e) {
            return null;
        }
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

}
