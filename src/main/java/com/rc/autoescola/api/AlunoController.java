package com.rc.autoescola.api;

import com.rc.autoescola.DTO.AlunoCreateDTO;
import com.rc.autoescola.DTO.AlunoGetDTO;
import com.rc.autoescola.DTO.AlunoUpdateDTO;
import com.rc.autoescola.assembler.AlunoAssembler;
import com.rc.autoescola.domain.models.Aluno;
import com.rc.autoescola.domain.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/aluno")
public class AlunoController {
    private final AlunoService alunoService;
    private final AlunoAssembler alunoAssembler;

    @GetMapping
    public List<AlunoGetDTO> findAll() {
        List<Aluno> alunos = alunoService.findAll();
        return alunoAssembler.toCollectionAlunoGetDTO(alunos);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Aluno>> findAllPaginated(Pageable pageable) {
        return ResponseEntity.ok(alunoService.findAllPaginated(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Aluno>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(alunoService.findByName(nome));
    }

    @GetMapping("/matricula")
    public ResponseEntity<Aluno> findByMatricula(@RequestParam String matricula) {
        return ResponseEntity.ok(alunoService.findByMatricula(matricula));
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@Valid @RequestBody AlunoCreateDTO alunoCreateDTO) {
        return new ResponseEntity<>(alunoService.save(alunoCreateDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{alunoId}")
    public ResponseEntity<Aluno> update(@Valid @RequestBody AlunoUpdateDTO alunoUpdateDTO,
                                        @PathVariable Long alunoId) {
        return ResponseEntity.ok(alunoService.update(alunoUpdateDTO, alunoId));
    }
}
