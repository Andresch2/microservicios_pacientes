
package com.clinica.pacientes.domain.repository;

import com.clinica.pacientes.infrastructure.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaciente extends JpaRepository<Paciente, Long> {
}
