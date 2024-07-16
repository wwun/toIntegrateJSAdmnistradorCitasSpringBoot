package com.william.springboot.app.administradorcitas.springboot_jpa.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.william.springboot.app.administradorcitas.springboot_jpa.entities.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, String>{
    Optional<Paciente> findByNombreAndPropietario(String nombre, String propietario);
    Optional<Paciente> findByNombreAndTelefono(String nombre, String telefono);
}
