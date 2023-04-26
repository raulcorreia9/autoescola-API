package com.rc.autoescola.DTO;


import lombok.Data;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AlunoDTO {

    @NotBlank
    private String nome;

    private String matricula;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
}
