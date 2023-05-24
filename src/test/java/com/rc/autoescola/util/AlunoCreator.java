package com.rc.autoescola.util;

import com.rc.autoescola.DTO.AlunoGetDTO;
import com.rc.autoescola.models.Aluno;

public class AlunoCreator {

    public static Aluno createAlunoToBeSaved() {
        return Aluno.builder()
                .nome("Raul")
                .email("raul@email.com")
                .matricula("2023123456")
                .build();
    }

    public static Aluno createValidAluno() {
        return Aluno.builder()
                .id(1L)
                .nome("Raul")
                .email("raul@email.com")
                .matricula("2023123456")
                .build();
    }

    public static AlunoGetDTO createValidAlunoGetDTO() {
        AlunoGetDTO alunoGetDTO = new AlunoGetDTO(1L, "Raul", "raul@email.com", "2023123456", null);
        return alunoGetDTO;
    }

}
