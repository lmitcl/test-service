package ru.agiletech.teammate.service.application.security;

import ru.agiletech.teammate.service.domain.teammate.Credential;

public interface SecurityService {

    void passwordEncrypt(Credential credential);
}
