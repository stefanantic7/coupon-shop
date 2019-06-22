package rs.raf.repositories.Coupon;

import rs.raf.models.Coupon;

import java.util.List;

public interface CouponRepository {
    List<Coupon> getWhereShopId(int shopId);
}
