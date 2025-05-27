package com.clinica.pacientes.infrastructure.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.clinica.pacientes.domain.dto.PacienteDTO;
import com.clinica.pacientes.infrastructure.entity.Paciente;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    @Mapping(source = "nombre",          target = "firstName")
    @Mapping(source = "apellido",        target = "lastName")
    @Mapping(source = "cedula",          target = "idNumber")
    @Mapping(source = "email",           target = "email")
    @Mapping(source = "telefono",        target = "phone")
    @Mapping(source = "fechaNacimiento", target = "birthDate")
    @Mapping(source = "activo",          target = "active")
    PacienteDTO toDto(Paciente paciente);

    List<PacienteDTO> toDtos(List<Paciente> pacientes);

    @InheritInverseConfiguration
    Paciente toEntity(PacienteDTO dto);

    List<Paciente> toEntities(List<PacienteDTO> dtos);
}
