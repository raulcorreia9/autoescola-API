package com.rc.autoescola.DTO;

import com.rc.autoescola.models.Veiculo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlunoGetDTO {
    private Long id;

    private String nome;

    private String matricula;

    private String email;

    private Veiculo veiculo;
}
