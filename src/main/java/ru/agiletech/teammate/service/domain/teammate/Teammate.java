package ru.agiletech.teammate.service.domain.teammate;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.*;
import ru.agiletech.teammate.service.domain.supertype.AggregateRoot;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teammates")
@Getter(value = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Teammate extends AggregateRoot {


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id", column=@Column(name="teammate_id"))
    })
    private TeammateId  teammateId;
    private FullName    fullName;
    private Credential  credential;
    private Contacts    contacts;
    private LocalDate   createDate;

    @Enumerated(EnumType.STRING)
    private UserRole    role;
    private Set<TaskId> tasks;


    private Teammate(TeammateId         teammateId,
                     FullName           fullName,
                     Credential         credential,
                     Contacts           contacts,
                     LocalDate          createDate,
                     UserRole           role,
                     Set<TaskId>        tasks) {
        this.teammateId = teammateId;
        this.fullName   = fullName;
        this.credential = credential;
        this.contacts   = contacts;
        this.createDate = createDate;
        this.role       = role;
        this.tasks      = tasks;
    }

    public void changeName(String name){
        if(StringUtils.isNotEmpty(name))
            throw new UnsupportedOperationException("Невозможно изменить имя. " +
                    "name должен содержать символы");

        fullName.changeName(name);
    }

    public void changeSurName(String surName){
        if(StringUtils.isNotEmpty(surName))
            throw new UnsupportedOperationException("Невозможно изменить фамилию. " +
                    "surName должен содержать символы");

        fullName.changeSurName(surName);
    }

    public void changePatronymic(String patronymic){
        if(StringUtils.isNotEmpty(patronymic))
            throw new UnsupportedOperationException("Невозможно изменить отчество. " +
                    "patronymic должен содержать символы");

        fullName.changePatronymic(patronymic);
    }

    public void changeLogin(String login){
        if(StringUtils.isNotEmpty(login))
            throw new UnsupportedOperationException("Невозможно изменить логин. " +
                    "login должен содержать символы");

        credential.changeLogin(login);
    }

    public void changePassword(String password){
        if(StringUtils.isNotEmpty(password))
            throw new UnsupportedOperationException("Невозможно изменить пароль. " +
                    "password должен содержать символы");

        credential.changePassword(password);
    }

    public void changeEmail(String email){
        if(StringUtils.isNotEmpty(email))
            throw new UnsupportedOperationException("Невозможно изменить адрес электронной почты. " +
                    "email должен содержать символы");

        contacts.changeEmail(email);
    }

    public void changePhone(String phone){
        if(StringUtils.isNotEmpty(phone))
            throw new UnsupportedOperationException("Невозможно изменить номер телефона. " +
                    "phone должен содержать символы");

        contacts.changePhone(phone);
    }

    public void changeRole(UserRole userRole){
        if(userRole == UserRole.UNKNOWN)
            throw new IllegalArgumentException("Unknown role");

        this.role = userRole;
    }

    public String role(){
        return this.role.getName();
    }


    public String teammateId(){
        return this.teammateId.getId();
    }

    public static Teammate create(String name,
                                  String surName,
                                  String patronymic,
                                  String login,
                                  String password,
                                  String email,
                                  String phone) {
        TeammateId teammateId = TeammateId.identifyTeammate();
        FullName fullName = FullName.create(name, surName, patronymic);
        Credential credential = Credential.create(login, password);
        Contacts contacts = Contacts.create(email, phone);

        return new Teammate(teammateId,
                fullName,
                credential,
                contacts,
                LocalDate.now(),
                UserRole.USER,
                new HashSet<>());
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
