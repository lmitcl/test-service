package ru.agiletech.teammate.service.domain.teammate;

import lombok.*;
import ru.agiletech.teammate.service.domain.supertype.ValueObject;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Credential implements ValueObject {

    private String login;
    private String password;

    static Credential create(String login,
                             String password){
        return new Credential(login,
                password);
    }


    public void changeLogin(String login){
        this.login = login;
    }

    public void changePassword(String password){
        this.password = password;
    }


}
