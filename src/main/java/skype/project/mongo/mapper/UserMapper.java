package skype.project.mongo.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import skype.project.mongo.dto.user.UserCreateDto;
import skype.project.mongo.dto.user.UserFullDto;
import skype.project.mongo.dto.user.UserPreviewDto;
import skype.project.mongo.entity.UserEntity;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserFullDto mapToFullDto(UserEntity entity);

    UserPreviewDto mapToPreviewDto(UserEntity entity);

    UserEntity mapToEntity(UserCreateDto createDto);
}
