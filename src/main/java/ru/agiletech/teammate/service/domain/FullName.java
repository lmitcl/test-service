package ru.agiletech.teammate.service.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FullName {

    private String name;
    private String surName;
    private String patronymic;

    static FullName create(String name,
                           String surName,
                           String patronymic){
        return new FullName(name,
                surName,
                patronymic);
    }

    FullName withName(String name){
        return new FullName(name,
                this.surName,
                this.patronymic);
    }

    FullName withSurName(String surName){
        return new FullName(this.name,
                surName,
                this.patronymic);
    }

    FullName withPatronymic(String patronymic){
        return new FullName(this.name,
                this.surName,
                patronymic);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null
                || getClass() != object.getClass())
            return false;
        FullName fullName = (FullName) object;

        return Objects.equals(name, fullName.name) &&
                Objects.equals(surName, fullName.surName) &&
                Objects.equals(patronymic, fullName.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,
                surName,
                patronymic);
    }

}
