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
public class Credentials implements ValueObject {

    private String login;
    private String password;

    static Credentials create(String login,
                              String password){
        return new Credentials(login,
                password);
    }

    Credentials withPassword(String password){
        return new Credentials(this.login,
                password);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null
                || getClass() != object.getClass())
            return false;

        Credentials that = (Credentials) object;

        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login,
                password);
    }

}
