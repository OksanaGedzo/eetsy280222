package ee.bcs.eetsy.domain.primary_group;

import ee.bcs.eetsy.domain.picture.PictureDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class PrimaryGroupDto implements Serializable {
    private final PictureDto picture;
    private final String name;
}
