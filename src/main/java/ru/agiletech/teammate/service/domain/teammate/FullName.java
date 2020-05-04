package ru.agiletech.teammate.service.domain.teammate;

import lombok.*;
import ru.agiletech.teammate.service.domain.supertype.ValueObject;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FullName implements ValueObject {

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

    public void changeName(String name){
        this.name = name;
    }

    public void changeSurName(String surName){
        this.surName = surName;
    }

    public void changePatronymic(String patronymic){
        this.patronymic = patronymic;
    }

}
