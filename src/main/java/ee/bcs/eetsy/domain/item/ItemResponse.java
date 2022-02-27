package ee.bcs.eetsy.domain.item;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ItemResponse implements Serializable {
    private Integer itemId;
    private String name;
    private BigDecimal price;
    private String description;
    private String pictureData;
}
