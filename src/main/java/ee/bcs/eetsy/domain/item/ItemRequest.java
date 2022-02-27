package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.picture.PictureDto;
import ee.bcs.eetsy.domain.picture.PictureResponse;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemRequest implements Serializable {
    private Integer itemId;
    private String name;
    private String subGroupName;
    private Integer sellerId;
    private String sellerName;
    private BigDecimal price;
    private String description;
    private List<PictureResponse> pictures;
}
