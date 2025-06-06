
package com.clinica.pacientes.external.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clinica.pacientes.external.dto.ConsultaDTO;

@FeignClient(name = "consultas", url = "http://localhost:8083")
public interface ConsultaClient {
    @GetMapping("/consulta/paciente/{id}")
    List<ConsultaDTO> getConsultasPorPaciente(@PathVariable("id") Long pacienteId);
}
