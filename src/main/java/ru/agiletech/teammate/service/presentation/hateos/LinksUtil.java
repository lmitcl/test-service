package ru.agiletech.teammate.service.presentation.hateos;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import ru.agiletech.teammate.service.application.TeammateDTO;
import ru.agiletech.teammate.service.presentation.TeammateResource;

public class LinksUtil {

    public static void addLinks(TeammateDTO teammateDTO){
        addSelfLink(teammateDTO);
        addAllTeammatesLink(teammateDTO);
        addNameLink(teammateDTO);
        addSurNameLink(teammateDTO);
        addPatronymicLink(teammateDTO);
        addLoginLink(teammateDTO);
        addPasswordLink(teammateDTO);
        addEmailLink(teammateDTO);
        addPhoneLink(teammateDTO);
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

    private static void addNameLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changeName(teammateDTO.getId(),null))
                .withRel("name"));
    }

    private static void addSurNameLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changeSurName(teammateDTO.getId(),null))
                .withRel("surName"));
    }

    private static void addPatronymicLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changePatronymic(teammateDTO.getId(),null))
                .withRel("patronymic"));
    }

    private static void addLoginLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changeLogin(teammateDTO.getId(),null))
                .withRel("login"));
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

    private static void addPhoneLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changePhone(teammateDTO.getId(),null))
                .withRel("phone"));
    }

    private static void addRoleLink(TeammateDTO teammateDTO){
        teammateDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TeammateResource.class)
                .changeRole(teammateDTO.getId(),null))
                .withRel("role"));
    }

}
