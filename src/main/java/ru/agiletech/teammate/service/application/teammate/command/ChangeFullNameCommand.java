package ru.agiletech.teammate.service.application.teammate.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeFullNameCommand {

    private String id;
    private String name;
    private String surName;
    private String patronymic;

}
