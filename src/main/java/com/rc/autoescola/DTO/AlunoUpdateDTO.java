package com.rc.autoescola.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AlunoUpdateDTO {
    @NotBlank
    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String nome;

    private String matricula;

    @NotBlank
    private String email;
}
