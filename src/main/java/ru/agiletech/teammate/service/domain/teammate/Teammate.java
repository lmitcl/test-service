package ru.agiletech.teammate.service.domain.teammate;

import lombok.*;
import ru.agiletech.teammate.service.domain.supertype.AggregateRoot;

import javax.persistence.Table;
import java.time.LocalDate;
import java.util.*;

//@Entity
@Table(name = "teammates")
@Getter(value = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Teammate extends AggregateRoot {

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="id", column=@Column(name="teammate_id"))
//    })
    private TeammateId teammateId;

    private String userName;
    private String name;
    private String surName;
    private String patronymic;
    private String password;
    private String email;

    //private LocalDate createDate;

//    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean isDeleted;
    private Set<TaskId> tasks;


    private Teammate(TeammateId         teammateId,
                     String             userName,
                     String             name,
                     String             surName,
                     String             patronymic,
                     String             password,
                     String             email,
                     //LocalDate          createDate,
                     UserRole           role,
                     Set<TaskId>        tasks) {
        this.teammateId = teammateId;
        this.userName   = userName;
        this.name       = name;
        this.surName    = surName;
        this.patronymic = patronymic;
        this.password   = password;
        this.email      = email;
        //this.createDate = createDate;
        this.role       = role;
        this.tasks      = tasks;
    }

    public void changeUserName(String userName){
        if(userName.equals(""))
            throw new UnsupportedOperationException("Невозможно изменить userName. " +
                    "Название должно содержать символы");

        this.userName = userName;
    }

    public void changePassword(String password){
        if(password.equals(""))
            throw new UnsupportedOperationException("Невозможно изменить password. " +
                    "Пароль должен содержать символы");

        this.password = password;
    }

    public void changeEmail(String email){
        if(email.equals(""))
            throw new UnsupportedOperationException("Невозможно изменить email. " +
                    "email должен содержать символы");

        this.email = email;
    }

    public void changeRole(UserRole userRole){
        if(userRole == UserRole.UNKNOWN)
            throw new IllegalArgumentException("Unknown role");

        this.role = userRole;
    }

    public String role(){
        return this.role.getName();
    }

//    public LocalDate createDate(){
//        return this.createDate = LocalDate.now();
//    }

    public String teammateId(){
        return this.teammateId.getId();
    }

    public static Teammate create(String userName,
                                  String name,
                                  String surName,
                                  String patronymic,
                                  String password,
                                  String email){
        TeammateId teammateId = TeammateId.identifyTeammate();


        return new Teammate(teammateId,
                userName,
                name,
                surName,
                patronymic,
                password,
                email,
                //createDate,
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
