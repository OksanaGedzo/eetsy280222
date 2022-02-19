package ee.bcs.eetsy.domain.picture;

import ee.bcs.eetsy.domain.item.Item;
import ee.bcs.eetsy.domain.item.ItemDto;
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

    public void saveImageToDataBase(String stringData) {
        byte[] byteData = stringData.getBytes(StandardCharsets.UTF_8);
        Picture picture = new Picture();
        picture.setData(byteData);
        pictureRepository.save(picture);
    }

    public PictureResponse getLastImageFromDatabase() {
        Picture picture = pictureRepository.findFirstByOrderByIdDesc();
        PictureResponse pictureResponse = pictureResponseMapper.pictureToPictureResponse(picture);
        return pictureResponse;
    }
}
