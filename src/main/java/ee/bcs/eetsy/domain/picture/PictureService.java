package ee.bcs.eetsy.domain.picture;

import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.item.Item;
import ee.bcs.eetsy.domain.item.ItemDto;
import ee.bcs.eetsy.domain.item.ItemRepository;
import ee.bcs.eetsy.domain.picture.item_picture.ItemPicture;
import ee.bcs.eetsy.domain.picture.item_picture.ItemPictureMapper;
import ee.bcs.eetsy.domain.picture.item_picture.ItemPictureRepository;
import ee.bcs.eetsy.domain.picture.item_picture.ItemPictureRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@Service
public class PictureService {

    @Resource
    PictureRepository pictureRepository;
    @Resource
    PictureResponseMapper pictureResponseMapper;
    @Resource
    ItemRepository itemRepository;
    @Resource
    ItemPictureRepository itemPictureRepository;

    public List<PictureResponse> getImagesByItemId(Integer id) {
        List<ItemPicture> itemPictures = itemPictureRepository.findByItemId(id);
        //mapping itempicture to picture
        List<PictureResponse> pictures = pictureResponseMapper.itemPicturesToPicturesResponse(itemPictures);
        return pictures;
    }

    public RequestResponse saveImageToDataBase(ItemPictureRequest request) {
        RequestResponse requestResponse = new RequestResponse();
        String pictureData = request.getPictureData();
        byte[] byteData = pictureData.getBytes(StandardCharsets.UTF_8);
        Picture picture = new Picture();
        picture.setData(byteData);
        pictureRepository.save(picture);
        requestResponse.setMessage("Image added to database.");

        //create linked item_picture entry
        ItemPicture itemPicture = new ItemPicture();
        itemPicture.setPicture(picture);
        itemPicture.setItem(itemRepository.getById(request.getItemId()));
        return requestResponse;
    }

    public PictureResponse getLastImageFromDatabase() {
        Picture picture = pictureRepository.findFirstByOrderByIdDesc();
        PictureResponse pictureResponse = pictureResponseMapper.pictureToPictureResponse(picture);
        return pictureResponse;
    }
}
