package ru.agiletech.teammate.service.presentation.hateos;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import ru.agiletech.teammate.service.application.TeammateDTO;
import ru.agiletech.teammate.service.presentation.TeammateResource;

public class LinksUtil {

    public static void addLinks(TeammateDTO teammateDTO){
        addSelfLink(teammateDTO);
        addAllTeammatesLink(teammateDTO);
        addUserNameLink(teammateDTO);
        addPasswordLink(teammateDTO);
        addEmailLink(teammateDTO);
        addRoleLink(teammateDTO);
    }

    private static void addSelfLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .getTeammate(teammateDTO.getId()))
                .withSelfRel());
    }

    private static void addAllTeammatesLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .getAllTeammates())
                .withRel("allTeammates"));
    }

    private static void addUserNameLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changeUserName(teammateDTO.getId(),null))
                .withRel("userName"));
    }

    private static void addPasswordLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changePassword(teammateDTO.getId(),null))
                .withRel("password"));
    }

    private static void addEmailLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changeEmail(teammateDTO.getId(),null))
                .withRel("email"));
    }

    private static void addRoleLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changeRole(teammateDTO.getId(),null))
                .withRel("role"));
    }

}
