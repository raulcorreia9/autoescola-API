package com.rc.autoescola.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Random;

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

    public void generateMatriculaAluno() {
        //Formato da matrícula: Ano atual + 6 dígitos aleatorios
        int year = LocalDate.now().getYear();
        String randomDigits = String.format("%06d", new Random().nextInt(999999));
        setMatricula(year + randomDigits);
    }
}
