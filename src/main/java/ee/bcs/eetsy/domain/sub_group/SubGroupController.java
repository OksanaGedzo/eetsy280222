package ee.bcs.eetsy.domain.sub_group;

import ee.bcs.eetsy.domain.item.ItemRepository;
import ee.bcs.eetsy.domain.picture.Picture;
import ee.bcs.eetsy.domain.picture.PictureDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController

public class SubGroupController {
    @Resource
    private SubGroupMapper subGroupMapper;

    @Resource
    private SubGroupRepository subGroupRepository;

    @Resource
    private SubGroupService subGroupService;

    @Resource
    private ItemRepository itemRepository;


    @GetMapping("/get/subgroups/by/primarygroup/id")
    @Operation(summary = "Get sub_gruppid, mis kuuluvad primary_groupisse")
    public List<SubGroupResponse> getSubGroupsByPrimaryGroupId(@RequestParam Integer id) {
        List<SubGroupResponse> response = subGroupService.findSubGroupsNameAndPicture(id);
        return response;
    }
    @GetMapping("get/items/by/subgroup/name")
    @Operation(summary = "Get toodete gruppi, mis kuulub sub_groupisse")
    public List<SubGroupItemResponse> getSubGroupItemResponse (@RequestParam String name) {
        List<SubGroupItemResponse> responses = subGroupService.findItemsBySubGroupName(name);
        return responses;
    }
//
//    @GetMapping("/get/group")
//    @Operation(summary = "Get toodete gruppi, mis kuulub sub_groupisse")
//    public SubGroupResponse getGroup(@RequestParam Integer id) {
//        SubGroupResponse response = subGroupService.findSubGroupNameAndPicture(id);
//        return response;
//    }
//

}
