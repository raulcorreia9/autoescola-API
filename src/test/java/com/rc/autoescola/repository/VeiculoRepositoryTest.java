package com.rc.autoescola.repository;

import com.rc.autoescola.domain.models.Veiculo;
import com.rc.autoescola.domain.repository.VeiculoRepository;
import com.rc.autoescola.util.VeiculoCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Testes para repository de Veiculo - JPA Tests")
@Log4j2
class VeiculoRepositoryTest {
    @Autowired
    VeiculoRepository veiculoRepository;

    @Test
    @DisplayName("Save persiste o veículo quando ocorrer sucesso")
    void save_PersistsVeiculo_WhenSuccessful() {
        Veiculo veiculoToBeSaved = VeiculoCreator.createVeiculoToBeSaved();

        Veiculo veiculoSaved = veiculoRepository.save(veiculoToBeSaved);

        Assertions.assertThat(veiculoSaved).isNotNull();
        Assertions.assertThat(veiculoSaved.getId()).isNotNull();

        Assertions.assertThat(veiculoSaved.getAno()).isGreaterThanOrEqualTo(2000);
    }


    @Test
    @DisplayName("Update atualiza um veículo quando ocorrer sucesso")
    void save_UpdatesAnime_WhenSuccessful(){
        Veiculo veiculoToBeSaved = VeiculoCreator.createVeiculoToBeSaved();

        Veiculo veiculoSaved = veiculoRepository.save(veiculoToBeSaved);

        veiculoSaved.setCor("Azul");

        Veiculo veiculoUpdated = veiculoRepository.save(veiculoSaved);

        Assertions.assertThat(veiculoUpdated).isNotNull();

        Assertions.assertThat(veiculoUpdated.getId()).isNotNull();

        Assertions.assertThat(veiculoUpdated.getPlaca()).isEqualTo(veiculoSaved.getPlaca());
    }
//
//    @Test
//    @DisplayName("Find All retorna uma lista de Alunos quando ocorrer sucesso")
//    void findAll_ReturnsListOfAluno_WhenSuccessful() {
//        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();
//
//        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);
//
//        List<Aluno> alunos = alunoRepository.findAll();
//
//        Assertions.assertThat(alunos).isNotEmpty().hasSize(1).isNotNull();
//        Assertions.assertThat(alunos).contains(alunoSaved);
//
//    }
//
//    @Test
//    @DisplayName("Find All retorna uma lista vazia de Alunos quando nenhum aluno for encontrado")
//    void findAll_ReturnsEmptyListOfAluno_WhenSuccessful() {
//        List<Aluno> alunos = alunoRepository.findAll();
//
//        Assertions.assertThat(alunos).isNotNull().isEmpty();
//    }
//
//    @Test
//    @DisplayName("Find By Name retorna uma lista de Alunos quando ocorrer sucesso")
//    void findAlunoByNome_ReturnsListOfAluno_WhenAlunoAreFound() {
//        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();
//
//        String nome = alunoToBeSaved.getNome();
//
//        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);
//
//        List<Aluno> alunos = alunoRepository.findAlunoByNomeContainingIgnoreCase(nome);
//
//        Assertions.assertThat(alunos).isNotEmpty().hasSize(1).isNotNull();
//        Assertions.assertThat(alunos).contains(alunoSaved);
//
//    }
//
//    @Test
//    @DisplayName("Find Aluno By Nome retorna uma lista vazia de Alunos quando nenhum aluno for encontrado")
//    void findAlunoByNome_ReturnsEmptyListOfAluno_WhenNoAlunoAreFound() {
//        List<Aluno> alunos = alunoRepository.findAlunoByNomeContainingIgnoreCase("nobody");
//        Assertions.assertThat(alunos).isNotNull().isEmpty();
//    }
//
//
//    @Test
//    @DisplayName("Find By Id retorna um Aluno quando ocorrer sucesso")
//    void findById_ReturnsAnAluno_WhenAlunoAreFound() {
//        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();
//
//        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);
//        Long id = alunoSaved.getId();
//
//        Optional<Aluno> aluno = alunoRepository.findById(id);
//
//        Assertions.assertThat(aluno).isNotEmpty().isNotNull();
//        Assertions.assertThat(aluno.get().getId()).isEqualTo(id);
//
//    }
//
//    @Test
//    @DisplayName("Find By Id retorna uma lista vazia de Alunos quando nenhum aluno for encontrado")
//    void findById_ReturnsEmptyListOfAluno_WhenNoAlunoAreFound() {
//        Optional<Aluno> aluno = alunoRepository.findById(1L);
//
//        Assertions.assertThat(aluno).isNotNull().isEmpty();
//    }
//
//    @Test
//    @DisplayName("Find By matrícula retorna um Aluno quando ocorrer sucesso")
//    void findByMatricula_ReturnsAnAluno_WhenAlunoAreFound() {
//        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();
//
//        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);
//        String matricula = alunoSaved.getMatricula();
//
//        Optional<Aluno> aluno = alunoRepository.findAlunoByMatricula(matricula);
//
//        Assertions.assertThat(aluno).isNotEmpty().isNotNull();
//        Assertions.assertThat(aluno.get().getMatricula()).isEqualTo(matricula);
//    }
//
//    @Test
//    @DisplayName("Delete remove um Aluno quando ocorrer sucesso")
//    void delete_RemovesAnAluno_WhenSuccessful() {
//        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();
//
//        Aluno alunoSaved = alunoRepository.save(alunoToBeSaved);
//
//        alunoRepository.delete(alunoSaved);
//
//        Optional<Aluno> alunoFounded = alunoRepository.findById(alunoSaved.getId());
//
//        Assertions.assertThat(alunoFounded).isEmpty();
//
//    }

}