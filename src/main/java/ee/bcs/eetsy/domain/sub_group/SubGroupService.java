package ee.bcs.eetsy.domain.sub_group;

import ee.bcs.eetsy.domain.picture.PictureDto;
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
        List<SubGroupResponse> subGroupsResponse = subGroupMapper.subGroupToSubGroupResponse(subGroups);
        return subGroupsResponse;

    }

}
