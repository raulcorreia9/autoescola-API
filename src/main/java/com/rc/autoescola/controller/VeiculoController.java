package com.rc.autoescola.controller;

import com.rc.autoescola.DTO.AlunoUpdateDTO;
import com.rc.autoescola.DTO.VeiculoCreateDTO;
import com.rc.autoescola.DTO.VeiculoUpdateDTO;
import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.models.Veiculo;
import com.rc.autoescola.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/veiculo")
public class VeiculoController {
    private final VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<Veiculo> save(@RequestBody @Valid VeiculoCreateDTO veiculoCreateDTO) {
        return new ResponseEntity<>(veiculoService.save(veiculoCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAll() {
        return ResponseEntity.ok(veiculoService.findAll());
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Veiculo>> findAllPaginated(Pageable pageable) {
        return ResponseEntity.ok(veiculoService.findAllPaginated(pageable));
    }

    @GetMapping("/placa")
    public ResponseEntity<Veiculo> findByMatricula(@RequestParam String placa) {
        return ResponseEntity.ok(veiculoService.findByPlaca(placa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        veiculoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Veiculo> update(@Valid @RequestBody VeiculoUpdateDTO veiculoUpdateDTO) {
        return new ResponseEntity<>(veiculoService.update(veiculoUpdateDTO), HttpStatus.OK);
    }

}
