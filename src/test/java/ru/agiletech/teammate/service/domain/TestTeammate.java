package ru.agiletech.teammate.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.agiletech.teammate.service.Application;
import ru.agiletech.teammate.service.domain.teammate.Teammate;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class TestTeammate {

    private Teammate teammate;

    @Test
    public void testCreateTeammate(){
        this.teammate = createTeammate();

        assertNotNull(teammate.teammateId());
        assertNotNull(teammate.role());
    }


//    @Test
//    public void testChangeUserName(){
//        this.teammate = createTeammate();
//
//        Teammate. userRole = Teammate.UserRole.ADMINISTRATOR;
//
//        teammate.changeRole(userRole);
//
//        assertNotNull(teammate.teammateId());
//    }

    @Test
    public void testChangeRole(){
        this.teammate = createTeammate();

        Teammate.UserRole userRole = Teammate.UserRole.ADMINISTRATOR;

        teammate.changeRole(userRole);

        assertNotNull(teammate.teammateId());
    }

    private Teammate createTeammate(){
        String userName = "ivan13test";
        String name = "Ivan";
        String surName = "Ivanov";
        String patronymic = "Ivanich";
        String password = "12345";
        String email = "Ivan@mail.ru";

        return Teammate.create(userName,
                name,
                surName,
                patronymic,
                password,
                email);
    }
}
