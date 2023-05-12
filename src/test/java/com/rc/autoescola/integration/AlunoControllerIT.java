package com.rc.autoescola.integration;

import com.rc.autoescola.DTO.AlunoCreateDTO;
import com.rc.autoescola.DTO.AlunoUpdateDTO;
import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.repository.AlunoRepository;
import com.rc.autoescola.util.AlunoCreator;
import com.rc.autoescola.util.AlunoPatchDTOCreator;
import com.rc.autoescola.util.AlunoPostDTOCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AlunoControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("listAll returns list of anime when successful")
    void listAll_ReturnsListOfAnimes_WhenSuccessful() {
        Aluno alunoSaved = alunoRepository.save(AlunoCreator.createAlunoToBeSaved());

        String expectedField = alunoSaved.getMatricula();

        List<Aluno> alunos = testRestTemplate.exchange("/api/aluno", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Aluno>>() {
                }).getBody();

        Assertions.assertThat(alunos)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(alunos.get(0).getMatricula()).isEqualTo(expectedField);
    }

    @Test
    @DisplayName("findById retorna um anime quando ocorrer sucesso")
    void findById_ReturnsAnime_WhenSuccessful() {
        Aluno alunoSaved = alunoRepository.save(AlunoCreator.createAlunoToBeSaved());

        Long expectedField = alunoSaved.getId();

        Aluno aluno = testRestTemplate.getForObject("/api/aluno/{id}", Aluno.class, expectedField);

        Assertions.assertThat(aluno).isNotNull();

        Assertions.assertThat(aluno.getId()).isNotNull().isEqualTo(expectedField);
    }

    @Test
    @DisplayName("Find By Nome retorna uma lista de Alunos quando ocorrer sucesso")
    void findByNome_ReturnsListOfAlunos_WhenSuccessful() {
        Aluno alunoSaved = alunoRepository.save(AlunoCreator.createAlunoToBeSaved());
        String expectedField = alunoSaved.getNome();

        String url = String.format("/api/aluno/nome?nome=%s", expectedField);

        List<Aluno> alunos = testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Aluno>>() {}).getBody();

        Assertions.assertThat(alunos)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .contains(alunoSaved);
        Assertions.assertThat(alunos.get(0).getNome()).isEqualTo(expectedField);
    }

    @Test
    @DisplayName("Find By Nome retorna uma lista vazia quando nenhum aluno for encontrado")
    void findByNome_ReturnsEmptyListOfAlunos_WhenNoAlunoAreFound() {

        String url = String.format("/api/aluno/nome?nome=%s", "sdaas");

        List<Aluno> alunos = testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Aluno>>() {}).getBody();

        Assertions.assertThat(alunos).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("Find By Matricula retorna um Aluno quando ocorrer sucesso")
    void findByMatricula_ReturnsAluno_WhenSuccessful() {
        Aluno alunoSaved = alunoRepository.save(AlunoCreator.createAlunoToBeSaved());
        String expectedField = alunoSaved.getMatricula();

        String url = String.format("/api/aluno/matricula?matricula=%s", expectedField);

        Aluno aluno = testRestTemplate.getForObject(url, Aluno.class, expectedField);

        Assertions.assertThat(aluno).isNotNull();
        Assertions.assertThat(aluno.getMatricula()).isEqualTo(alunoSaved.getMatricula());
        Assertions.assertThat(aluno.getMatricula().length()).isEqualTo(10);
    }

    @Test
    @DisplayName("Save persiste o aluno no banco de dados quando ocorrer sucesso")
    void save_PersistsAluno_WhenSuccessful() {
        AlunoCreateDTO alunoPostDTO = AlunoPostDTOCreator.createAlunoPostDTO();

        ResponseEntity<Aluno> alunoSaved = testRestTemplate.postForEntity("/api/aluno", alunoPostDTO, Aluno.class);

        Assertions.assertThat(alunoSaved.getBody()).isNotNull();
        Assertions.assertThat(alunoSaved.getBody().getId()).isNotNull();
        Assertions.assertThat(alunoSaved.getBody().getMatricula().length()).isEqualTo(10);
        Assertions.assertThat(alunoSaved.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Update atualiza um Aluno quando ocorrer sucesso")
    void update_UpdatesAluno_WhenSuccessful() {
        Aluno alunoSaved = alunoRepository.save(AlunoCreator.createAlunoToBeSaved());
        alunoSaved.setNome("Vinicius");

        ResponseEntity<Aluno> alunoUpdated = testRestTemplate.exchange("/api/aluno",
                HttpMethod.PUT,new HttpEntity<>(alunoSaved), Aluno.class);

        Assertions.assertThat(alunoUpdated.getBody()).isNotNull();
        Assertions.assertThat(alunoUpdated.getBody().getId()).isNotNull();
        Assertions.assertThat(alunoUpdated.getBody().getNome()).isEqualTo("Vinicius");
        Assertions.assertThat(alunoUpdated.getBody().getMatricula().length()).isEqualTo(10);
        Assertions.assertThat(alunoUpdated.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Delete remove Aluno quando ocorrer sucesso")
    void delete_RemovesAluno_WhenSuccessful() {
        Aluno alunoSaved = alunoRepository.save(AlunoCreator.createAlunoToBeSaved());

        ResponseEntity<Void> alunoResponseEntity = testRestTemplate.exchange("/api/aluno/{id}",
                HttpMethod.DELETE, null, Void.class, alunoSaved.getId());

        Assertions.assertThat(alunoResponseEntity).isNotNull();
        Assertions.assertThat(alunoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
