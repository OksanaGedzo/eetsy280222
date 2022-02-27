package ee.bcs.eetsy.domain.sub_group;

import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.picture.item_picture.ItemPictureRepository;
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
    @Resource
    private ItemPictureRepository itemPictureRepository;

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
        return subGroupMapper.subGroupToSubGroupResponses(subGroups);
    }

    public List<SubGroupItemResponse> findItemsBySubGroupName(String name) {
        List<SubGroup> subGroups = subGroupRepository.findListByNameIgnoreCase(name);
        for (SubGroup sub : subGroups) {
            Integer itemId = sub.getItem().getId();
            sub.setPicture(itemPictureRepository.findByItemId(itemId).get(0).getPicture());
        }
        return subGroupMapper.toSubGroupItemResponses(subGroups);
    }

    public RequestResponse addNewSubGroup(SubGroupRequest subGroupRequest) {
        RequestResponse response = new RequestResponse();
        return null;
    }
}
