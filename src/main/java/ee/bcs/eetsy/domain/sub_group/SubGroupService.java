package ee.bcs.eetsy.domain.sub_group;

import ee.bcs.eetsy.domain.primary_group.PrimaryGroup;
import ee.bcs.eetsy.domain.primary_group.PrimaryGroupDto;
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
}
