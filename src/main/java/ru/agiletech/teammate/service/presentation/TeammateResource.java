package ru.agiletech.teammate.service.presentation;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.agiletech.teammate.service.application.TeammateDTO;
import ru.agiletech.teammate.service.application.TeammateService;
import ru.agiletech.teammate.service.domain.teammate.Teammate;
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

    @PutMapping(value = "/teammates/{id}/username")
    public ResponseEntity<Void> changeUserName(@PathVariable(name = "id") String teammateId,
                                               @RequestParam              String userName){
        teammateService.changeUserName(teammateId, userName);

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
        teammateService.changeUserName(teammateId, email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/teammates/{id}/role")
    public ResponseEntity<Void> changeRole(@PathVariable(name = "id") String teammateId,
                                           @RequestParam              String role){
        teammateService.changeUserName(teammateId, role);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
