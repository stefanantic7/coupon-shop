package rs.raf.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rs.raf.dtos.CouponDto;
import rs.raf.models.Coupon;

import java.util.List;

@Mapper
public interface CouponMapper {
    CouponMapper instance = Mappers.getMapper( CouponMapper.class );

    CouponDto couponToCouponDto(Coupon coupon);

}
