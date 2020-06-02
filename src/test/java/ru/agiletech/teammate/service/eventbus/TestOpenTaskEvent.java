package ru.agiletech.teammate.service.eventbus;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.agiletech.teammate.service.Application;
import ru.agiletech.teammate.service.application.teammate.TeammateDTO;
import ru.agiletech.teammate.service.application.teammate.TeammateService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@DirtiesContext
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class TestOpenTaskEvent {

    private static final String ASSIGNEE_ID     = "assigneeId";
    private static final String TASK_ID         = "taskId";
    private static final String EVENT_NAME      = "eventName";

    @Autowired
    private Sink sink;

    @Autowired
    private TeammateService teammateService;

    @Test
    public void testOpenTaskForTeammate(){
        TeammateDTO teammateDTO = createTeammate();

        String taskId = UUID.randomUUID().toString();

        Map<String, Object> event = new HashMap<>();

        event.put(ASSIGNEE_ID, teammateDTO.getId());
        event.put(TASK_ID, taskId);

        sink.input()
                .send(MessageBuilder
                        .withPayload(event)
                        .setHeader(EVENT_NAME, "ru.agiletech.task.service.domain.task.TeammateAssigned")
                        .build());
    }


    private TeammateDTO createTeammate(){
        TeammateDTO teammateDTO = new TeammateDTO();

        teammateDTO.setEmail("test@mail.ru");
        teammateDTO.setLogin("login");
        teammateDTO.setPassword("password");
        teammateDTO.setName("Name");
        teammateDTO.setSurName("SurName");
        teammateDTO.setPatronymic("Patronymic");
        teammateDTO.setRole("USER");
        teammateDTO.setPhoneNumber("89261765544");

        return teammateService.createTeammate(teammateDTO);
    }

}
