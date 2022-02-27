package ee.bcs.eetsy.domain.primary_group;

import ee.bcs.eetsy.domain.sub_group.SubGroup;
import ee.bcs.eetsy.domain.sub_group.SubGroupDto;
import org.mapstruct.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PrimaryGroupMapper {
    PrimaryGroup primaryGroupDtoToPrimaryGroup(PrimaryGroupDto primaryGroupDto);

    PrimaryGroupDto primaryGroupToPrimaryGroupDto(PrimaryGroup primaryGroup);

    List<PrimaryGroupDto> primaryGroupToPrimaryGroupDto(List<PrimaryGroup> primaryGroup);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePrimaryGroupFromPrimaryGroupDto(PrimaryGroupDto primaryGroupDto, @MappingTarget PrimaryGroup primaryGroup);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "picture.id", target = "pictureId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "picture.data", target = "pictureData", qualifiedByName = "byteToString")
    PrimaryGroupResponse primaryGroupToPrimaryGroupResponse(PrimaryGroup primaryGroup);
    List<PrimaryGroupResponse> primaryGroupsToPrimaryGroupResponses(List<PrimaryGroup> primaryGroup);

    @Named("byteToString")
    public static String byteToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
