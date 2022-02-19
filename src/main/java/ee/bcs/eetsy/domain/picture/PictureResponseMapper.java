package ee.bcs.eetsy.domain.picture;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Mapper
public interface PictureResponseMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "data", source = "data", qualifiedByName = "byteToString")
    public PictureResponse pictureToPictureResponse(Picture picture);

    @Named("byteToString")
    public static String byteToString(byte[] bytes){
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
