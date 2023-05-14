package com.rc.autoescola.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "o campo nome precisa ser informado")
    private String nome;

    @Column(unique = true)
    private String matricula;

    @NotEmpty
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "veiculoId", nullable = true)
    private Veiculo veiculo;
}
