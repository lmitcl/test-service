package ru.agiletech.teammate.service.application.teammate;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.agiletech.teammate.service.domain.Teammate;

@Service
@RequiredArgsConstructor
public class TeammateAssembler {

    private final ModelMapper modelMapper;

    TeammateDTO writeDTO(Teammate teammate){
        var teammateDTO = modelMapper.map(teammate, TeammateDTO.class);
        var teammateSnapshot = teammate.makeSnapshot();

        var id = teammate.teammateId();

        teammateDTO.setId(id);
        teammateDTO.setRole(teammateSnapshot.getRole());
        teammateDTO.setEmail(teammateSnapshot.getEmail());
        teammateDTO.setPhoneNumber(teammateSnapshot.getPhoneNumber());
        teammateDTO.setLogin(teammateSnapshot.getLogin());
        teammateDTO.setPassword(teammateSnapshot.getPassword());
        teammateDTO.setName(teammateSnapshot.getName());
        teammateDTO.setPatronymic(teammateSnapshot.getPatronymic());
        teammateDTO.setSurName(teammateSnapshot.getSurName());
        teammateDTO.setOpenTasks(teammateSnapshot.getOpenTasks());

        return teammateDTO;
    }

}
