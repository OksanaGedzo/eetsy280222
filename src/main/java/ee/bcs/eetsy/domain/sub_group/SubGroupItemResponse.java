package ee.bcs.eetsy.domain.sub_group;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubGroupItemResponse {
    private final Integer itemId;
    private final String subGroupName;
    private final String itemName;
    private final String itemPictureData;
    private final BigDecimal itemPrice;
    private final String itemDescription;
}
