package ru.agiletech.teammate.service.application.teammate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agiletech.teammate.service.application.teammate.command.ChangeContactsCommand;
import ru.agiletech.teammate.service.application.teammate.command.ChangeFullNameCommand;
import ru.agiletech.teammate.service.application.teammate.command.ChangePasswordCommand;
import ru.agiletech.teammate.service.application.teammate.command.ChangeRoleCommand;
import ru.agiletech.teammate.service.domain.Teammate;
import ru.agiletech.teammate.service.domain.TeammateId;
import ru.agiletech.teammate.service.domain.TeammateRepository;

import java.util.HashSet;
import java.util.Set;

import static ru.agiletech.teammate.service.domain.Teammate.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeammateServiceImpl implements TeammateService {

    private final TeammateRepository    teammateRepository;
    private final TeammateAssembler     teammateAssembler;
    private final PasswordEncoder       passwordEncoder;

    @Override
    @Transactional
    public TeammateDTO createTeammate(TeammateDTO rawTeammate) {
        log.info("Create teammate");
        String encodedPassword = passwordEncoder.encode(rawTeammate.getPassword());

        Teammate teammate = new Builder()
                .withContacts(rawTeammate.getEmail(),
                        rawTeammate.getPhoneNumber())
                .withCredentials(rawTeammate.getLogin(),
                        encodedPassword)
                .withFullName(rawTeammate.getName(),
                        rawTeammate.getSurName(),
                        rawTeammate.getPatronymic())
                .withRole(rawTeammate.getRole())
                .build();

        String id = teammate.teammateId();

        log.info("Teammate with id {} has been created", id);
        teammateRepository.save(teammate);

        log.info("Teammate with id {} has been saved", id);

        return teammateAssembler.writeDTO(teammate);
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordCommand command) {
        log.info("Change password");

        String encodedPassword = passwordEncoder.encode(command.getPassword());

        TeammateId teammateId = TeammateId.identifyTeammateFrom(command.getId());
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        teammate.changePassword(encodedPassword);
        log.info("Password has been changed for teammate with id {}", command.getId());

        teammateRepository.save(teammate);
        log.info("Teammate with id {} has been saved", command.getId());
    }

    @Override
    @Transactional
    public void changeContacts(ChangeContactsCommand command) {
        log.info("Change contacts");

        TeammateId teammateId = TeammateId.identifyTeammateFrom(command.getId());
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        teammate.changeContacts(command.getEmail(),
                command.getPhoneNumber());
        log.info("Contacts has been changed for teammate with id {}", command.getId());

        teammateRepository.save(teammate);
        log.info("Teammate with id {} has been saved", command.getId());
    }

    @Override
    @Transactional
    public void changeFullName(ChangeFullNameCommand command) {
        log.info("Change fullName");

        TeammateId teammateId = TeammateId.identifyTeammateFrom(command.getId());
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        teammate.changeFullName(command.getName(),
                command.getSurName(),
                command.getPatronymic());
        log.info("FullName has been changed for teammate with id {}", command.getId());

        teammateRepository.save(teammate);
        log.info("Teammate with id {} has been saved", command.getId());
    }

    @Override
    @Transactional
    public void changeRole(ChangeRoleCommand command) {
        log.info("Change role");

        TeammateId teammateId = TeammateId.identifyTeammateFrom(command.getId());
        Teammate teammate = teammateRepository.teammateOfId(teammateId);

        UserRole userRole = UserRole.fromName(command.getRole());

        teammate.changeRole(userRole);
        log.info("Role has been changed for teammate with id {}", command.getId());

        teammateRepository.save(teammate);
        log.info("Teammate with id {} has been saved", command.getId());
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

}
