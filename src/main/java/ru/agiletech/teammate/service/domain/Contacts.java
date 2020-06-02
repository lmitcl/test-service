package ru.agiletech.teammate.service.domain;

import lombok.*;
import ru.agiletech.teammate.service.domain.supertype.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;


@Embeddable
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Contacts implements ValueObject {

    private String email;
    private String phoneNumber;

    static Contacts create(String email,
                           String phone){
        return new Contacts(email,
                phone);
    }

    Contacts withEmail(String email){
        return new Contacts(email ,
                this.phoneNumber);
    }

    Contacts withPhoneNumber(String phoneNumber){
        return new Contacts(this.email ,
                phoneNumber);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null
                || getClass() != object.getClass())
            return false;

        Contacts contacts = (Contacts) object;

        return Objects.equals(email, contacts.email) &&
                Objects.equals(phoneNumber, contacts.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email,
                phoneNumber);
    }

}
