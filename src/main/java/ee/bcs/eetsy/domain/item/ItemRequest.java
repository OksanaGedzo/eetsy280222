package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.picture.PictureDto;
import ee.bcs.eetsy.domain.picture.PictureResponse;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemRequest implements Serializable {
    private final Integer itemId;
    private final String name;
    private final String subGroupName;
    private final Integer sellerId;
    private final BigDecimal price;
    private final String description;
    private final List<PictureResponse> pictures;
}
