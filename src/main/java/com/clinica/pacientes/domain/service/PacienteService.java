package com.clinica.pacientes.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.pacientes.domain.dto.PacienteActividadDTO;
import com.clinica.pacientes.domain.dto.PacienteDTO;
import com.clinica.pacientes.domain.repository.IPaciente;
import com.clinica.pacientes.external.client.CitaClient;
import com.clinica.pacientes.external.client.ConsultaClient;
import com.clinica.pacientes.infrastructure.entity.Paciente;
import com.clinica.pacientes.infrastructure.mapper.PacienteMapper;

@Service
public class PacienteService {

    @Autowired
    private IPaciente crud;

    @Autowired
    private PacienteMapper mapper;

    @Autowired
    private CitaClient citaClient;

    @Autowired
    private ConsultaClient consultaClient;

    public List<PacienteDTO> obtenerTodo() {
        return crud.findAll()
                .stream()
                .map(mapper::toPacienteDTO)
                .collect(Collectors.toList());
    }

    public Optional<PacienteDTO> obtenerPorId(Long id) {
        return crud.findById(id)
                .map(mapper::toPacienteDTO);
    }

    public PacienteDTO guardar(PacienteDTO dto) {
        if (dto.getActive() == null) {
            dto.setActive(false);
        }
        Paciente p = mapper.toPaciente(dto);
        return mapper.toPacienteDTO(crud.save(p));
    }

    public PacienteDTO actualizar(Long id, PacienteDTO dto) {
        if (crud.existsById(id)) {
            if (dto.getActive() == null) {
                dto.setActive(false);
            }
            Paciente p = mapper.toPaciente(dto);
            p.setId(id);
            return mapper.toPacienteDTO(crud.save(p));
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (crud.existsById(id)) {
            crud.deleteById(id);
            return true;
        }
        return false;
    }

    public PacienteActividadDTO obtenerActividad(Long pacienteId) {
        return new PacienteActividadDTO(
            citaClient.getCitasPorPaciente(pacienteId),
            consultaClient.getConsultasPorPaciente(pacienteId)
        );
    }

    public List<PacienteDTO> obtenerActivos() {
        return crud.findAll().stream()
                .filter(p -> Boolean.TRUE.equals(p.getActivo()))
                .map(mapper::toPacienteDTO)
                .collect(Collectors.toList());
    }

    public List<PacienteDTO> obtenerFrecuentes(int min) {
        return crud.findAll().stream()
                .filter(paciente -> {
                    try {
                        return citaClient.getCitasPorPaciente(paciente.getId()).size() >= min;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .map(mapper::toPacienteDTO)
                .collect(Collectors.toList());
    }
}