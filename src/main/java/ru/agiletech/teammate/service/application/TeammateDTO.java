package ru.agiletech.teammate.service.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeammateDTO extends RepresentationModel<TeammateDTO> {

    @NotEmpty
    private String      userName;
    @NotEmpty
    private String      name;
    @NotEmpty
    private String      surName;
    @NotEmpty
    private String      patronymic;
    @NotEmpty
    private String      password;
    @NotEmpty
    private String      email;
    private String      id;
    private LocalDate   createDate;
    private String      role;


}
