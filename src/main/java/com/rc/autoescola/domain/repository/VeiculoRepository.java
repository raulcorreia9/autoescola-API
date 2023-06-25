package com.rc.autoescola.domain.repository;

import com.rc.autoescola.domain.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findVeiculoByPlaca(String placa);
}
