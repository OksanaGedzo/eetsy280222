package ee.bcs.eetsy.domain.picture.item_picture;

import ee.bcs.eetsy.domain.picture.Picture;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ItemPictureMapper {
    @Mapping(source = "itemId", target = "item.id")
    ItemPicture itemPictureDtoToItemPicture(ItemPictureRequest itemPictureRequest);

    @Mapping(source = "item.id", target = "itemId")
    ItemPictureRequest itemPictureToItemPictureDto(ItemPicture itemPicture);


    @Mapping(source = "itemId", target = "item.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateItemPictureFromItemPictureDto(ItemPictureRequest itemPictureRequest, @MappingTarget ItemPicture itemPicture);
}
