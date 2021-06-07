package skype.project.mongo.service;

import skype.project.mongo.dto.user.UserCreateDto;
import skype.project.mongo.dto.user.UserFullDto;
import skype.project.mongo.dto.user.UserPreviewDto;
import skype.project.mongo.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserFullDto findById(String id);

    UserEntity create(UserCreateDto propertyCreateDto);

    List<UserPreviewDto> findAll();

    void delete(String id);

    UserFullDto update(UserFullDto propertyFullDto);
}
