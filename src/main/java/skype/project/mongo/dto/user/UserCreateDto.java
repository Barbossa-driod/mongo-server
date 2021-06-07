package skype.project.mongo.dto.user;

import lombok.Data;
import skype.project.mongo.entity.PropertyEntity;

@Data
public class UserCreateDto {

    private String name;
    private String surname;
    private int age;
    private String sex;
    private PropertyEntity property;
}
