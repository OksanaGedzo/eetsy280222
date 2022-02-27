package ee.bcs.eetsy.domain.primary_group;

import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.picture.Picture;
import ee.bcs.eetsy.domain.picture.PictureRepository;
import ee.bcs.eetsy.domain.picture.PictureResponseMapper;
import ee.bcs.eetsy.domain.sub_group.SubGroupRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class PrimaryGroupService {

    @Resource
    private PrimaryGroupRepository primaryGroupRepository;
    @Resource
    private PrimaryGroupMapper primaryGroupMapper;
    @Resource
    private PictureResponseMapper pictureResponseMapper;
    @Resource
    private PictureRepository pictureRepository;
    @Resource
    private SubGroupRepository subGroupRepository;


    public List<PrimaryGroupResponse> findAllPrimaryGroups() {

        List<PrimaryGroup> allPrimaryGroups = primaryGroupRepository.findAll();
        List<PrimaryGroupResponse> primaryGroupDtos = primaryGroupMapper.primaryGroupsToPrimaryGroupResponses(allPrimaryGroups);

        return primaryGroupDtos;
    }


    public RequestResponse addNewPrimaryGroup(PrimaryGroupResponse primaryGroupResponse) {
        RequestResponse response = new RequestResponse();
        if (primaryGroupRepository.findByName(primaryGroupResponse.getName()).isPresent()) {
            response.setError("A primary group with that name already exists.");
        } else {
            Picture picture = new Picture();
            byte[] byteData = primaryGroupResponse.getPictureData().getBytes(StandardCharsets.UTF_8);
            picture.setData(byteData);
            picture = pictureRepository.save(picture); //reposityory.save returns the saved object with the id from the table

            PrimaryGroup primaryGroup = new PrimaryGroup();
            primaryGroup.setName(primaryGroupResponse.getName());
            primaryGroup.setPicture(picture);
            primaryGroupRepository.save(primaryGroup);
            response.setMessage("New primary group added");
        }
        return response;
    }

    public RequestResponse updatePrimaryGroup(PrimaryGroupResponse primaryGroupResponse) {
        RequestResponse response = new RequestResponse();
        Optional<PrimaryGroup> primaryGroupCheck = primaryGroupRepository.findById(primaryGroupResponse.getId());
        if (primaryGroupCheck.isEmpty()) {
            response.setError("A primary group with that id not found.");
        } else {
            PrimaryGroup primaryGroup = primaryGroupCheck.get();
            if (primaryGroupResponse.getPictureId() == 0) {
                Picture picture = new Picture();
                byte[] byteData = primaryGroupResponse.getPictureData().getBytes(StandardCharsets.UTF_8);
                picture.setData(byteData);
                picture = pictureRepository.save(picture);
                primaryGroup.setPicture(picture);
            } else {
                Picture picture = pictureRepository.findById(primaryGroupResponse.getPictureId()).get();
                primaryGroup.setPicture(picture);
            }
            primaryGroup.setName(primaryGroupResponse.getName());
            primaryGroupRepository.save(primaryGroup);
            response.setMessage("Primary Group updated.");
        }

        return response;
    }

    public RequestResponse deletePrimaryGroup(Integer id) {
        RequestResponse response = new RequestResponse();
        Optional<PrimaryGroup> primaryGroupCheck = primaryGroupRepository.findById(id);
        if (primaryGroupCheck.isEmpty()) {
            response.setError("A primary group with that id not found.");
        } else {
            primaryGroupRepository.deleteById(id);
            response.setMessage("Primary Group deleted.");
        }
        return response;
    }

    public RequestResponse countPrimaryGroupRelations(Integer id) {
        RequestResponse response = new RequestResponse();
        Integer subGroupCount = 0;
        Optional<PrimaryGroup> primaryGroupCheck = primaryGroupRepository.findById(id);
        if (primaryGroupCheck.isEmpty()) {
            response.setError("A primary group with that id not found.");
        } else {
            subGroupCount = Math.toIntExact(subGroupRepository.countSubGroupsByPrimaryGroupId(id));
            response.setMessage("CAUTION! "+subGroupCount+" sub groups and all their items will be deleted.");
        }
        return response;
    }
}
