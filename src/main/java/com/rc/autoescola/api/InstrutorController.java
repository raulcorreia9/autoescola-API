package com.rc.autoescola.api;

import com.rc.autoescola.domain.models.Instrutor;
import com.rc.autoescola.domain.repository.InstrutorRepository;
import com.rc.autoescola.domain.service.CadastroInstrutorService;
import com.rc.autoescola.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instrutor")
public class InstrutorController {

    private final InstrutorRepository instrutorRepository;
    private final CadastroInstrutorService cadastroInstrutorService;

    @GetMapping
    public List<Instrutor> listar() {
        return instrutorRepository.findAll();
    }

    @GetMapping("/{instrutorId}")
    public ResponseEntity<Instrutor> buscar(@PathVariable Long instrutorId) {
        Optional<Instrutor> instrutorEncontrado = instrutorRepository.findById(instrutorId);
        return instrutorEncontrado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Instrutor> adicionar(@Valid @RequestBody Instrutor instrutor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroInstrutorService.salvar(instrutor));
    }

    @DeleteMapping("/{instrutorId}")
    public ResponseEntity<?> remover(@PathVariable Long instrutorId) {
        try {
            cadastroInstrutorService.remover(instrutorId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
