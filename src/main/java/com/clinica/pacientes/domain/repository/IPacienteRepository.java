package com.clinica.pacientes.domain.repository;

import java.util.List;
import java.util.Optional;

import com.clinica.pacientes.domain.dto.PacienteActividadDTO;
import com.clinica.pacientes.domain.dto.PacienteDTO;

public interface IPacienteRepository {
    List<PacienteDTO> getAll();
    Optional<PacienteDTO> findById(Long id);
    PacienteDTO save(PacienteDTO dto);
    PacienteDTO update(Long id, PacienteDTO dto);
    boolean delete(Long id);
    List<PacienteDTO> getActivos();
    List<PacienteDTO> getFrecuentes(int minCitas);
    PacienteActividadDTO getActividad(Long pacienteId);
}