package skype.project.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "user")
public class UserEntity {

    @Id
    private /*ObjectId*/ String id;

    @Field
    private String name;

    @Field
    private String surname;

    @Field
    private int age;

    @Field
    private String sex;

    @DBRef
    private PropertyEntity property;
}
