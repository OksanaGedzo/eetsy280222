package ee.bcs.eetsy.domain.picture;

import lombok.Data;

import java.io.Serializable;

@Data
public class PictureResponse implements Serializable {
    private final int id;
    private final String data;
}
