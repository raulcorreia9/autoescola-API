package com.rc.autoescola.controller;

import com.rc.autoescola.DTO.AlunoCreateDTO;
import com.rc.autoescola.DTO.AlunoUpdateDTO;
import com.rc.autoescola.exception.NotFoundException;
import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.service.AlunoService;
import com.rc.autoescola.util.AlunoCreator;
import com.rc.autoescola.util.AlunoPatchDTOCreator;
import com.rc.autoescola.util.AlunoPostDTOCreator;
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

        BDDMockito.when(alunoServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(alunoList);

        BDDMockito.when(alunoServiceMock.findByMatricula(ArgumentMatchers.anyString()))
                .thenReturn(AlunoCreator.createValidAluno());

        BDDMockito.when(alunoServiceMock.save(ArgumentMatchers.any()))
                .thenReturn(AlunoCreator.createValidAluno());

        BDDMockito.doNothing().when(alunoServiceMock).delete(ArgumentMatchers.anyLong());

        BDDMockito.when(alunoServiceMock.update(ArgumentMatchers.any()))
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
    @DisplayName("Find All retorna uma lista vazia quando nenhum aluno for encontrado")
    void findAll_ReturnsEmptyListOfAlunos_WhenNoAlunoAreFound() {
        BDDMockito.when(alunoServiceMock.findAll())
                .thenReturn(List.of());

        List<Aluno> alunosList = alunoController.findAll().getBody();

        Assertions.assertThat(alunosList).isNotNull().isEmpty();
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
    @DisplayName("Find By Nome retorna uma lista de Alunos quando ocorrer sucesso")
    void findByNome_ReturnsListOfAlunos_WhenSuccessful() {
        Aluno validAluno = AlunoCreator.createValidAluno();
        List<Aluno> alunosList = alunoController.findByNome("asdas").getBody();

        Assertions.assertThat(alunosList).isNotNull();
        Assertions.assertThat(alunosList).isNotEmpty().hasSize(1);
        Assertions.assertThat(alunosList).contains(validAluno);
        Assertions.assertThat(alunosList.get(0)).isEqualTo(validAluno);
    }

    @Test
    @DisplayName("Find By Nome retorna uma lista vazia quando nenhum aluno for encontrado")
    void findByNome_ReturnsEmptyListOfAlunos_WhenNoAlunoAreFound() {
        BDDMockito.when(alunoServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of());

        List<Aluno> alunosList = alunoController.findByNome("asdas").getBody();

        Assertions.assertThat(alunosList).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("Find By Matricula retorna um Aluno quando ocorrer sucesso")
    void findByMatricula_ReturnsAluno_WhenSuccessful() {
        Aluno validAluno = AlunoCreator.createValidAluno();
        Aluno aluno = alunoController.findByMatricula("111").getBody();

        Assertions.assertThat(aluno).isNotNull();
        Assertions.assertThat(aluno.getMatricula()).isEqualTo(validAluno.getMatricula());
        Assertions.assertThat(aluno).isEqualTo(validAluno);
    }

    @Test
    @DisplayName("Find By Matricula lança Bad Request Exception quando nenhum aluno for encontrado pelo ID")
    void findByMatricula_ReturnsBadRequestException_WhenNoAlunoAreFound() {
        String message = "Aluno não encontrado";
        BDDMockito.when(alunoServiceMock.findByMatricula(ArgumentMatchers.anyString()))
                .thenThrow(new NotFoundException(message));

        Assertions.assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> alunoController.findByMatricula("111"))
                .withMessageContaining(message);
    }

    @Test
    @DisplayName("Save persiste o aluno no banco de dados quando ocorrer sucesso")
    void save_PersistsAluno_WhenSuccessful() {
        AlunoCreateDTO alunoPostDTO = AlunoPostDTOCreator.createAlunoPostDTO();

        Aluno alunoSaved = alunoController.save(alunoPostDTO).getBody();

        Assertions.assertThat(alunoSaved).isNotNull();
        Assertions.assertThat(alunoSaved.getId()).isNotNull();
        Assertions.assertThat(alunoSaved.getMatricula().length()).isEqualTo(10);
        Assertions.assertThat(alunoSaved).isEqualTo(AlunoCreator.createValidAluno());
    }

    @Test
    @DisplayName("Save lança ConstraintViolationException quando atributo obrigatorio de Aluno não for informado")
    void save_ThrowsConstraintViolationException_WhenRequiredFieldIsEmpty(){
        BDDMockito.when(alunoServiceMock.save(ArgumentMatchers.any()))
                .thenThrow(ConstraintViolationException.class);


        AlunoCreateDTO alunoValidPostDTO = AlunoPostDTOCreator.createAlunoPostDTO();

        AlunoCreateDTO AlunoPostDTOWithoutNameField = AlunoCreateDTO.builder()
                .email(alunoValidPostDTO.getEmail())
                .matricula(alunoValidPostDTO.getMatricula())
                .build();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> alunoController.save(AlunoPostDTOWithoutNameField));
    }

    @Test
    @DisplayName("Delete remove Aluno quando ocorrer sucesso")
    void delete_RemovesAluno_WhenSuccessful() {
        Assertions.assertThatCode(() -> alunoController.delete(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> delete = alunoController.delete(1L);

        Assertions.assertThat(delete).isNotNull();
        Assertions.assertThat(delete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Update atualiza um Aluno quando ocorrer sucesso")
    void update_UpdatesAluno_WhenSuccessful() {
        AlunoUpdateDTO alunoPatchDTO = AlunoPatchDTOCreator.createAlunoPatchDTO();

        Aluno alunoUpdated = alunoController.update(alunoPatchDTO).getBody();

        System.out.println(alunoUpdated);

        Assertions.assertThat(alunoUpdated).isNotNull();
        Assertions.assertThat(alunoUpdated.getId()).isNotNull();
        Assertions.assertThat(alunoUpdated.getMatricula().length()).isEqualTo(10);
        Assertions.assertThat(alunoUpdated).isEqualTo(AlunoCreator.createValidAluno());
    }
}