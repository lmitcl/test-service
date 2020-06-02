package ru.agiletech.teammate.service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.agiletech.teammate.service.domain.Teammate;
import ru.agiletech.teammate.service.domain.TeammateId;

import java.util.Optional;

public interface TeammateDAO extends JpaRepository<Teammate, Long> {

    Optional<Teammate> findByTeammateId(TeammateId teammateId);

}
