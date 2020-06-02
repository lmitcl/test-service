package ru.agiletech.teammate.service.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import ru.agiletech.teammate.service.domain.supertype.AggregateRoot;
import ru.agiletech.teammate.service.domain.task.TaskId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teammates")
@Getter(value = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Teammate extends AggregateRoot {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="teammate_id"))
    })
    private TeammateId teammateId;

    @Embedded
    private FullName fullName;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Contacts contacts;

    private LocalDate createDate;

    @Enumerated(EnumType.STRING)
    private UserRole    role;

    @ElementCollection
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="task_id"))
    })
    private Set<TaskId> tasks;

    public void changeFullName(String name,
                               String surName,
                               String patronymic){
        if(StringUtils.isNotEmpty(name))
            this.fullName = this.fullName.withName(name);
        if(StringUtils.isNotEmpty(surName))
            this.fullName = this.fullName.withSurName(surName);
        if(StringUtils.isNotEmpty(patronymic))
            this.fullName = this.fullName.withPatronymic(patronymic);
    }

    public void changePassword(String password){
        this.credentials = this.credentials.withPassword(password);
    }

    public void changeContacts(String email,
                               String phoneNumber){
        if(StringUtils.isNotEmpty(email))
            this.contacts = this.contacts.withEmail(email);
        if(StringUtils.isNotEmpty(phoneNumber))
            this.contacts = this.contacts.withPhoneNumber(phoneNumber);
    }

    public void changeRole(UserRole userRole){
        if(userRole == UserRole.UNKNOWN)
            throw new IllegalArgumentException("Unknown role");

        this.role = userRole;
    }

    public String teammateId(){
        return this.teammateId.getId();
    }

    public void openTask(TaskId taskId){
        this.tasks.add(taskId);
    }

    public TeammateSnapshot makeSnapshot(){
        return new TeammateSnapshot(this.role,
                this.contacts,
                this.fullName,
                this.credentials,
                this.tasks);
    }

    public static class Builder {

        private final Teammate teammate;

        public Builder() {
            this.teammate = new Teammate();

            TeammateId teammateId = TeammateId.identifyTeammate();
            LocalDate createDate = LocalDate.now();

            this.teammate.setTasks(new HashSet<>());
            this.teammate.setCreateDate(createDate);
            this.teammate.setTeammateId(teammateId);
        }

        public Builder withFullName(String name,
                                    String surName,
                                    String patronymic) {
            FullName fullName = FullName.create(name,
                    surName,
                    patronymic);

            teammate.setFullName(fullName);

            return this;
        }

        public Builder withCredentials(String login,
                                       String password) {
            Credentials credentials = Credentials.create(login,
                    password);

            teammate.setCredentials(credentials);

            return this;
        }

        public Builder withContacts(String email,
                                    String phoneNumber) {
            Contacts contacts = Contacts.create(email,
                    phoneNumber);

            teammate.setContacts(contacts);

            return this;
        }

        public Builder withRole(String role){
            UserRole userRole = UserRole.fromName(role);
            if(userRole == UserRole.UNKNOWN)
                throw new IllegalArgumentException(String.format("Unknown user role %s", role));

            this.teammate.setRole(userRole);

            return this;
        }

        public Teammate build(){
            return this.teammate;
        }

    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null
                || getClass() != object.getClass())
            return false;

        Teammate teammate = (Teammate) object;

        return Objects.equals(teammateId,
                teammate.teammateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teammateId);
    }

    @RequiredArgsConstructor
    public enum UserRole{
        USER("USER"),
        ADMINISTRATOR("ADMINISTRATOR"),
        UNKNOWN("UNKNOWN");

        @Getter
        private final String name;

        public static UserRole fromName(String name){
            for(UserRole userRole: UserRole.values()){
                if(userRole.getName().equals(name))
                    return userRole;
            }

            return UNKNOWN;
        }

    }

}
