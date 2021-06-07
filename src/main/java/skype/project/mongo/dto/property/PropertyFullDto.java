package skype.project.mongo.dto.property;

import lombok.Data;
import skype.project.mongo.entity.LocationEntity;

@Data
public class PropertyFullDto {

    private String id;
    private String size;
    private String description;
    private String img;
    private LocationEntity location;

}
