package com.rc.autoescola.service;

import com.rc.autoescola.DTO.AlunoCreateDTO;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

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

    public Aluno findByMatricula(String matricula) {
        return alunoRepository.findAlunoByMatricula(matricula)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
    }

    public void delete(Long id) {
       alunoRepository.delete(findById(id));
    }

    @Transactional
    public Aluno save(AlunoCreateDTO alunoCreateDTO) {
        Aluno aluno = modelMapper.map(alunoCreateDTO, Aluno.class);
        aluno.setMatricula(generateMatriculaAluno());
        return alunoRepository.save(aluno);
    }

    public Aluno update(AlunoUpdateDTO alunoUpdateDTO) {
        Aluno alunoSaved = findById(alunoUpdateDTO.getId());
        Aluno alunoToUpdate = modelMapper.map(alunoUpdateDTO, Aluno.class);
        alunoToUpdate.setMatricula(alunoSaved.getMatricula());

        return alunoRepository.save(alunoToUpdate);
    }

    //Utils
    private String generateMatriculaAluno() {
        //Formato da matrícula: Ano atual + 6 dígitos aleatorios
        int year = LocalDate.now().getYear();
        String randomDigits = String.format("%06d", new Random().nextInt(999999));
        return year + randomDigits;
    }

}
