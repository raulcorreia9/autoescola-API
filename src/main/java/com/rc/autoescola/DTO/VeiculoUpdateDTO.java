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

//    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "A placa deve ter o formato AAA-1234")
    @Size(max = 8, min = 7, message = "A placa deve ter no máximo 8 e no mínimo 7 caracteres")
    String placa;

    @Size(max = 20, message = "A cor do veículo deve ter no máximo 20 caracteres")
    String cor;

    @Size(max = 60)
    String modelo;

    @Min(value = 2000, message = "O ano do veículo deve ser igual ou superior a 2000")
    Integer ano;

    @Enumerated(EnumType.STRING)
    TipoVeiculo tipoVeiculo;
}
