package ru.agiletech.teammate.service.application;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.agiletech.teammate.service.domain.teammate.Teammate;

import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeammateAssembler {

    private final ModelMapper modelMapper;

    TeammateDTO writeDTO(Teammate teammate){
        var teammateDTO = modelMapper.map(teammate, TeammateDTO.class);

        var id = teammate.teammateId();
        var role = teammate.role();

        teammateDTO.setId(id);
        teammateDTO.setRole(role);

        return teammateDTO;
    }
}
