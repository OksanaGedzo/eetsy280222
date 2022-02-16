package ee.bcs.eetsy.domain.sub_group;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SubGroupMapper {
    SubGroup subGroupDtoToSubGroup(SubGroupDto subGroupDto);

    SubGroupDto subGroupToSubGroupDto(SubGroup subGroup);

    @Mapping(target = "name", source = "subGroup.name")
    @Mapping(target = "pictureDto", source = "subGroup.picture")
    List<SubGroupResponse> subGroupToSubGroupResponse(List<SubGroup> subGroups);



    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSubGroupFromSubGroupDto(SubGroupDto subGroupDto, @MappingTarget SubGroup subGroup);
}
