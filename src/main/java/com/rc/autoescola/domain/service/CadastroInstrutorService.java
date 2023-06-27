package com.rc.autoescola.domain.service;

import com.rc.autoescola.domain.models.Instrutor;
import com.rc.autoescola.domain.repository.InstrutorRepository;
import com.rc.autoescola.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadastroInstrutorService {

    private final InstrutorRepository instrutorRepository;

    public Instrutor salvar(Instrutor instrutor) {
        return instrutorRepository.save(instrutor);
    }

    public void remover(Long instrutorId) {
        try {
            instrutorRepository.findById(instrutorId);
        }catch(EmptyResultDataAccessException ex) {
            throw new NotFoundException(String.format("Não foi encontrado instrutor com o ID = %d", instrutorId));
        }
    }

}
