package com.rc.autoescola.service;

import com.rc.autoescola.DTO.AlunoUpdateDTO;
import com.rc.autoescola.DTO.VeiculoCreateDTO;
import com.rc.autoescola.DTO.VeiculoUpdateDTO;
import com.rc.autoescola.exception.NotFoundException;
import com.rc.autoescola.models.Aluno;
import com.rc.autoescola.models.Veiculo;
import com.rc.autoescola.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final ModelMapper modelMapper;

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    public Page<Veiculo> findAllPaginated(Pageable pageable) {
        return veiculoRepository.findAll(pageable);
    }

    @Transactional
    public Veiculo save(VeiculoCreateDTO veiculoCreateDTO) {
        Veiculo veiculo = modelMapper.map(veiculoCreateDTO, Veiculo.class);
        return veiculoRepository.save(veiculo);
    }

    public Veiculo findByPlaca(String placa) {
        return veiculoRepository.findVeiculoByPlaca(placa).orElseThrow(() -> new NotFoundException("Veículo não encontrado"));
    }

    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id).orElseThrow(() -> new NotFoundException("Veículo não encontrado."));
    }

    public void delete(Long id) {
        veiculoRepository.delete(findById(id));
    }

    @Transactional
    public Veiculo update(VeiculoUpdateDTO veiculoUpdateDTO) {
        Veiculo veiculoSaved = findById(veiculoUpdateDTO.getId());
        BeanUtils.copyProperties(veiculoUpdateDTO, veiculoSaved);

        return veiculoRepository.save(veiculoSaved);
    }
}
