package skype.project.mongo.dto.user;

import lombok.Data;
import org.bson.types.ObjectId;
import skype.project.mongo.entity.PropertyEntity;

@Data
public class UserFullDto {

    private /*ObjectId*/ String id;
    private String name;
    private String surname;
    private int age;
    private String sex;
    private PropertyEntity property;

}
