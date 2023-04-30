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

    @NotBlank(message = "o campo nome precisa ser informado")
    private String nome;

    private String matricula;

    @NotBlank(message = "o campo email precisa ser informado")
    @Email(message = "o campo email precisa ser válido")
    private String email;
}
