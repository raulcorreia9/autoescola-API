package com.rc.autoescola.DTO;

import com.rc.autoescola.domain.models.Veiculo;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
public class AlunoUpdateDTO {

    @Size(max = 60)
    private String nome;

    @Email
    private String email;

    private Veiculo veiculo;
}
