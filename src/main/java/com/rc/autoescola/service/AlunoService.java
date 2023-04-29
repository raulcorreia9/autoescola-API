package com.rc.autoescola.service;

import com.rc.autoescola.DTO.AlunoDTO;
import com.rc.autoescola.DTO.AlunoUpdateDTO;
import com.rc.autoescola.exception.NotFoundException;
import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ModelMapper modelMapper;

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Page<Aluno> findAllPaginated(Pageable pageable) {
        return alunoRepository.findAll(pageable);
    }

    public Aluno findById(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado."));
    }

    public List<Aluno> findByName(String nome) {
        return alunoRepository.findAlunoByNomeContainingIgnoreCase(nome);
    }

    public void delete(Long id) {
       alunoRepository.delete(findById(id));
    }

    public Aluno update(AlunoUpdateDTO alunoUpdateDTO) {
        findById(alunoUpdateDTO.getId());
        Aluno alunoToUpdate = modelMapper.map(alunoUpdateDTO, Aluno.class);
        return alunoRepository.save(alunoToUpdate);
    }

    @Transactional
    public Aluno save(AlunoDTO alunoDTO) {
        Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);
        return alunoRepository.save(aluno);
    }

}
