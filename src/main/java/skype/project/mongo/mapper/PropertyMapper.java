package skype.project.mongo.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import skype.project.mongo.dto.property.PropertyCreateDto;
import skype.project.mongo.dto.property.PropertyFullDto;
import skype.project.mongo.dto.property.PropertyPreviewDto;
import skype.project.mongo.entity.PropertyEntity;

@Mapper(componentModel = "spring")
@Component
public interface PropertyMapper {


    PropertyFullDto mapToFullDto(PropertyEntity entity);

    PropertyPreviewDto mapToPreviewDto(PropertyEntity entity);

    PropertyEntity mapToEntity(PropertyCreateDto createDto);

}
