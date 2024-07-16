package com.william.springboot.app.administradorcitas.springboot_jpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.william.springboot.app.administradorcitas.springboot_jpa.entities.Cita;
import com.william.springboot.app.administradorcitas.springboot_jpa.entities.Paciente;
import com.william.springboot.app.administradorcitas.springboot_jpa.repositories.CitaRepository;
import com.william.springboot.app.administradorcitas.springboot_jpa.repositories.PacienteRepository;

@Service
public class PacienteCitasServiceImpl implements PacienteCitasService{
    
    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    CitaRepository citaRepository;

    @Override
    @Transactional
    public boolean savePacienteYCita(Paciente paciente){
        //se está considerando que una mascota pertenece solo a un dueno
        
        Optional<Paciente> pacienteExistente = pacienteRepository.findByNombreAndPropietario(paciente.getNombre(), paciente.getPropietario());
        
        if(!pacienteExistente.isPresent()){
            pacienteRepository.save(Optional.of(paciente).orElseThrow());
        }

        //se asigna el paciente a la cita
        paciente.getCitas().getFirst().setPaciente(paciente);   //revisar si necesario
        
        //solo viene una sola cita, la lista de paciente tiene una sola cita
        //guardar la cita
        citaRepository.save(paciente.getCitas().getFirst());

        return true;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Paciente> getAllPacientes(){
        return (List<Paciente>)pacienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public List<Cita> getAllCitas(){
        return (List<Cita>)citaRepository.findAll();
    }

    @Override
    @Transactional
    public boolean updatePacienteYCita(Paciente paciente){
        try{
            Optional<Paciente> pacienteOptional = pacienteRepository.findById(paciente.getIdPaciente());
            if(pacienteOptional.isPresent()){
            Optional<Cita> citaOptional = citaRepository.findById(pacienteOptional.get().getCitas().getFirst().getIdCita());
                if(citaOptional.isPresent()){
                    paciente.getCitas().getFirst().setPaciente(paciente);
                    pacienteRepository.save(paciente);
                    return true;
                }
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }

}
