package ee.bcs.eetsy.domain.sub_group;

import lombok.Data;

@Data
public class SubGroupRequest {
    private final Integer id;
    private final Integer primaryGroupId;
    private final Integer pictureId;
    private final byte[] pictureData;
    private final String name;
}
