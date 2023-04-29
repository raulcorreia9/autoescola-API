package com.rc.autoescola.repository;

import com.rc.autoescola.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM aluno a WHERE a.nome LIKE %:nome% ")
    List<Aluno> findAlunoByNome(String nome);

    List<Aluno> findAlunoByNomeContainingIgnoreCase(String nome);
}
