package skype.project.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "property")
public class PropertyEntity {

    @Id
    private String id;

    @Field
    private String size;

    @Field
    private LocationEntity location;

    @Field
    private String description;

    @Field
    private String img;


}
