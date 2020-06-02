package ru.agiletech.teammate.service.application.teammate;

import ru.agiletech.teammate.service.application.teammate.command.ChangeContactsCommand;
import ru.agiletech.teammate.service.application.teammate.command.ChangeFullNameCommand;
import ru.agiletech.teammate.service.application.teammate.command.ChangePasswordCommand;
import ru.agiletech.teammate.service.application.teammate.command.ChangeRoleCommand;

import java.util.Set;

public interface TeammateService {

    TeammateDTO createTeammate(TeammateDTO teammateDTO);

    void changePassword(ChangePasswordCommand command);

    void changeContacts(ChangeContactsCommand command);

    void changeFullName(ChangeFullNameCommand command);

    void changeRole(ChangeRoleCommand command);

    TeammateDTO searchTeammate(String rawTeammateId);

    Set<TeammateDTO> searchAllTeammates();

}
