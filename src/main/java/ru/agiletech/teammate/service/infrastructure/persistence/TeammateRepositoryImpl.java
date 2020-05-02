package ru.agiletech.teammate.service.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import ru.agiletech.teammate.service.domain.teammate.Teammate;
import ru.agiletech.teammate.service.domain.teammate.TeammateId;
import ru.agiletech.teammate.service.domain.teammate.TeammateRepository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeammateRepositoryImpl implements TeammateRepository {

    private final TeammateDAO teammateDAO;

    @Override
    public void save(Teammate teammate) {
        try{
            teammateDAO.save(teammate);
        } catch (DataAccessException e) {
            log.error(e.getMessage());

            throw new RepositoryAccessException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Teammate> allTeammates() {
        try{
            return new HashSet<>(teammateDAO.findAll());

        } catch (DataAccessException e){
            log.error(e.getMessage());

            throw new RepositoryAccessException(e.getMessage(), e);
        }
    }

    @Override
    public Teammate teammateOfId(TeammateId teammateId) {
        try {
            return teammateDAO.findByTeammateId(teammateId)
                    .orElseThrow(() -> new TeammateNotFoundException(String.format("Teammate with id %s is not found", teammateId.getId())));

        } catch (DataAccessException e) {
            log.error(e.getMessage());

            throw new RepositoryAccessException(e.getMessage(), e);
        }
    }
}
