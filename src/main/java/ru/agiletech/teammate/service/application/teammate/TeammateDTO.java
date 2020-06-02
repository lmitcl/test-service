package ru.agiletech.teammate.service.application.teammate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Представление модели участника agile команды")
public class TeammateDTO extends RepresentationModel<TeammateDTO> {

    @ApiModelProperty(value = "Идентификатор участника команды")
    private String      id;

    @NotEmpty
    @ApiModelProperty(position = 1, required = true, value = "Логин")
    private String      login;

    @NotEmpty
    @ApiModelProperty(position = 2, required = true, value = "Имя")
    private String      name;

    @NotEmpty
    @ApiModelProperty(position = 3, required = true, value = "Фамилия")
    private String      surName;

    @ApiModelProperty(position = 4, value = "Отчество")
    private String      patronymic;

    @NotEmpty
    @ApiModelProperty(position = 5, required = true, value = "Пароль")
    private String      password;

    @NotEmpty
    @ApiModelProperty(position = 6, required = true, value = "Почта")
    private String      email;

    @ApiModelProperty(position = 7, value = "Номер телефона")
    private String      phoneNumber;

    @ApiModelProperty(position = 8, value = "Дата создания")
    private LocalDate createDate;

    @ApiModelProperty(position = 9, value = "Роль")
    private String      role;

    @ApiModelProperty(position = 10, value = "Открытые задачи")
    private Set<String> openTasks;

}
