package com.rc.autoescola.controller;

import com.rc.autoescola.DTO.AlunoCreateDTO;
import com.rc.autoescola.DTO.AlunoUpdateDTO;
import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.service.AlunoService;
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

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        return ResponseEntity.ok(alunoService.findAll());
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
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Aluno> update(@Valid @RequestBody AlunoUpdateDTO alunoUpdateDTO) {
        return new ResponseEntity<>(alunoService.update(alunoUpdateDTO), HttpStatus.OK);
    }


    //    @ExceptionHandler(NotFoundException.class)
    //    public ResponseEntity<ErrorResponseDetails> handleAlunoNaotFoundException(NotFoundException ex) {
    //        ErrorResponseDetails error = ErrorResponseDetails.builder()
    //                .status(HttpStatus.NOT_FOUND.value())
    //                .message(ex.getMessage())
    //                .timestamp(LocalDateTime.now())
    //                .build();
    //        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    //    }
}
