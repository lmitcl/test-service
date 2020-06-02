package ru.agiletech.teammate.service.application.teammate.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeRoleCommand {

    private String id;
    private String role;

}
