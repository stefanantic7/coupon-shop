package rs.raf.services;

import org.jvnet.hk2.annotations.Service;
import rs.raf.dtos.ShopDto;
import rs.raf.mappers.ShopMapper;
import rs.raf.mappers.ShopMapperImpl;
import rs.raf.models.Coupon;
import rs.raf.models.Shop;
import rs.raf.repositories.CouponRepository;
import rs.raf.repositories.shop.ShopRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ShopService {

    private ShopRepository shopRepository;

    @Inject
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<ShopDto> paginate(int limit, int page) {
        List<ShopDto> shopDtos = new ArrayList<>();
        List<Shop> shops = this.shopRepository.paginate(limit, page);
        for (Shop shop: shops) {
            shopDtos.add(ShopMapper.INSTANCE.shopToShopDto(shop));
        }
        return shopDtos;
    }

}
