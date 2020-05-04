package ru.agiletech.teammate.service.domain.teammate;

import lombok.*;
import ru.agiletech.teammate.service.domain.supertype.ValueObject;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Contacts implements ValueObject {

    private String email;
    private String phone;

    static Contacts create(String email,
                           String phone){
        return new Contacts(email,
                phone);
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void changePhone(String phone){
        this.phone = phone;
    }


}
