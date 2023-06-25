package com.rc.autoescola.domain.service;

import com.rc.autoescola.DTO.AlunoCreateDTO;
import com.rc.autoescola.DTO.AlunoSimpleDTO;
import com.rc.autoescola.DTO.AlunoUpdateDTO;
import com.rc.autoescola.assembler.AlunoAssembler;
import com.rc.autoescola.domain.models.Veiculo;
import com.rc.autoescola.domain.repository.AlunoRepository;
import com.rc.autoescola.domain.repository.VeiculoRepository;
import com.rc.autoescola.exception.NotFoundException;
import com.rc.autoescola.domain.models.Aluno;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final VeiculoRepository veiculoRepository;
    private final AlunoAssembler alunoAssembler;

    public List<Aluno> findAll() {
//        List<Aluno> alunos = alunoRepository.findAll();
//        return alunos.stream().map(aluno -> new AlunoMinDTO(aluno)).toList();
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

    public List<AlunoSimpleDTO> findAllAlunosForVeiculoByPlaca(String placa) {
        List<Aluno> alunos = alunoRepository.findAllAlunosForVeiculoByPlaca(placa);
        return alunos.stream().map(AlunoSimpleDTO::new).collect(Collectors.toList());
    }

    public void delete(Long id) {
       alunoRepository.delete(findById(id));
    }

    @Transactional
    public Aluno save(AlunoCreateDTO alunoCreateDTO) {

        if(alunoCreateDTO.getVeiculo() != null) {
            Optional<Veiculo> veiculo = veiculoRepository.findById(alunoCreateDTO.getVeiculo().getId());

            if(veiculo.isPresent()) {
                alunoCreateDTO.setVeiculo(veiculo.get());
            } else {
                throw new NotFoundException("Veículo não encontrado");
            }
        }

        Aluno aluno = alunoAssembler.toEntityFromCreate(alunoCreateDTO);

        aluno.generateMatriculaAluno();

        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno update(AlunoUpdateDTO alunoUpdateDTO, Long alunoId) {
        Aluno alunoSaved = findById(alunoId);

        alunoSaved.setNome(alunoUpdateDTO.getNome() != null ? alunoUpdateDTO.getNome() : alunoSaved.getNome());
        alunoSaved.setEmail(alunoUpdateDTO.getEmail() != null ? alunoUpdateDTO.getEmail() : alunoSaved.getEmail());

        if(alunoUpdateDTO.getVeiculo() != null) {
            Optional<Veiculo> veiculo = veiculoRepository.findById(alunoUpdateDTO.getVeiculo().getId());
            if(veiculo.isPresent()) {
                alunoSaved.setVeiculo(veiculo.get());
            } else {
                throw new NotFoundException("Veículo não encontrado");
            }
        }

        return alunoRepository.save(alunoSaved);
    }

}
