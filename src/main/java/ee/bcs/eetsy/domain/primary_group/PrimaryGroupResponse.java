package ee.bcs.eetsy.domain.primary_group;

import ee.bcs.eetsy.domain.picture.PictureDto;
import lombok.Data;

@Data
public class PrimaryGroupResponse {
    private final int id;
    private final String name;
    private final int pictureId;
    private final String pictureData;
}
