package com.rc.autoescola.util;

import com.rc.autoescola.DTO.AlunoCreateDTO;
import com.rc.autoescola.domain.models.Aluno;

public class AlunoPostDTOCreator {
    public static AlunoCreateDTO createAlunoPostDTO() {
        Aluno validAluno = AlunoCreator.createAlunoToBeSaved();
        return AlunoCreateDTO.builder()
                .nome(validAluno.getNome())
                .email(validAluno.getEmail())
                .matricula(validAluno.getMatricula())
                .build();
    }

}
