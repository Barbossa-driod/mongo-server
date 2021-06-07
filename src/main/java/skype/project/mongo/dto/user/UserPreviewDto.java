package skype.project.mongo.dto.user;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class UserPreviewDto {

    private /*ObjectId*/ String id;
    private String name;
    private String surname;

}
