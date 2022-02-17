package ee.bcs.eetsy.domain.role;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {
    Role roleDtoToRole(RoleDto roleDto);

    RoleDto roleToRoleDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoleFromRoleDto(RoleDto roleDto, @MappingTarget Role role);
}
