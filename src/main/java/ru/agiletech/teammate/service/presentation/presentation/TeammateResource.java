package ru.agiletech.teammate.service.presentation.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.agiletech.teammate.service.application.teammate.TeammateDTO;
import ru.agiletech.teammate.service.application.teammate.TeammateService;
import ru.agiletech.teammate.service.application.teammate.command.ChangeContactsCommand;
import ru.agiletech.teammate.service.application.teammate.command.ChangeFullNameCommand;
import ru.agiletech.teammate.service.application.teammate.command.ChangePasswordCommand;
import ru.agiletech.teammate.service.application.teammate.command.ChangeRoleCommand;
import ru.agiletech.teammate.service.presentation.presentation.hateoas.LinksUtil;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(value = "REST-ресурс модели участника agile команды")
public class TeammateResource {

    private final TeammateService teammateService;

    @PostMapping(value = "/teammates")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Создать участника", code = 201)
    public TeammateDTO createTeammate(@Valid @RequestBody TeammateDTO teammateDTO){
        var createdTeammate = teammateService.createTeammate(teammateDTO);
        LinksUtil.addLinks(createdTeammate);

        return createdTeammate;
    }

    @GetMapping(value = "/teammates/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Найти участника по идентификатору")
    public TeammateDTO getTeammate(@PathVariable String id){
        var teammate = teammateService.searchTeammate(id);
        LinksUtil.addLinks(teammate);

        return teammate;
    }

    @GetMapping(value = "/teammates")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Найти всех участников")
    public Set<TeammateDTO> getAllTeammates(){
        Set<TeammateDTO> teammates = teammateService.searchAllTeammates();

        if(CollectionUtils.isNotEmpty(teammates))
            teammates.forEach(LinksUtil::addLinks);

        return teammates;
    }

    @PutMapping(value = "/teammates/{id}/fullName")
    @ApiOperation(value = "Обновить ФИО участника")
    public ResponseEntity<Void> changeFullName(@PathVariable(name = "id")       String teammateId,
                                               @RequestParam                    String name,
                                               @RequestParam                    String surName,
                                               @RequestParam(required = false)  String patronymic){
        ChangeFullNameCommand command = new ChangeFullNameCommand(teammateId,
                name,
                surName,
                patronymic);

        teammateService.changeFullName(command);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/credentials")
    @ApiOperation(value = "Обновить полномочия участника")
    public ResponseEntity<Void> changePassword(@PathVariable(name = "id") String teammateId,
                                               @RequestParam              String password){
        ChangePasswordCommand command = new ChangePasswordCommand(teammateId,
                password);

        teammateService.changePassword(command);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/contacts")
    @ApiOperation(value = "Обновить контакты участника")
    public ResponseEntity<Void> changeContacts(@PathVariable(name = "id")          String teammateId,
                                               @RequestParam(required = false)     String email,
                                               @RequestParam(required = false)     String phoneNumber){
        ChangeContactsCommand command = new ChangeContactsCommand(teammateId,
                email,
                phoneNumber);

        teammateService.changeContacts(command);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/role")
    @ApiOperation(value = "Обновить роль участника")
    public ResponseEntity<Void> changeRole(@PathVariable(name = "id") String teammateId,
                                           @RequestParam              String role){
        ChangeRoleCommand command = new ChangeRoleCommand(teammateId,
                role);

        teammateService.changeRole(command);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
