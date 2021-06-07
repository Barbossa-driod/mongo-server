package skype.project.mongo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skype.project.mongo.dto.property.PropertyCreateDto;
import skype.project.mongo.dto.property.PropertyFullDto;
import skype.project.mongo.dto.property.PropertyPreviewDto;
import skype.project.mongo.entity.PropertyEntity;
import skype.project.mongo.exception.EntityIsNotFoundException;
import skype.project.mongo.mapper.PropertyMapper;
import skype.project.mongo.repository.PropertyRepository;
import skype.project.mongo.service.PropertyService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public PropertyFullDto findById(String id) {
        PropertyEntity foundPropertyEntity = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityIsNotFoundException(String.format("Property was not found by id: %s", id)));
        return propertyMapper.mapToFullDto(foundPropertyEntity);
    }

    @Override
    public PropertyEntity create(PropertyCreateDto createDto) {
        PropertyEntity propertyEntityToSave = propertyMapper.mapToEntity(createDto);
        return propertyRepository.save(propertyEntityToSave);
    }

    @Override
    public List<PropertyPreviewDto> findAll(){
        List<PropertyEntity> allProperties = propertyRepository.findAll();

        List<PropertyPreviewDto> previewDtoList = new ArrayList<>();
        for (PropertyEntity propertyEntity: allProperties){
            previewDtoList.add(propertyMapper.mapToPreviewDto(propertyEntity));
        }
        return previewDtoList;
    }

    @Override
    public List<PropertyPreviewDto> findAllWithFilter(PropertyPreviewDto filterDto) {
        List<PropertyEntity> allProperties = propertyRepository.findAll();

        List<PropertyPreviewDto> previewDtoList = new ArrayList<>();
        for (PropertyEntity propertyEntity: allProperties){
            previewDtoList.add(propertyMapper.mapToPreviewDto(propertyEntity));
        }

        Stream<PropertyPreviewDto> streamProperty = previewDtoList.stream();
        List<PropertyPreviewDto> filterProperty = streamProperty
                        .filter(p -> p.getSize().equals(filterDto.getSize()))
                        .filter(p -> p.getLocation().getCity().equals(filterDto.getLocation().getCity()))
                        .collect(Collectors.toList());

        return filterProperty;
    }

    @Override
    public void delete(String id) {
        if (!propertyRepository.existsById(id)){
            throw new EntityIsNotFoundException(String.format("Property was not found by id: %s", id));
        }
        propertyRepository.deleteById(id);
    }

    @Override
    public PropertyFullDto update(PropertyFullDto fullDto) {
        PropertyEntity foundPropertyEntity = propertyRepository.findById(fullDto.getId())
                .orElseThrow(() -> new EntityIsNotFoundException(String.format("Property was not found by id: %s", fullDto.getId())));

        foundPropertyEntity.setLocation(fullDto.getLocation());
        foundPropertyEntity.setSize(fullDto.getSize());
        foundPropertyEntity.setDescription(fullDto.getDescription());

        PropertyEntity updatedPropertyEntity = propertyRepository.save(foundPropertyEntity);

        return propertyMapper.mapToFullDto(updatedPropertyEntity);
    }

}
