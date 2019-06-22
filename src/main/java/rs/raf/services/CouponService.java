package rs.raf.services;

import rs.raf.dtos.CouponDto;
import rs.raf.enums.CouponStatus;
import rs.raf.mappers.CouponMapper;
import rs.raf.models.Coupon;
import rs.raf.repositories.Coupon.CouponRepository;
import rs.raf.responses.PaginationResponse;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CouponService {

    private CouponRepository couponRepository;

    @Inject
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public PaginationResponse<CouponDto> paginate(int limit, int page, CouponStatus couponStatus)
    {
        List<Coupon> coupons = this.couponRepository.paginate(limit, page, couponStatus);
        List<CouponDto> couponDtos = new ArrayList<>(coupons.size());
        for (Coupon coupon: coupons) {
            couponDtos.add(CouponMapper.instance.couponToCouponDto(coupon));
        }
        int totalSize = this.couponRepository.count(couponStatus);
        int pages = (int) Math.ceil(totalSize/(float)limit);
        return new PaginationResponse<>(couponDtos, totalSize, page, pages, limit);
    }
}
