package skype.project.mongo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skype.project.mongo.dto.user.UserCreateDto;
import skype.project.mongo.dto.user.UserFullDto;
import skype.project.mongo.dto.user.UserPreviewDto;
import skype.project.mongo.entity.UserEntity;
import skype.project.mongo.exception.EntityIsNotFoundException;
import skype.project.mongo.mapper.PropertyMapper;
import skype.project.mongo.mapper.UserMapper;
import skype.project.mongo.repository.UserRepository;
import skype.project.mongo.service.PropertyService;
import skype.project.mongo.service.UserService;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PropertyService propertyService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PropertyMapper propertyMapper;


    @Override
    public UserFullDto findById(String id) {
        UserEntity foundUserEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityIsNotFoundException(String.format("User was not found by id: %s", id)));
        return userMapper.mapToFullDto(foundUserEntity);
    }

    @Override
    public UserEntity create(UserCreateDto createDto) {
        UserEntity userEntityToSave = userMapper.mapToEntity(createDto);
        return userRepository.save(userEntityToSave);
    }

    @Override
    public List<UserPreviewDto> findAll() {
        List<UserEntity> allProperties = userRepository.findAll();

        List<UserPreviewDto> previewDtoList = new ArrayList<>();
        for (UserEntity userEntity: allProperties){
            previewDtoList.add(userMapper.mapToPreviewDto(userEntity));
        }
        return previewDtoList;
    }


    @Override
    public void delete(String id) {
        if (!userRepository.existsById(id)){
            throw new EntityIsNotFoundException(String.format("User was not found by id: %s", id));
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserFullDto update(UserFullDto fullDto) {
        UserEntity foundUserEntity = userRepository.findById(fullDto.getId())
                .orElseThrow(() -> new EntityIsNotFoundException(String.format("User was not found by id: %s", fullDto.getId())));

        foundUserEntity.setAge(fullDto.getAge());
        foundUserEntity.setName(fullDto.getName());
        foundUserEntity.setSex(fullDto.getSex());
        foundUserEntity.setSurname(fullDto.getSurname());

        String id = fullDto.getProperty().getId();
        if (id !=null && !id.trim().isEmpty()){
            propertyService.update(propertyMapper.mapToFullDto(fullDto.getProperty()));
            foundUserEntity.setProperty(fullDto.getProperty());
        }

        UserEntity updatedPropertyEntity = userRepository.save(foundUserEntity);

        return userMapper.mapToFullDto(updatedPropertyEntity);
    }
}
