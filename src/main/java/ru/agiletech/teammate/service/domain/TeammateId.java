package ru.agiletech.teammate.service.domain;

import lombok.*;
import ru.agiletech.teammate.service.domain.supertype.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeammateId implements ValueObject {

    private String id;

    public static TeammateId identifyTeammate(){
        String id = UUID.randomUUID().toString();

        return new TeammateId(id);
    }

    public static TeammateId identifyTeammateFrom(String id){
        return new TeammateId(id);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null
                || getClass() != object.getClass())
            return false;

        TeammateId teammateId = (TeammateId) object;
        return Objects.equals(id,
                teammateId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
