package com.clinica.pacientes.infrastructure.repositories;

import java.util.List;
import java.util.Optional;

import com.clinica.pacientes.domain.dto.PacienteDTO;

public interface IPaciente {
    List<PacienteDTO> getAll();
    Optional<PacienteDTO> findById(Long id);
    PacienteDTO save(PacienteDTO dto);
    PacienteDTO update(Long id, PacienteDTO dto);
    boolean delete(Long id);
    List<PacienteDTO> getFrecuentes(int minCitas);
    List<PacienteDTO> getActivos();
}
