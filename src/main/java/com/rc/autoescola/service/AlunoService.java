package com.rc.autoescola.service;

import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final AlunoRepository alunoRepository;

}
