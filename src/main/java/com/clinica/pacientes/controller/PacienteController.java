package com.clinica.pacientes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.pacientes.domain.dto.PacienteActividadDTO;
import com.clinica.pacientes.domain.dto.PacienteDTO;
import com.clinica.pacientes.domain.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService svc;

    @GetMapping
    public List<PacienteDTO> getAll() {
        return svc.obtenerTodo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getById(@PathVariable Long id) {
        Optional<PacienteDTO> p = svc.obtenerPorId(id);
        return p.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> create(@RequestBody PacienteDTO dto) {
        return ResponseEntity.ok(svc.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @RequestBody PacienteDTO dto) {
        return ResponseEntity.ok(svc.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (svc.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/actividad")
    public ResponseEntity<PacienteActividadDTO> getActividad(@PathVariable Long id) {
        return ResponseEntity.ok(svc.obtenerActividad(id));
    }

    @GetMapping("/activos")
    public List<PacienteDTO> getActivos() {
        return svc.obtenerActivos();
    }

    @GetMapping("/frecuentes")
    public List<PacienteDTO> getFrecuentes(@RequestParam(defaultValue = "5") int min) {
        return svc.obtenerFrecuentes(min);
    }
}