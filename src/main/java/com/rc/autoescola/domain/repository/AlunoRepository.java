package com.rc.autoescola.domain.repository;

import com.rc.autoescola.domain.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
//    @Query(nativeQuery = true, value = "SELECT * FROM aluno a WHERE a.nome LIKE %:nome% ")
//    List<Aluno> findAlunoByNome(String nome);

    List<Aluno> findAlunoByNomeContainingIgnoreCase(String nome);

    Optional<Aluno> findAlunoByMatricula(String matricula);

    @Query(nativeQuery = true, value = "SELECT a.* FROM autoescola.aluno a INNER JOIN autoescola.veiculo v ON a.veiculo_id = v.id AND v.placa = :placa ")
    List<Aluno> findAllAlunosForVeiculoByPlaca(String placa);
}
