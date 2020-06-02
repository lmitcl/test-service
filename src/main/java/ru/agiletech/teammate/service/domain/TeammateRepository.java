package ru.agiletech.teammate.service.domain;

import java.util.Set;

public interface TeammateRepository {

    void save(Teammate teammate);

    Set<Teammate> allTeammates();

    Teammate teammateOfId(TeammateId teammateId);

}
