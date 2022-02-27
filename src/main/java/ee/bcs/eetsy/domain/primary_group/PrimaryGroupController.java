package ee.bcs.eetsy.domain.primary_group;

import ee.bcs.eetsy.domain.RequestResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
//@RequestMapping("/")
public class PrimaryGroupController {

    @Resource
    PrimaryGroupRepository primaryGroupRepository;

    @Resource
    PrimaryGroupService primaryGroupService;


    @GetMapping("/get/all/primarygroups/")
    @Operation(summary = "Get'i kõik primary_group andmed")
    public List<PrimaryGroupResponse> getAllPrimaryGroups() {
        return primaryGroupService.findAllPrimaryGroups();
    }

    @PostMapping("/add/primarygroup/")
    @Operation(summary = "Add new primary group with image data")
    public RequestResponse getAllPrimaryGroups(@RequestBody PrimaryGroupResponse primaryGroupResponse) {
        return primaryGroupService.addNewPrimaryGroup(primaryGroupResponse);
    }

    @PutMapping("/update/primarygroup/")
    @Operation(summary = "Update an existing primary group")
    public RequestResponse updatePrimaryGroup(@RequestBody PrimaryGroupResponse primaryGroupResponse) {
        return primaryGroupService.updatePrimaryGroup(primaryGroupResponse);
    }

    @DeleteMapping("/delete/primarygroup/")
    @Operation(summary = "Delete an existing primary group")
    public RequestResponse deletePrimaryGroup(@RequestParam Integer id) {
        return primaryGroupService.deletePrimaryGroup(id);
    }

    @GetMapping("/get/primarygroup/relation/count")
    @Operation(summary = "Gets amount of entries in relation with given primary group id")
    public RequestResponse countPrimaryGroupRelations(@RequestParam Integer id) {
        return primaryGroupService.countPrimaryGroupRelations(id);
    }
}
