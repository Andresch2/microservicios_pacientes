package com.clinica.pacientes.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.clinica.pacientes.domain.dto.PacienteActividadDTO;
import com.clinica.pacientes.domain.dto.PacienteDTO;
import com.clinica.pacientes.domain.repository.IPacienteRepository;
import com.clinica.pacientes.external.client.CitaClient;
import com.clinica.pacientes.external.client.ConsultaClient;
import com.clinica.pacientes.infrastructure.crud.PacienteCrudRepository;
import com.clinica.pacientes.infrastructure.entity.Paciente;
import com.clinica.pacientes.infrastructure.mapper.PacienteMapper;

@Repository
public class PacienteImpl implements IPacienteRepository {

    @Autowired
    private PacienteCrudRepository crud;

    @Autowired
    private PacienteMapper mapper;

    @Autowired
    private CitaClient citaClient;

    @Autowired
    private ConsultaClient consultaClient;

    @Override
    public List<PacienteDTO> getAll() {
        return mapper.toDtos(crud.findAll());
    }

    @Override
    public Optional<PacienteDTO> findById(Long id) {
        return crud.findById(id)
        .map(mapper::toDto);
    }

    @Override
    public PacienteDTO save(PacienteDTO dto) {
        if (dto.getActive() == null) {
            dto.setActive(false);
        }
        Paciente entity = mapper.toEntity(dto);
        Paciente saved = crud.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public PacienteDTO update(Long id, PacienteDTO dto) {
        Paciente updated = mapper.toEntity(dto);
        updated.setId(id);
        Paciente saved = crud.save(updated);
        return mapper.toDto(saved);
    }

    @Override
    public boolean delete(Long id) {
        if (!crud.existsById(id)) {
            return false;
        }
        crud.deleteById(id);
        return true;
    }

    @Override
public List<PacienteDTO> getActivos() {
    return crud.findAll().stream()
        .filter(Paciente::getActivo)
        .map(mapper::toDto)
        .toList();
}

@Override
public List<PacienteDTO> getFrecuentes(int minCitas) {
    return crud.findAll().stream()
        .filter(p -> {
            try {
                return citaClient.getCitasPorPaciente(p.getId()).size() >= minCitas;
            } catch (Exception e) {
                return false;
            }
        })
        .map(mapper::toDto)
        .toList();
}


    @Override
    public PacienteActividadDTO getActividad(Long pacienteId) {
        return new PacienteActividadDTO(
            citaClient.getCitasPorPaciente(pacienteId),
            consultaClient.getConsultasPorPaciente(pacienteId)
        );
    }
}
