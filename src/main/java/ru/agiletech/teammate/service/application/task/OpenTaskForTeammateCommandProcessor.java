package ru.agiletech.teammate.service.application.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agiletech.teammate.service.domain.Teammate;
import ru.agiletech.teammate.service.domain.TeammateId;
import ru.agiletech.teammate.service.domain.TeammateRepository;
import ru.agiletech.teammate.service.domain.task.TaskId;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenTaskForTeammateCommandProcessor
        implements CommandProcessor<OpenTaskForTeammateCommand> {

    private final TeammateRepository teammateRepository;

    @Override
    @Transactional
    public void process(OpenTaskForTeammateCommand command) {
        log.info("Open task for teammate");

        TeammateId teammateId = TeammateId.identifyTeammateFrom(command.getTeammateId());
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        TaskId taskId = TaskId.identifyTaskFrom(command.getTaskId());

        teammate.openTask(taskId);

        log.info("Task id {} has been open for teammate {}", command.getTaskId(),
                command.getTeammateId());

        teammateRepository.save(teammate);
        log.info("Teammate {} has been saved", command.getTeammateId());
    }

}
