package com.rc.autoescola.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private String email;

    private String cpf;

    @Embedded
    @JsonIgnore
    private Endereco endereco;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "instrutor_veiculo",
            joinColumns = @JoinColumn(name = "instrutor_id"),
            inverseJoinColumns = @JoinColumn(name = "veiculo_id"))
    private List<Veiculo> veiculos = new ArrayList<>();

    @OneToMany(mappedBy = "instrutor")
    @JsonIgnore
    private List<Aluno> alunos = new ArrayList<>();
}
