package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.picture.PictureResponse;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemNewSubGroupRequest implements Serializable {
    private final Integer itemId;
    private final String name;
    private final Integer primaryGroupId;
    private final String subGroupName;
    private final String subGroupPictureData;
    private final Integer sellerId;
    private final BigDecimal price;
    private final String description;
    private final List<PictureResponse> pictures;
}
