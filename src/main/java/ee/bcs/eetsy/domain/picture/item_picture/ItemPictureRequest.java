package ee.bcs.eetsy.domain.picture.item_picture;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemPictureRequest implements Serializable {
    private final Integer id;
    private final Integer itemId;
    private final String pictureData;
}
