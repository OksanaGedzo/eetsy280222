package ee.bcs.eetsy.domain.seller;

import ee.bcs.eetsy.domain.picture.PictureDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class SellerBasicDto implements Serializable {
    private final Integer id;
    private final String name;
}
