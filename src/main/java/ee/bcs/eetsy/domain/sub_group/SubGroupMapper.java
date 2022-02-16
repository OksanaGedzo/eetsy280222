package ee.bcs.eetsy.domain.sub_group;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SubGroupMapper {
    SubGroup subGroupDtoToSubGroup(SubGroupDto subGroupDto);

    SubGroupDto subGroupToSubGroupDto(SubGroup subGroup);


    @Mapping(target = "pictureData", source = "picture.data")
    @Mapping(target = "name", source = "name")
    SubGroupResponse subGroupToSubGroupResponse(SubGroup subGroups);
    List<SubGroupResponse> subGroupToSubGroupResponses(List<SubGroup> subGroups);


    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "subGroupName", source = "name")
    @Mapping(target = "itemName", source = "item.name")
    @Mapping(target = "itemPrice", source = "item.price")
    @Mapping(target = "itemDescription", source = "item.description")
    SubGroupItemResponse toSubGroupItemResponse(SubGroup subGroups);
    List<SubGroupItemResponse> toSubGroupItemResponses(List<SubGroup> subGroups);




//    @Mapping(target = "name", source = "subGroup.name")
//    @Mapping(target = "picture", source = "subGroup.picture")
//    SubGroupResponse subGroupResponse (SubGroup subGroup);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSubGroupFromSubGroupDto(SubGroupDto subGroupDto, @MappingTarget SubGroup subGroup);
}
