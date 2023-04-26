package com.rc.autoescola.service;

import com.rc.autoescola.DTO.AlunoDTO;
import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Aluno save(AlunoDTO alunoDTO) {
        Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);
        return alunoRepository.save(aluno);
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

}
