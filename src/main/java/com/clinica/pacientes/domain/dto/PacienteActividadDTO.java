
package com.clinica.pacientes.domain.dto;

import com.clinica.pacientes.external.dto.CitaDTO;
import com.clinica.pacientes.external.dto.ConsultaDTO;

import java.util.List;

public class PacienteActividadDTO {
    private List<CitaDTO> citas;
    private List<ConsultaDTO> consultas;

    public PacienteActividadDTO(List<CitaDTO> citas, List<ConsultaDTO> consultas) {
        this.citas = citas;
        this.consultas = consultas;
    }

    public List<CitaDTO> getCitas() {
        return citas;
    }

    public List<ConsultaDTO> getConsultas() {
        return consultas;
    }
}
