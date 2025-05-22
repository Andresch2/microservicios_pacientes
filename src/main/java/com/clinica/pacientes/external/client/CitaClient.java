
package com.clinica.pacientes.external.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clinica.pacientes.external.dto.CitaDTO;

@FeignClient(name = "citas", url = "https://citaspoo-production.up.railway.app")
public interface CitaClient {
    @GetMapping("/citas/paciente/{pacienteId}")
    List<CitaDTO> getCitasPorPaciente(@PathVariable("pacienteId") Long pacienteId);
}
