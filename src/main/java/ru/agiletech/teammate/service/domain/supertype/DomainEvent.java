package ru.agiletech.teammate.service.domain.supertype;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DomainEvent {

    private Date occurredOn;
    private String  name;
}
