package skype.project.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skype.project.mongo.dto.property.PropertyCreateDto;
import skype.project.mongo.dto.property.PropertyFullDto;
import skype.project.mongo.dto.property.PropertyPreviewDto;
import skype.project.mongo.entity.PropertyEntity;
import skype.project.mongo.service.PropertyService;

import java.util.List;

@RestController
@RequestMapping(value = "/property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping("/{id}")
    public PropertyFullDto findById(@PathVariable String id){
        return propertyService.findById(id);
    }

    @GetMapping()
    public List<PropertyPreviewDto> findAll() {
       return propertyService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        propertyService.delete(id);
    }

    @PostMapping("/create")
    public PropertyEntity create(@RequestBody PropertyCreateDto createDto){
        return propertyService.create(createDto);
    }

    @PutMapping("/update")
    public PropertyFullDto update(@RequestBody PropertyFullDto fullDto){
        return propertyService.update(fullDto);
    }

    @PostMapping("/filter")
    public List<PropertyPreviewDto> findAllWithFilter(@RequestBody PropertyPreviewDto filterDto) {
        return propertyService.findAllWithFilter(filterDto);
    }
}
