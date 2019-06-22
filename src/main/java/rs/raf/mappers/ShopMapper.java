package rs.raf.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import rs.raf.dtos.ShopDto;
import rs.raf.models.Shop;

@Mapper
public interface ShopMapper {
    ShopMapper INSTANCE = Mappers.getMapper( ShopMapper.class );

    ShopDto shopToShopDto(Shop shop);

}
