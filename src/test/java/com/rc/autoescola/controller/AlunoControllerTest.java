package com.rc.autoescola.controller;

import com.rc.autoescola.exception.NotFoundException;
import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.service.AlunoService;
import com.rc.autoescola.util.AlunoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AlunoControllerTest {

    @InjectMocks
    private AlunoController alunoController;

    @Mock
    private AlunoService alunoServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Aluno> alunoPage = new PageImpl<>(List.of(AlunoCreator.createValidAluno()));
        List<Aluno> alunoList = List.of(AlunoCreator.createValidAluno());

        BDDMockito.when(alunoServiceMock.findAllPaginated(ArgumentMatchers.any()))
                .thenReturn(alunoPage);
        
        BDDMockito.when(alunoServiceMock.findAll())
                .thenReturn(alunoList);

        BDDMockito.when(alunoServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(AlunoCreator.createValidAluno());
    }

    @Test
    @DisplayName("Find All retorna uma lista de Alunos quando ocorrer sucesso")
    void findAll_ReturnsListOfAlunos_WhenSuccessful() {
        Aluno validAluno = AlunoCreator.createValidAluno();
        List<Aluno> alunosList = alunoController.findAll().getBody();

        Assertions.assertThat(alunosList).isNotNull();
        Assertions.assertThat(alunosList).isNotEmpty().hasSize(1);
        Assertions.assertThat(alunosList).contains(validAluno);
        Assertions.assertThat(alunosList.get(0)).isEqualTo(validAluno);
    }

    @Test
    @DisplayName("Find All Paginated retorna uma lista paginada de Alunos quando ocorrer sucesso")
    void findAllPaginated_ReturnsListOfAlunosInsidePageObject_WhenSuccessful() {
        Aluno validAluno = AlunoCreator.createValidAluno();
        //ArgumentMatcher.any() -> null como parametro (irrelevante, pois está mockado)
        Page<Aluno> alunoPage = alunoController.findAllPaginated(null).getBody();

        Assertions.assertThat(alunoPage).isNotNull();
        Assertions.assertThat(alunoPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(alunoPage.toList()).contains(validAluno);
        Assertions.assertThat(alunoPage.toList().get(0)).isEqualTo(validAluno);
    }

    @Test
    @DisplayName("Find By Id retorna uma aluno quando ocorrer sucesso")
    void findById_ReturnsAluno_WhenSuccessful() {
        Aluno validAluno = AlunoCreator.createValidAluno();

        Aluno aluno = alunoController.findById(1L).getBody();

        Assertions.assertThat(aluno).isNotNull();
        Assertions.assertThat(aluno.getId()).isNotNull();
        Assertions.assertThat(aluno.getId()).isEqualTo(validAluno.getId());
        Assertions.assertThat(aluno).isEqualTo(validAluno);

    }

    @Test
    @DisplayName("Find By Id lança Bad Request Exception quando nenhum aluno for encontrado pelo ID")
    void findById_ReturnsBadRequestException_WhenNoAlunoAreFound() {
        String message = "Aluno não encontrado";
        BDDMockito.when(alunoServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new NotFoundException(message));

//        // when
//        Throwable throwable = Assertions.catchThrowable(() -> alunoController.findById(1L));
//
//        // then
//        Assertions.assertThat(throwable)
//                .isInstanceOf(NotFoundException.class)
//                .hasFieldOrPropertyWithValue("message", message);

        Assertions.assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> alunoController.findById(1L))
                .withMessageContaining(message);
    }

    @Test
    void findByNome() {
    }

    @Test
    void findByMatricula() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}