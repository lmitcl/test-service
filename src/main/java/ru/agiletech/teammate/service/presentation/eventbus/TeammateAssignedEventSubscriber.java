package ru.agiletech.teammate.service.presentation.eventbus;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import ru.agiletech.teammate.service.application.task.CommandProcessor;
import ru.agiletech.teammate.service.application.task.OpenTaskForTeammateCommand;

import javax.validation.Valid;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TeammateAssignedEventSubscriber implements EventSubscriber<Map<String, Object>> {

    private static final String TASK_ID         = "taskId";
    private static final String ASSIGNEE_ID     = "assigneeId";

    private final CommandProcessor<OpenTaskForTeammateCommand> openTaskForTeammateCommandProcessor;

    @Override
    @StreamListener(value = Sink.INPUT,
            condition = "headers['eventName'] == 'ru.agiletech.task.service.domain.task.TeammateAssigned'")
    public void onEvent(@Valid Map<String, Object> serializedEvent) {
        String teammateId = (String) serializedEvent.get(ASSIGNEE_ID);
        String taskId = (String) serializedEvent.get(TASK_ID);

        var command = new OpenTaskForTeammateCommand(taskId,
                teammateId);

        openTaskForTeammateCommandProcessor.process(command);
    }

}
