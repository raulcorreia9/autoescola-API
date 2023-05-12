package com.rc.autoescola.models;

import com.rc.autoescola.DTO.VeiculoCreateDTO;
import com.rc.autoescola.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

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
    String placa;

    String cor;

    String modelo;

    Integer ano;

    @Enumerated
    TipoVeiculo tipoVeiculo;

}
