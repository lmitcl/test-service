package ru.agiletech.teammate.service.application.teammate.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeContactsCommand {

    private String id;
    private String email;
    private String phoneNumber;

}
