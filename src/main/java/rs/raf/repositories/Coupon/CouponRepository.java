package rs.raf.repositories.Coupon;

import rs.raf.enums.CouponStatus;
import rs.raf.models.Coupon;

import java.util.Date;
import java.util.List;

public interface CouponRepository {
    List<Coupon> paginate(int limit, int page, CouponStatus couponStatus);
    List<Coupon> getWhereShopId(int shopId);
    int deleteWhereShopId(int shopId);
    int delete(long id);
    int count();
    int count(CouponStatus couponStatus);
    Coupon create(long shopId, String product, float discountedPrice,
                  float originalPrice, Date validFrom, Date validTo);
}
