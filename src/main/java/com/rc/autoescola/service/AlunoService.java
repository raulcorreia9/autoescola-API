package com.rc.autoescola.service;

import com.rc.autoescola.DTO.AlunoCreateDTO;
import com.rc.autoescola.DTO.AlunoUpdateDTO;
import com.rc.autoescola.exception.NotFoundException;
import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.models.Veiculo;
import com.rc.autoescola.repository.AlunoRepository;
import com.rc.autoescola.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final VeiculoRepository veiculoRepository;
    private final ModelMapper modelMapper;

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

    public List<Aluno> findAllAlunosForVeiculoByPlaca(String placa) {
        return alunoRepository.findAllAlunosForVeiculoByPlaca(placa);
    }

    public void delete(Long id) {
       alunoRepository.delete(findById(id));
    }

    @Transactional
    public Aluno save(AlunoCreateDTO alunoCreateDTO) {

        Optional<Veiculo> veiculo = veiculoRepository.findById(alunoCreateDTO.getVeiculo().getId());

        Aluno aluno = modelMapper.map(alunoCreateDTO, Aluno.class);

        if(veiculo.isPresent()) {
            aluno.setVeiculo(veiculo.get());
        } else {
            throw new NotFoundException("Veículo não encontrado");
        }

        aluno.setMatricula(generateMatriculaAluno());

        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno update(AlunoUpdateDTO alunoUpdateDTO) {
        Aluno alunoSaved = findById(alunoUpdateDTO.getId());

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

    //Utils
    private String generateMatriculaAluno() {
        //Formato da matrícula: Ano atual + 6 dígitos aleatorios
        int year = LocalDate.now().getYear();
        String randomDigits = String.format("%06d", new Random().nextInt(999999));
        return year + randomDigits;
    }

}
