package com.rc.autoescola.controller;

import com.rc.autoescola.DTO.AlunoDTO;
import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/autoescola")
public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        return ResponseEntity.ok(alunoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@Valid @RequestBody AlunoDTO alunoDTO) {
        return new ResponseEntity<>(alunoService.save(alunoDTO), HttpStatus.CREATED);
    }
}
