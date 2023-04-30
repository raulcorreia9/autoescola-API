package com.rc.autoescola.repository;

import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.util.AlunoCreator;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@DisplayName("Testes para repository de Aluno - JPA Tests")
@Log4j2
class AlunoRepositoryTest {
    @Autowired
    AlunoRepository alunoRepository;
    
    @Test
    @DisplayName("Save persiste o aluno quando ocorrer sucesso")
    void save_PersistsAluno_WhenSuccessful() {
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();

        String nome = alunoToBeSaved.getNome();
        String matricula = alunoToBeSaved.getMatricula();
        String email = alunoToBeSaved.getEmail();

        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);

        Assertions.assertThat(alunoSaved).isNotNull();
        Assertions.assertThat(alunoSaved.getId()).isNotNull();

        Assertions.assertThat(alunoSaved.getMatricula().length()).isEqualTo(10);

        Assertions.assertThat(alunoSaved.getNome()).isEqualTo(nome);
        Assertions.assertThat(alunoSaved.getEmail()).isEqualTo(email);
        Assertions.assertThat(alunoSaved.getMatricula()).isEqualTo(matricula);
    }

    @Test
    @DisplayName("Save lança ConstraintViolationException quando atributo obrigatorio de Aluno não for informado")
    void save_ThrowsConstraintViolationException_WhenRequiredFieldIsEmpty(){
        Aluno aluno = Aluno.builder()
                .email("aluno@email.com")
                .build();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.alunoRepository.save(aluno))
                .withMessageContaining("o campo nome precisa ser informado");
    }

    @Test
    @DisplayName("Find All retorna uma lista de Alunos quando ocorrer sucesso")
    void findAll_ReturnsListOfAluno_WhenSuccessful() {
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();

        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);

        List<Aluno> alunos = alunoRepository.findAll();

        Assertions.assertThat(alunos).isNotEmpty().hasSize(1).isNotNull();
        Assertions.assertThat(alunos).contains(alunoSaved);

    }

    @Test
    @DisplayName("Find All retorna uma lista vazia de Alunos quando nenhum aluno for encontrado")
    void findAll_ReturnsEmptyListOfAluno_WhenSuccessful() {
        List<Aluno> alunos = alunoRepository.findAll();

        Assertions.assertThat(alunos).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("Find By Name retorna uma lista de Alunos quando ocorrer sucesso")
    void findAlunoByNome_ReturnsListOfAluno_WhenAlunoAreFound() {
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();

        String nome = alunoToBeSaved.getNome();

        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);

        List<Aluno> alunos = alunoRepository.findAlunoByNome(nome);

        Assertions.assertThat(alunos).isNotEmpty().hasSize(1).isNotNull();
        Assertions.assertThat(alunos).contains(alunoSaved);

    }

    @Test
    @DisplayName("Find Aluno By Nome retorna uma lista vazia de Alunos quando nenhum aluno for encontrado")
    void findAlunoByNome_ReturnsEmptyListOfAluno_WhenNoAlunoAreFound() {
        List<Aluno> alunos = alunoRepository.findAlunoByNome("nobody");
        Assertions.assertThat(alunos).isNotNull().isEmpty();
    }


    @Test
    @DisplayName("Find By Id retorna um Aluno quando ocorrer sucesso")
    void findById_ReturnsAnAluno_WhenAlunoAreFound() {
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();

        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);

        Optional<Aluno> alunos = alunoRepository.findById(alunoSaved.getId());

        Assertions.assertThat(alunos).isNotEmpty().isNotNull();
        Assertions.assertThat(alunos).contains(alunoSaved);

    }

    @Test
    @DisplayName("Find By Id retorna uma lista vazia de Alunos quando nenhum aluno for encontrado")
    void findById_ReturnsEmptyListOfAluno_WhenNoAlunoAreFound() {
        Optional<Aluno> aluno = alunoRepository.findById(1L);

        Assertions.assertThat(aluno).isNotNull().isEmpty();
    }

    /*
    * DELETE
    * UPDATE
    * */


}