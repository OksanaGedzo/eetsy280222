package ee.bcs.eetsy.domain.picture;

import ee.bcs.eetsy.domain.item.ItemDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class PictureController {

    @Resource
    PictureService pictureService;

    @PostMapping("/upload/image")
    public String uploadImage (@RequestBody Map<String, String> pictureRequest) {
        String pictureData = pictureRequest.get("data");
        pictureService.saveImageToDataBase(pictureData);
        return "Picture data received! Maybe!";
    }

    @GetMapping("/receive/image")
    public PictureResponse uploadImage () {
        PictureResponse lastImageFromDatabase = pictureService.getLastImageFromDatabase();
        return lastImageFromDatabase;
    }


}
