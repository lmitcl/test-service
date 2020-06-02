package ru.agiletech.teammate.service.presentation.presentation.hateoas;

import lombok.experimental.UtilityClass;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import ru.agiletech.teammate.service.application.teammate.TeammateDTO;
import ru.agiletech.teammate.service.presentation.presentation.TeammateResource;

@UtilityClass
public class LinksUtil {

    public void addLinks(TeammateDTO teammateDTO){
        addSelfLink(teammateDTO);
        addAllTeammatesLink(teammateDTO);
        addRoleLink(teammateDTO);
        addContactsLink(teammateDTO);
        addCredentialsLink(teammateDTO);
        addFullNameLink(teammateDTO);
    }

    private void addSelfLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .getTeammate(teammateDTO.getId()))
                .withSelfRel());
    }

    private void addAllTeammatesLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .getAllTeammates())
                .withRel("allTeammates"));
    }

    private void addRoleLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changeRole(teammateDTO.getId(),null))
                .withRel("role"));
    }

    private void addFullNameLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changeFullName(teammateDTO.getId(),null, null, null))
                .withRel("fullName"));
    }

    private void addCredentialsLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changePassword(teammateDTO.getId(),null))
                .withRel("credentials"));
    }

    private void addContactsLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changeContacts(teammateDTO.getId(),null, null))
                .withRel("contacts"));
    }

}
