package ru.agiletech.teammate.service.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.agiletech.teammate.service.domain.teammate.FullName;
import ru.agiletech.teammate.service.domain.teammate.Teammate;
import ru.agiletech.teammate.service.domain.teammate.TeammateId;
import ru.agiletech.teammate.service.domain.teammate.TeammateRepository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeammateServiceImpl implements TeammateService {

    private final TeammateRepository teammateRepository;
    private final TeammateAssembler teammateAssembler;


    @Override
    @Transactional
    public TeammateDTO createTeammate(TeammateDTO rawTeammate) {
        log.info("Create teammate");
        Teammate teammate = Teammate.create(rawTeammate.getName(),
                        rawTeammate.getSurName(),
                        rawTeammate.getPatronymic(),
                        rawTeammate.getLogin(),
                        rawTeammate.getPassword(),
                        rawTeammate.getEmail(),
                        rawTeammate.getPhone());

        String id = teammate.teammateId();

        log.info("Teammate with id {} has been created", id);
        teammateRepository.save(teammate);

        log.info("Teammate with id {} has been saved", id);

        return teammateAssembler.writeDTO(teammate);
    }

    @Override
    @Transactional
    public void changeName(String rawTeammateId, String rawName) {
        log.info("Change name for teammate with id {}", rawTeammateId);

        TeammateId teammateId = TeammateId.identifyTeammateFrom(rawTeammateId);
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        teammate.changeName(rawName);

        log.info("Name has been changed for teammate with id {}", rawTeammateId);

        teammateRepository.save(teammate);

        log.info("Teammate with id {} has been saved", rawTeammateId);

    }

    @Override
    @Transactional
    public void changeSurName(String rawTeammateId, String rawSurName) {
        log.info("Change surName for teammate with id {}", rawTeammateId);

        TeammateId teammateId = TeammateId.identifyTeammateFrom(rawTeammateId);
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        teammate.changeSurName(rawSurName);

        log.info("SurName has been changed for teammate with id {}", rawTeammateId);

        teammateRepository.save(teammate);

        log.info("Teammate with id {} has been saved", rawTeammateId);

    }

    @Override
    @Transactional
    public void changePatronymic(String rawTeammateId, String rawPatronymic) {
        log.info("Change patronymic for teammate with id {}", rawTeammateId);

        TeammateId teammateId = TeammateId.identifyTeammateFrom(rawTeammateId);
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        teammate.changePatronymic(rawPatronymic);

        log.info("Patronymic has been changed for teammate with id {}", rawTeammateId);

        teammateRepository.save(teammate);

        log.info("Teammate with id {} has been saved", rawTeammateId);
    }

    @Override
    @Transactional
    public void changeLogin(String rawTeammateId, String rawLogin) {
        log.info("Change login for teammate with id {}", rawTeammateId);

        TeammateId teammateId = TeammateId.identifyTeammateFrom(rawTeammateId);
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        teammate.changeLogin(rawLogin);

        log.info("Login has been changed for teammate with id {}", rawTeammateId);

        teammateRepository.save(teammate);

        log.info("Teammate with id {} has been saved", rawTeammateId);
    }

    @Override
    @Transactional
    public void changePassword(String rawTeammateId, String rawPassword) {
        log.info("Change password for teammate with id {}", rawTeammateId);

        TeammateId teammateId = TeammateId.identifyTeammateFrom(rawTeammateId);
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        teammate.changePassword(rawPassword);

        log.info("Password has been changed for teammate with id {}", rawTeammateId);

        teammateRepository.save(teammate);

        log.info("Teammate with id {} has been saved", rawTeammateId);

    }

    @Override
    @Transactional
    public void changeEmail(String rawTeammateId, String rawEmail) {
        log.info("Change email for teammate with id {}", rawTeammateId);

        TeammateId teammateId = TeammateId.identifyTeammateFrom(rawTeammateId);
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        teammate.changeEmail(rawEmail);

        log.info("Email has been changed for teammate with id {}", rawTeammateId);

        teammateRepository.save(teammate);

        log.info("Teammate with id {} has been saved", rawTeammateId);
    }

    @Override
    public void changePhone(String rawTeammateId, String rawPhone) {
        log.info("Change phone for teammate with id {}", rawTeammateId);

        TeammateId teammateId = TeammateId.identifyTeammateFrom(rawTeammateId);
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        teammate.changePhone(rawPhone);

        log.info("Phone has been changed for teammate with id {}", rawTeammateId);

        teammateRepository.save(teammate);

        log.info("Teammate with id {} has been saved", rawTeammateId);
    }

    @Override
    @Transactional
    public void changeRole(String rawTeammateId, String rawRole) {
        log.info("Change role for teammate with id {}", rawRole);

        TeammateId teammateId = TeammateId.identifyTeammateFrom(rawTeammateId);
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        Teammate.UserRole userRole = Teammate.UserRole.fromName(rawRole);
        teammate.changeRole(userRole);

        log.info("Role has been changed for teammate with id {}", rawTeammateId);

        teammateRepository.save(teammate);

        log.info("Teammate with id {} has been saved", rawTeammateId);

    }

    @Override
    @Transactional
    public TeammateDTO searchTeammate(String rawTeammateId) {
        log.info("Search teammate with id {}", rawTeammateId);

        TeammateId teammateId = TeammateId.identifyTeammateFrom(rawTeammateId);
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        log.info("Teammate with id {} has been found", rawTeammateId);

        return teammateAssembler.writeDTO(teammate);
    }

    @Override
    @Transactional
    public Set<TeammateDTO> searchAllTeammates() {
        log.info("Search all created teammates");

        Set<Teammate> teammates = teammateRepository.allTeammates();
        Set<TeammateDTO> teammateDTOS = new HashSet<>();

        if(CollectionUtils.isNotEmpty(teammates))
            teammates.forEach(task -> teammateDTOS.add(teammateAssembler.writeDTO(task)));

        log.info("All created teammates have been found");

        return teammateDTOS;
    }

//    @Override
//    @Transactional
//    public TeammateDTO deleteTeammate(String rawTeammateId) {
//        return null;
//    }
}
