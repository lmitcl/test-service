package ru.agiletech.teammate.service.presentation;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.agiletech.teammate.service.application.TeammateDTO;
import ru.agiletech.teammate.service.application.TeammateService;
import ru.agiletech.teammate.service.presentation.hateos.LinksUtil;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeammateResource {

    private final TeammateService teammateService;

    @PostMapping(value = "/teammates")
    @ResponseStatus(HttpStatus.CREATED)
    public TeammateDTO createTeammate(@Valid @RequestBody TeammateDTO teammateDTO){
        var createdTeammate = teammateService.createTeammate(teammateDTO);
        LinksUtil.addLinks(createdTeammate);

        return createdTeammate;
    }

    @GetMapping(value = "/teammates/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeammateDTO getTeammate(@PathVariable String id){
        var teammate = teammateService.searchTeammate(id);
        LinksUtil.addLinks(teammate);

        return teammate;
    }

    @GetMapping(value = "/teammates")
    @ResponseStatus(HttpStatus.OK)
    public Set<TeammateDTO> getAllTeammates(){
        Set<TeammateDTO> teammates = teammateService.searchAllTeammates();

        if(CollectionUtils.isNotEmpty(teammates))
            teammates.forEach(LinksUtil::addLinks);

        return teammates;
    }

    @PutMapping(value = "/teammates/{id}/name")
    public ResponseEntity<Void> changeName(@PathVariable(name = "id") String teammateId,
                                           @RequestParam              String name){
        teammateService.changeName(teammateId, name);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/surName")
    public ResponseEntity<Void> changeSurName(@PathVariable(name = "id") String teammateId,
                                              @RequestParam              String surName){
        teammateService.changeSurName(teammateId, surName);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/patronymic")
    public ResponseEntity<Void> changePatronymic(@PathVariable(name = "id") String teammateId,
                                                 @RequestParam              String patronymic){
        teammateService.changePatronymic(teammateId, patronymic);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/login")
    public ResponseEntity<Void> changeLogin(@PathVariable(name = "id") String teammateId,
                                            @RequestParam              String login){
        teammateService.changeLogin(teammateId, login);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable(name = "id") String teammateId,
                                               @RequestParam              String password){
        teammateService.changePassword(teammateId, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/email")
    public ResponseEntity<Void> changeEmail(@PathVariable(name = "id") String teammateId,
                                            @RequestParam              String email){
        teammateService.changeEmail(teammateId, email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/phone")
    public ResponseEntity<Void> changePhone(@PathVariable(name = "id") String teammateId,
                                            @RequestParam              String phone){
        teammateService.changePhone(teammateId, phone);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/role")
    public ResponseEntity<Void> changeRole(@PathVariable(name = "id") String teammateId,
                                           @RequestParam              String role){
        teammateService.changeRole(teammateId, role);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
