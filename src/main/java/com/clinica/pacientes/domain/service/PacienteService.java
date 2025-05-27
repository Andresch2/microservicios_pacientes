package com.clinica.pacientes.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinica.pacientes.domain.dto.PacienteActividadDTO;
import com.clinica.pacientes.domain.dto.PacienteDTO;
import com.clinica.pacientes.domain.repository.IPacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private IPacienteRepository repo;

    public List<PacienteDTO> obtenerTodo() {
        return repo.getAll();
    }

    public Optional<PacienteDTO> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public PacienteDTO guardar(PacienteDTO dto) {
        return repo.save(dto);
    }

    public PacienteDTO actualizar(Long id, PacienteDTO dto) {
        return repo.update(id, dto);
    }

    public boolean eliminar(Long id) {
        return repo.delete(id);
    }

    public List<PacienteDTO> obtenerActivos() {
        return repo.getActivos();
    }

    public List<PacienteDTO> obtenerFrecuentes(int min) {
        return repo.getFrecuentes(min);
    }

    public PacienteActividadDTO obtenerActividad(Long pacienteId) {
        return repo.getActividad(pacienteId);
    }
}