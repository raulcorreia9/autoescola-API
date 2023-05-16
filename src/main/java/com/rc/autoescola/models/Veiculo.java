package com.rc.autoescola.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rc.autoescola.enums.TipoVeiculo;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @PreRemove
    public void setVeiculoAsNullOnRemove() {
        for (Aluno aluno : getAlunos()) {
            if (aluno.getVeiculo() != null && aluno.getVeiculo().equals(this)) {
                aluno.setVeiculo(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id) && Objects.equals(placa, veiculo.placa) && Objects.equals(cor, veiculo.cor) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(ano, veiculo.ano) && tipoVeiculo == veiculo.tipoVeiculo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placa, cor, modelo, ano, tipoVeiculo);
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", cor='" + cor + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", tipoVeiculo=" + tipoVeiculo +
                ", alunos=" + alunos +
                '}';
    }
}
