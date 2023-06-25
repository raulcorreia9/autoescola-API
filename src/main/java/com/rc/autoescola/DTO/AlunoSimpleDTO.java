package com.rc.autoescola.DTO;

import com.rc.autoescola.domain.models.Aluno;
import lombok.Getter;

@Getter
public class AlunoSimpleDTO {
    private String nome;

    private String matricula;

    private String email;

    public AlunoSimpleDTO(Aluno aluno) {
        nome = aluno.getNome();
        matricula = aluno.getMatricula();
        email = aluno.getEmail();
    }
}
