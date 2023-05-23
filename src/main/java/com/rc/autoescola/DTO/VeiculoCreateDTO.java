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
import javax.validation.constraints.Size;

@Data
@Builder
public class VeiculoCreateDTO {
    @NotBlank(message = "A placa do veículo deve ser informada")
    @Size(max = 8, min = 7, message = "A placa deve ter no máximo 8 e no mínimo 7 caracteres")
    String placa;

    @NotBlank(message = "A cor do veículo deve ser informada")
    @Size(max = 20, message = "A cor do veículo deve ter no máximo 20 caracteres")
    String cor;

    @NotBlank(message = "O modelo deve ser informado")
    @Size(max = 60)
    String modelo;

    @NotNull
    @Min(value = 2000, message = "O ano do veículo deve ser igual ou superior a 2000")
    Integer ano;

    @NotNull(message = "O tipo do veículo deve ser informado")
    @Enumerated(EnumType.STRING)
    TipoVeiculo tipoVeiculo;
}
