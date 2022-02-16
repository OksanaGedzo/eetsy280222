package ee.bcs.eetsy.domain.sub_group;

import ee.bcs.eetsy.domain.picture.PictureDto;
import lombok.Data;

@Data
public class SubGroupResponse {
    private final PictureDto picture;
    private final String name;
}
