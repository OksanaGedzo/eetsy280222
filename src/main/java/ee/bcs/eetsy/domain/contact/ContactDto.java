package ee.bcs.eetsy.domain.contact;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContactDto implements Serializable {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String postIndex;
    private final String country;
    private final String phoneNumber;
    private final String email;
}
