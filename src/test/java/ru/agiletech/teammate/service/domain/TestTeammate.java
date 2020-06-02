package ru.agiletech.teammate.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.agiletech.teammate.service.Application;
import ru.agiletech.teammate.service.domain.task.TaskId;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class TestTeammate {

    private Teammate teammate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreateTeammate(){
        this.teammate = createTeammate();

        TeammateSnapshot snapshot = teammate.makeSnapshot();

        assertNotNull(teammate.teammateId());
        assertNotNull(snapshot.getEmail());
        assertNotNull(snapshot.getPhoneNumber());
        assertNotNull(snapshot.getName());
        assertNotNull(snapshot.getSurName());
        assertNotNull(snapshot.getPatronymic());
        assertNotNull(snapshot.getLogin());
        assertNotNull(snapshot.getPassword());
        assertNotNull(snapshot.getRole());
    }


    @Test
    public void testChangePassword(){
        this.teammate = createTeammate();
        String newEncodedPassword = passwordEncoder.encode("newPassword");

        teammate.changePassword(newEncodedPassword);

        TeammateSnapshot snapshot = teammate.makeSnapshot();

        assertNotNull(teammate.teammateId());
        assertNotNull(snapshot.getEmail());
        assertNotNull(snapshot.getPhoneNumber());
        assertNotNull(snapshot.getName());
        assertNotNull(snapshot.getSurName());
        assertNotNull(snapshot.getPatronymic());
        assertNotNull(snapshot.getLogin());
        assertNotNull(snapshot.getPassword());
        assertNotNull(snapshot.getRole());
    }

    @Test
    public void testChangeRole(){
        this.teammate = createTeammate();

        Teammate.UserRole userRole = Teammate.UserRole.ADMINISTRATOR;

        teammate.changeRole(userRole);

        TeammateSnapshot snapshot = teammate.makeSnapshot();

        assertNotNull(teammate.teammateId());
        assertNotNull(snapshot.getEmail());
        assertNotNull(snapshot.getPhoneNumber());
        assertNotNull(snapshot.getName());
        assertNotNull(snapshot.getSurName());
        assertNotNull(snapshot.getPatronymic());
        assertNotNull(snapshot.getLogin());
        assertNotNull(snapshot.getPassword());
        assertNotNull(snapshot.getRole());
    }

    @Test
    public void testContacts(){
        this.teammate = createTeammate();

        String email = "newtest@mail.ru";
        String phoneNumber = "89123123122";

        teammate.changeContacts(email, phoneNumber);

        TeammateSnapshot snapshot = teammate.makeSnapshot();

        assertNotNull(teammate.teammateId());
        assertNotNull(snapshot.getEmail());
        assertNotNull(snapshot.getPhoneNumber());
        assertNotNull(snapshot.getName());
        assertNotNull(snapshot.getSurName());
        assertNotNull(snapshot.getPatronymic());
        assertNotNull(snapshot.getLogin());
        assertNotNull(snapshot.getPassword());
        assertNotNull(snapshot.getRole());
    }

    @Test
    public void testChangeFullName(){
        this.teammate = createTeammate();

        String name = "NewName";
        String surName = "NewSurName";
        String patronymic = "NewPatronymic";

        teammate.changeFullName(name,
                surName,
                patronymic);

        TeammateSnapshot snapshot = teammate.makeSnapshot();

        assertNotNull(teammate.teammateId());
        assertNotNull(snapshot.getEmail());
        assertNotNull(snapshot.getPhoneNumber());
        assertNotNull(snapshot.getName());
        assertNotNull(snapshot.getSurName());
        assertNotNull(snapshot.getPatronymic());
        assertNotNull(snapshot.getLogin());
        assertNotNull(snapshot.getPassword());
        assertNotNull(snapshot.getRole());
    }

    @Test
    public void testOpenTask(){
        this.teammate = createTeammate();

        String rawTaskId = UUID.randomUUID().toString();

        TaskId taskId = TaskId.identifyTaskFrom(rawTaskId);
        teammate.openTask(taskId);

        TeammateSnapshot snapshot = teammate.makeSnapshot();

        assertNotNull(teammate.teammateId());
        assertNotNull(snapshot.getEmail());
        assertNotNull(snapshot.getPhoneNumber());
        assertNotNull(snapshot.getName());
        assertNotNull(snapshot.getSurName());
        assertNotNull(snapshot.getPatronymic());
        assertNotNull(snapshot.getLogin());
        assertNotNull(snapshot.getPassword());
        assertNotNull(snapshot.getRole());
        assertNotNull(snapshot.getOpenTasks());
    }

    private Teammate createTeammate(){
        String encodedPassword = passwordEncoder.encode("password");

        return new Teammate.Builder()
                .withContacts("test@mail.ru",
                        "89123123123")
                .withCredentials("login",
                        encodedPassword)
                .withFullName("Name",
                        "SurName",
                        "Patronymic")
                .withRole("USER")
                .build();
    }

}
