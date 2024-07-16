package com.william.springboot.app.administradorcitas.springboot_jpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.william.springboot.app.administradorcitas.springboot_jpa.entities.Cita;
import com.william.springboot.app.administradorcitas.springboot_jpa.entities.Paciente;

public interface PacienteCitasService {
    boolean savePacienteYCita(Paciente paciente);
    List<Paciente> getAllPacientes();
    List<Cita> getAllCitas();
    boolean updatePacienteYCita(Paciente paciente);
}
