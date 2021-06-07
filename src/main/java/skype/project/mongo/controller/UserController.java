package skype.project.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skype.project.mongo.dto.user.UserCreateDto;
import skype.project.mongo.dto.user.UserFullDto;
import skype.project.mongo.dto.user.UserPreviewDto;
import skype.project.mongo.entity.UserEntity;
import skype.project.mongo.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public UserFullDto findById(@PathVariable String id){
        return userService.findById(id);
    }

    @GetMapping()
    public List<UserPreviewDto> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        userService.delete(id);
    }

    @PostMapping("/create")
    public UserEntity create(@RequestBody UserCreateDto createDto){
        return userService.create(createDto);
    }

    @PutMapping("/update")
    public UserFullDto update(@RequestBody UserFullDto fullDto){
        return userService.update(fullDto);
    }
}
