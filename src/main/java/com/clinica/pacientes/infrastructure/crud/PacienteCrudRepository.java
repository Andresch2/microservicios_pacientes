package com.clinica.pacientes.infrastructure.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.pacientes.infrastructure.entity.Paciente;

@Repository
public interface PacienteCrudRepository extends JpaRepository<Paciente, Long> {
}
