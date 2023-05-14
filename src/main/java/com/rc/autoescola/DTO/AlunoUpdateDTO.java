package com.rc.autoescola.DTO;

import com.rc.autoescola.models.Veiculo;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
public class AlunoUpdateDTO {
    @NotNull
    @Positive
    private Long id;

    private String nome;

    @Email
    private String email;

    private Veiculo veiculo;
}
