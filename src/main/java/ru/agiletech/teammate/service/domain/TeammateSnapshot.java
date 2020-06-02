package ru.agiletech.teammate.service.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import ru.agiletech.teammate.service.domain.supertype.ValueObject;
import ru.agiletech.teammate.service.domain.task.TaskId;

import java.util.HashSet;
import java.util.Set;

import static ru.agiletech.teammate.service.domain.Teammate.UserRole;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class TeammateSnapshot implements ValueObject {

    private final UserRole      userRole;
    private final Contacts      contacts;
    private final FullName      fullName;
    private final Credentials credentials;
    private final Set<TaskId>   tasks;

    public String getName(){ return this.fullName.getName(); }

    public String getSurName(){ return this.fullName.getSurName(); }

    public String getPatronymic(){ return this.fullName.getPatronymic(); }

    public String getPassword(){ return this.credentials.getPassword(); }

    public String getPhoneNumber(){ return this.contacts.getPhoneNumber(); }

    public String getEmail(){ return this.contacts.getEmail(); }

    public String getLogin(){ return this.credentials.getLogin(); }

    public String getRole(){
        return this.userRole.getName();
    }

    public Set<String> getOpenTasks(){
        Set<String> tasks = new HashSet<>();

        if(CollectionUtils.isNotEmpty(this.tasks))
            this.tasks.forEach(taskId -> tasks.add(taskId.getId()));

        return tasks;
    }

}
