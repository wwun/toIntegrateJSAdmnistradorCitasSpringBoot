package com.william.springboot.app.administradorcitas.springboot_jpa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
//@Table(name="Cita")
public class Cita {

    @Id
    private String idCita;

    @NotBlank(message="{Cita.fecha.NotBlank}")
    @Size(min=10, message="{Cita.fecha.Size}")
    private String fecha;

    @NotBlank(message="{Cita.hora.NotBlank}")
    @Size(min=8, message="Cita.hora.Size")
    private String hora;

    @NotBlank(message="{Cita.sintomas.NotBlank}")
    @Size(min=1, max=200, message="{Cita.sintomas.Size}")
    private String sintomas;

    @ManyToOne
    @JoinColumn(name="id_paciente")  //ndica que la columna idPaciente en la tabla Citas es la clave externa que referencia a Paciente   //nombre de la FK en la tabla cita, no en Paciente
    private Paciente paciente;

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    //no agregar getters y setters de idPaciente sino se hacen llamadas en bucle
    public void setPaciente(Paciente paciente){
        this.paciente = paciente;
    }

    public String getPacienteId(){
        return paciente.getIdPaciente();
    }
}
