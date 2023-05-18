package com.rc.autoescola.util;

import com.rc.autoescola.enums.TipoVeiculo;
import com.rc.autoescola.models.Veiculo;

public class VeiculoCreator {

    public static Veiculo createVeiculoToBeSaved() {
        return Veiculo.builder()
                .modelo("VW UP")
                .placa("nhk2385")
                .cor("Branco")
                .ano(2015)
                .tipoVeiculo(TipoVeiculo.CARRO)
                .build();
    }

    public static Veiculo createValidAluno() {
        return Veiculo.builder()
                .id(1L)
                .modelo("VW UP")
                .placa("nhk2385")
                .cor("Branco")
                .ano(2015)
                .tipoVeiculo(TipoVeiculo.CARRO)
                .build();
    }

}
