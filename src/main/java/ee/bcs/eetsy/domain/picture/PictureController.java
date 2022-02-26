package ee.bcs.eetsy.domain.picture;

import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.picture.item_picture.ItemPictureRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PictureController {

    @Resource
    PictureService pictureService;


    @PostMapping("/upload/image")
    public RequestResponse uploadImage (@RequestBody ItemPictureRequest itemPictureRequest) {
        RequestResponse response = pictureService.saveImageToDataBase(itemPictureRequest);
        return response;
    }

    @GetMapping("/receive/image")
    public PictureResponse uploadImage () {
        PictureResponse lastImageFromDatabase = pictureService.getLastImageFromDatabase();
        return lastImageFromDatabase;
    }

    @GetMapping("/get/item/pictures")
    public List<PictureResponse> getItemImagesByItemId (@RequestParam Integer id) {
        List<PictureResponse> pictures = pictureService.getImagesByItemId(id);
        return pictures;
    }

    @DeleteMapping("/delete/item/picture")
    public RequestResponse deleteImageByImageId (@RequestParam Integer id) {
        RequestResponse requestResponse = pictureService.deleteImageByImageId(id);
        return requestResponse;
    }

}
