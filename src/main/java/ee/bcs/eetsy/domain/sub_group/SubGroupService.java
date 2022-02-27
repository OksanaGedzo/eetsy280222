package ee.bcs.eetsy.domain.sub_group;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class SubGroupService {

    @Resource
    private SubGroupRepository subGroupRepository;

    @Resource
    private SubGroupMapper subGroupMapper;


    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public List<SubGroupResponse> findSubGroupsNameAndPicture(Integer id) {
        List<SubGroup> subGroups = subGroupRepository.findSubGroupsByPrimaryGroupId(id);
        //could not figure out how to find all columns with value 'id' but where all column 'name''s are distinct
        //underlying problem with the way table design, had to resort to brute force filtering
        subGroups = subGroups
                .stream()
                .filter(distinctByKey(SubGroup::getName))
                .collect(Collectors.toList());

        List<SubGroupResponse> subGroupsResponse = subGroupMapper.subGroupToSubGroupResponses(subGroups);
        return subGroupsResponse;
    }


    public List<SubGroupItemResponse> findItemsBySubGroupName(String name) {
        List<SubGroup> subGroup = subGroupRepository.findListByNameIgnoreCase(name);
        List<SubGroupItemResponse> subGroupItemResponses = subGroupMapper.toSubGroupItemResponses(subGroup);
        return subGroupItemResponses;
    }


}
