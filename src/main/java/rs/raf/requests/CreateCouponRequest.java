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

    @NotNull
    @Positive
    private long shopId;

    @NotNull
    @NotBlank
    private String product;

    @NotNull
    @PositiveOrZero
    private float discountedPrice;

    @NotNull
    @PositiveOrZero
    private float originalPrice;

    @NotNull
    @NotBlank
    private String validFrom;

    @NotNull
    @NotBlank
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
            return new SimpleDateFormat("yyyy-mm-dd").parse(getValidFrom());
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
        try {
            return new SimpleDateFormat("yyyy-mm-dd").parse(getValidTo());
        } catch (ParseException e) {
            return null;
        }
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

}
