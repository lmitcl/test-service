package ru.agiletech.teammate.service.application;

import ru.agiletech.teammate.service.domain.teammate.Teammate;

import java.util.Set;

public interface TeammateService {

    TeammateDTO createTeammate(TeammateDTO teammateDTO);

    void changeUserName(String rawTeammateId,
                        String rawUserName);

    void changePassword(String rawTeammateId,
                        String rawPassword);

    void changeEmail(String rawTeammateId,
                     String rawEmail);

    void changeRole(String rawTeammateId,
                    String rawRole);

    TeammateDTO searchTeammate(String rawTeammateId);

    Set<TeammateDTO> searchAllTeammates();

//    TeammateDTO deleteTeammate(String rawTeammateId);


}
