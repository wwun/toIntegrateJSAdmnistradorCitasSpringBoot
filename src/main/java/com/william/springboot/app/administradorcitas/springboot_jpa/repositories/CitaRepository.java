package com.william.springboot.app.administradorcitas.springboot_jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.william.springboot.app.administradorcitas.springboot_jpa.entities.Cita;

@Repository
public interface CitaRepository extends CrudRepository<Cita, String>{

}
