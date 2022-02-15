package ee.bcs.eetsy.domain.picture;

import lombok.Data;

import java.io.Serializable;

@Data
public class PictureDto implements Serializable {
    private final String path;
}
