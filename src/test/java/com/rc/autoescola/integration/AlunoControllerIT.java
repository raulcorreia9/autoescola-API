package com.rc.autoescola.integration;

import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.repository.AlunoRepository;
import com.rc.autoescola.util.AlunoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
    @DisplayName("findById returns anime when successful")
    void findById_ReturnsAnime_WhenSuccessful() {
        Aluno alunoSaved = alunoRepository.save(AlunoCreator.createAlunoToBeSaved());

        Long expectedField = alunoSaved.getId();

        Aluno aluno = testRestTemplate.getForObject("/api/aluno/{id}", Aluno.class, expectedField);

        Assertions.assertThat(aluno).isNotNull();

        Assertions.assertThat(aluno.getId()).isNotNull().isEqualTo(expectedField);
    }
}
