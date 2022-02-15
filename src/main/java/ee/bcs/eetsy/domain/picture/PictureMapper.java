package ee.bcs.eetsy.domain.picture;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PictureMapper {
    Picture pictureDtoToPicture(PictureDto pictureDto);

    PictureDto pictureToPictureDto(Picture picture);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePictureFromPictureDto(PictureDto pictureDto, @MappingTarget Picture picture);
}
