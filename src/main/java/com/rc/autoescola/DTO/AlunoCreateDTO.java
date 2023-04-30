package com.rc.autoescola.DTO;


import lombok.Data;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AlunoCreateDTO {

    @NotBlank(message = "o campo nome precisa ser informado")
    private String nome;

    private String matricula;

    @NotBlank(message = "o campo email precisa ser informado")
    @Email(message = "o campo email precisa ser válido")
    private String email;
}
