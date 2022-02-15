package ee.bcs.eetsy.domain.sub_group;

import ee.bcs.eetsy.domain.item.Item;
import ee.bcs.eetsy.domain.picture.PictureDto;
import ee.bcs.eetsy.domain.primary_group.PrimaryGroupDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class SubGroupDto implements Serializable {
    private final Integer id;
    private final PrimaryGroupDto primaryGroup;
    private final Item item;
    private final PictureDto picture;
    private final String name;
}
