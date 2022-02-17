package ee.bcs.eetsy.domain.user;

import ee.bcs.eetsy.domain.contact.Contact;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Integer id;
    private final Contact contact;
    private final String userName;
    private final String password;
}
