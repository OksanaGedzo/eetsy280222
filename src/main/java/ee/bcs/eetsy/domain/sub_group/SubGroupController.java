package ee.bcs.eetsy.domain.sub_group;

import ee.bcs.eetsy.domain.primary_group.PrimaryGroupDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController

public class SubGroupController {
    @Resource
    private SubGroupMapper subGroupMapper;

    @Resource
    private  SubGroupRepository subGroupRepository;

    @Resource
    private SubGroupService subGroupService;

    @GetMapping("/get/all/subgroups/")
    @Operation(summary = "Get'i kõik sub_group andmed")

    public List<SubGroupDto> getAllSubGroups() {

        List<SubGroupDto> allSubGroups = subGroupService.findAllSubGroups();
        return allSubGroups;
    }

    //Oksana:otsime sub-groups listi by primary-group id.( Kõik, mis elavad ühe primary-groupi sees, nt Handcraftis)
    @GetMapping("/get/subgroups/by/primarygroup/id")
    @Operation (summary= "Get sub_gruppid, mis kuuluvad primary_groupisse")

    public List<SubGroupDto> getSubGroupsByPrimaryGroupId(int id){
       List<SubGroupDto> subGroupDtos = subGroupService.findSubGroupsByPrimaryGroupId(id);
        return subGroupDtos;
    }

    //???? Oksana:otsime sub-groupi by ühe item id? ?????
    @GetMapping("/get/subgroup/by/item/id")
    @Operation (summary= "Get sub_gruppi, millele kuulub item by valitud id")

    public SubGroupDto getSubGroupByItemId(int id){
        SubGroupDto subGroupDto = subGroupService.findSubGroupByItemId(id);
        return subGroupDto;
    }






}
