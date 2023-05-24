package com.rc.autoescola.assembler;

import com.rc.autoescola.DTO.AlunoCreateDTO;
import com.rc.autoescola.DTO.AlunoGetDTO;
import com.rc.autoescola.DTO.AlunoSimpleDTO;
import com.rc.autoescola.models.Aluno;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AlunoAssembler {

    private final ModelMapper modelMapper;

    public AlunoGetDTO toAlunoGetDTO(Aluno aluno) {
        return modelMapper.map(aluno, AlunoGetDTO.class);
    }

    public AlunoSimpleDTO toAlunoSimpleDTO(Aluno aluno) {
        return modelMapper.map(aluno, AlunoSimpleDTO.class);
    }

    public List<AlunoGetDTO> toCollectionAlunoGetDTO(List<Aluno> alunos) {
        return alunos.stream().map(this::toAlunoGetDTO).collect(Collectors.toList());
    }

    public Aluno toEntityFromCreate(AlunoCreateDTO alunoCreateDTO) {
        return modelMapper.map(alunoCreateDTO, Aluno.class);
    }
}
