package ee.bcs.eetsy.domain.primary_group;

import ee.bcs.eetsy.domain.sub_group.SubGroup;
import ee.bcs.eetsy.domain.sub_group.SubGroupDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PrimaryGroupMapper {
    PrimaryGroup primaryGroupDtoToPrimaryGroup(PrimaryGroupDto primaryGroupDto);

    PrimaryGroupDto primaryGroupToPrimaryGroupDto(PrimaryGroup primaryGroup);

    List<PrimaryGroupDto> primaryGroupToPrimaryGroupDto(List<PrimaryGroup> primaryGroup);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePrimaryGroupFromPrimaryGroupDto(PrimaryGroupDto primaryGroupDto, @MappingTarget PrimaryGroup primaryGroup);
}
