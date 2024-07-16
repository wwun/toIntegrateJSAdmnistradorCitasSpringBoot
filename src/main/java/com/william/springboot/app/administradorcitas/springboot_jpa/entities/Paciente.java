package com.william.springboot.app.administradorcitas.springboot_jpa.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
//@Table(name="Paciente")
public class Paciente {

    @Id
    private String idPaciente;

    @NotBlank(message="{Paciente.nombre.NotBlank}")   //la anotación @NotBlank asegura que el campo nombre no sea null y no esté vacío
    @Size(max=45, message="{Paciente.nombre.Size}")
    private String nombre;

    @NotBlank(message="{Paciente.propietario.NotBlank}")
    @Size(max=45, message="{Paciente.propietario.size}")
    private String propietario;

    @NotBlank(message="{Paciente.telefono.NotBlank}")
    @Size(max=10, message="{Paciente.telefono.Size}")
    @Pattern(regexp = "\\d{10}", message="{Paciente.telefono.Pattern}")
    private String telefono;
    //@Pattern(regexp = "\\d{10}", message = "El número de teléfono debe tener 10 dígitos")

    //@NotNull(message = "El nombre no puede ser nulo")
    //@Email(message = "Debe ser un correo electrónico válido")
    //private String email

    //@Past(message = "La fecha de nacimiento debe estar en el pasado")
    //@Future(message = "La fecha de la cita debe estar en el futuro")
    //private LocalDate fecha

    //@Transient    //variables en tu entidad que no deben ser persistidas en la base de datos

    @OneToMany(mappedBy="paciente", cascade=CascadeType.ALL, orphanRemoval=true)   //mappedBy="idPaciente" es el nombre de la FK en Cita
    private List<Cita> citas;

    public Paciente(){
        citas = new ArrayList<>();
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
