package ee.bcs.eetsy.domain.picture;

import ee.bcs.eetsy.domain.picture.item_picture.ItemPicture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Mapper
public interface PictureResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "data", source = "data", qualifiedByName = "byteToString")
    public PictureResponse pictureToPictureResponse(Picture picture);

    @Mapping(source = "picture.id", target = "id")
    @Mapping(source = "picture.data", target = "data", qualifiedByName = "byteToString")
    PictureResponse itemPictureToPictureResponse(ItemPicture itemPicture);
    List<PictureResponse> itemPicturesToPicturesResponse(List<ItemPicture> itemPicture);


    @Named("byteToString")
    public static String byteToString(byte[] bytes){
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
