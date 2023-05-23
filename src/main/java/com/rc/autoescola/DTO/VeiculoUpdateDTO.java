package com.rc.autoescola.DTO;

import com.rc.autoescola.enums.TipoVeiculo;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@Builder
public class VeiculoUpdateDTO {
    @NotNull
    @Positive
    private Long id;

//    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "A placa deve ter o formato AAA-1234")
    String placa;

    String cor;

    String modelo;

    @Min(value = 2000, message = "O ano do veículo deve ser igual ou superior a 2000")
    Integer ano;

    @Enumerated(EnumType.STRING)
    TipoVeiculo tipoVeiculo;
}
