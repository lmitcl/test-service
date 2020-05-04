package ru.agiletech.teammate.service.application;

import ru.agiletech.teammate.service.domain.teammate.Teammate;

import java.util.Set;

public interface TeammateService {

    TeammateDTO createTeammate(TeammateDTO teammateDTO);

    void changeName(String rawTeammateId,
                    String rawName);

    void changeSurName(String rawTeammateId,
                       String rawSurName);

    void changePatronymic(String rawTeammateId,
                          String rawPatronymic);

    void changeLogin(String rawTeammateId,
                     String rawLogin);

    void changePassword(String rawTeammateId,
                        String rawPassword);

    void changeEmail(String rawTeammateId,
                     String rawEmail);

    void changePhone(String rawTeammateId,
                     String rawPhone);

    void changeRole(String rawTeammateId,
                    String rawRole);

    TeammateDTO searchTeammate(String rawTeammateId);

    Set<TeammateDTO> searchAllTeammates();

//    TeammateDTO deleteTeammate(String rawTeammateId);


}
