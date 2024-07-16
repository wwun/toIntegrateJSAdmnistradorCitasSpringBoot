package com.william.springboot.app.administradorcitas.springboot_jpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.william.springboot.app.administradorcitas.springboot_jpa.entities.Cita;
import com.william.springboot.app.administradorcitas.springboot_jpa.entities.Paciente;
import com.william.springboot.app.administradorcitas.springboot_jpa.repositories.CitaRepository;
import com.william.springboot.app.administradorcitas.springboot_jpa.services.PacienteCitasService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api")
public class PacienteYCitaController {

    @Autowired
    PacienteCitasService pacienteCitasService;

    @PostMapping("/save")
    private ResponseEntity<?> savePacienteYCita(@Valid @RequestBody Paciente paciente, BindingResult result){
        
        paciente.getCitas().getFirst().setPaciente(paciente);
        
        if(result.hasFieldErrors()){
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<error:>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCitasService.savePacienteYCita(paciente));
    }

    @GetMapping("/pacientes")
    private ResponseEntity<?> getAllPacientes(){
        List<Paciente> pacientes = pacienteCitasService.getAllPacientes();
        
        if(pacientes.size()>0)
            return ResponseEntity.status(HttpStatus.OK).body(pacientes);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/citas")
    private ResponseEntity<?> getAllCitas(){
        List<Cita> citas = pacienteCitasService.getAllCitas();
        return ResponseEntity.status(HttpStatus.OK).body(citas);
    }

    private ResponseEntity<?> updatePacienteYCita(@Valid @RequestBody Paciente paciente, BindingResult result){
        if(result.hasFieldErrors()){
            System.out.println(result);
        }

        //se envía un paciente con solo la lista seleccionada
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCitasService.updatePacienteYCita(paciente));
    }
}
