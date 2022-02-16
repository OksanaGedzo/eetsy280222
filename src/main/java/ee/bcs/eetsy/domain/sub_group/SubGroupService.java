package ee.bcs.eetsy.domain.sub_group;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SubGroupService {

    @Resource
    private SubGroupRepository subGroupRepository;

    @Resource
    private SubGroupMapper subGroupMapper;

    public List<SubGroupDto> findAllSubGroups() {

        List<SubGroup> allSubGroups = subGroupRepository.findAll();
        List<SubGroupDto> subGroupDtos = subGroupMapper.subGroupsToSubGroupsDto(allSubGroups);
        return subGroupDtos;
    }
// Oksana: otsime sub-groups by primary-group id.( kõik, mis elavad ühe primary-groupi sees, nt Handcraftis)
    public List<SubGroupDto> findSubGroupsByPrimaryGroupId( int id) {
        List<SubGroup> subGroups = subGroupRepository.findSubGroupsByPrimaryGroupId( id);
        List<SubGroupDto> subGroupsDtos = subGroupMapper.subGroupsToSubGroupsDto(subGroups);
        return subGroupsDtos;
    }





//    // Oksana: otsime sub-group by item id.(  mis elab ühe sub-groupi sees, nt ForYourHead)
//    public SubGroupDto findSubGroupByItemId ( int id) {
//        SubGroup subGroupByItemId = subGroupRepository.findSubGroupByItemId(id);
//        SubGroupDto subGroupByItemIdDto = subGroupMapper.subGroupToSubGroupDto(subGroupByItemId);
//        return subGroupByItemIdDto;
//    }
}
