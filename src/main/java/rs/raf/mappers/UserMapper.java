package rs.raf.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import rs.raf.dtos.UserDto;
import rs.raf.models.User;

@Mapper
public interface UserMapper {
    UserMapper instance = Mappers.getMapper( UserMapper.class );

    UserDto userToUserDto(User user);
}
