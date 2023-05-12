package com.rc.autoescola.DTO;

import com.rc.autoescola.enums.TipoVeiculo;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class VeiculoCreateDTO {
    @NotBlank(message = "A placa do veículo deve ser informada")
    String placa;

    @NotBlank(message = "A cor do veículo deve ser informada")
    String cor;

    @NotBlank(message = "O modelo deve ser informado")
    String modelo;

    @NotNull
    @Min(value = 2000, message = "O ano do veículo deve ser igual ou superior a 2000")
    Integer ano;

    @NotNull(message = "O tipo do veículo deve ser informado")
    @Enumerated(EnumType.STRING)
    TipoVeiculo tipoVeiculo;
}
