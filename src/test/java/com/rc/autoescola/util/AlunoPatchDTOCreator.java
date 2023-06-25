package com.rc.autoescola.util;

import com.rc.autoescola.DTO.AlunoUpdateDTO;
import com.rc.autoescola.domain.models.Aluno;

public class AlunoPatchDTOCreator {
    public static AlunoUpdateDTO createAlunoPatchDTO() {
        Aluno validAluno = AlunoCreator.createValidAluno();
        return AlunoUpdateDTO.builder()
                .id(validAluno.getId())
                .nome(validAluno.getNome())
                .email(validAluno.getEmail())
                .build();
    }

}
