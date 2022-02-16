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




    public List<SubGroupResponse> findSubGroupsNameAndPicture(Integer id) {
        List<SubGroup> subGroups = subGroupRepository.findSubGroupsByPrimaryGroupId(id);
        List<SubGroupResponse> subGroupsResponse = subGroupMapper.subGroupToSubGroupResponses(subGroups);
        return subGroupsResponse;

    }

    public List<SubGroupItemResponse> findItemsBySubGroupName(String name) {
        List<SubGroup> subGroup = subGroupRepository.findByNameIgnoreCase(name);
        List<SubGroupItemResponse> subGroupItemResponses = subGroupMapper.toSubGroupItemResponses(subGroup);
        return subGroupItemResponses;
    }


}
