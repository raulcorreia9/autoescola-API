package com.rc.autoescola.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rc.autoescola.DTO.VeiculoCreateDTO;
import com.rc.autoescola.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String placa;

    private String cor;

    private String modelo;

    private Integer ano;

    @Enumerated
    private TipoVeiculo tipoVeiculo;

    @OneToMany(mappedBy = "veiculo")
    @JsonIgnore
    private Set<Aluno> alunos;

}
