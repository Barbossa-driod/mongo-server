package skype.project.mongo.dto.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import skype.project.mongo.entity.LocationEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyPreviewDto {

    private String id;
    private String size;
    private String img;
    private LocationEntity location;

}
