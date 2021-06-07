package skype.project.mongo.service;

import skype.project.mongo.dto.property.PropertyCreateDto;
import skype.project.mongo.dto.property.PropertyFullDto;
import skype.project.mongo.dto.property.PropertyPreviewDto;
import skype.project.mongo.entity.PropertyEntity;

import java.util.List;

public interface PropertyService {

    PropertyFullDto findById(String id);

    PropertyEntity create(PropertyCreateDto propertyCreateDto);

    List<PropertyPreviewDto> findAll();

    List<PropertyPreviewDto> findAllWithFilter(PropertyPreviewDto filterDto);

    void delete(String id);

    PropertyFullDto update(PropertyFullDto propertyFullDto);
}

