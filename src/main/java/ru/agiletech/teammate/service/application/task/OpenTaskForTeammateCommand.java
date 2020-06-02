package ru.agiletech.teammate.service.application.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OpenTaskForTeammateCommand implements Command{

    private String taskId;
    private String teammateId;

}
