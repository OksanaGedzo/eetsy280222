package ee.bcs.eetsy.domain.seller;

import ee.bcs.eetsy.domain.picture.PictureDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class SellerDto implements Serializable {
    private final Integer id;
    private final PictureDto logoPicture;
    private final String name;
    private final String email;
    private final String telephone;
    private final String index;
    private final String aadress;
    private final String website;
    private final Boolean validated;
}
