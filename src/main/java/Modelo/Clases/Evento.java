
package Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evento implements Serializable {
    @Id
    @GeneratedValue
    int id;
    
    @Basic
    String nombre;
    String descripcion;
    LocalDate fecha;
    
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name="asistencia_socios_evento",
            joinColumns = @JoinColumn(name="id_evento"),
            inverseJoinColumns = @JoinColumn(name="id_socio"))
    List<Socio> listaSocios;
    
    @ManyToMany
    @JoinTable(name = "asistencia_empleados_evento",
            joinColumns = @JoinColumn(name="id_evento"),
            inverseJoinColumns = @JoinColumn(name="id_socio"))
    List<Empleado> listaEmpleadosAsistencia;
    
    @ManyToOne
    @JoinColumn(name = "empleado_organizador")
    Empleado empleado;

    public Evento() {
    }
    
    public Evento(String nombre, String descripcion, LocalDate fecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.listaSocios = new ArrayList<Socio>();
        this.listaEmpleadosAsistencia = new ArrayList<Empleado>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<Socio> getListaSocios() {
        return listaSocios;
    }

    public void setListaSocios(List<Socio> listaSocios) {
        this.listaSocios = listaSocios;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getListaEmpleadosAsistencia() {
        return listaEmpleadosAsistencia;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleadosAsistencia = listaEmpleados;
    }
    
    public void addEmpleadoAsistencia(Empleado empleado){
        if(!this.getListaEmpleadosAsistencia().contains(empleado)){
            this.getListaEmpleadosAsistencia().add(empleado);
            
            if(!empleado.getListaEventosAsistencia().contains(this)){
                empleado.getListaEventosAsistencia().add(this);
            }
        }
    }
    
    public void addEmpleadoOrganizador(Empleado empleado){
        if(this.getEmpleado() == null){
            this.setEmpleado(empleado);
            
            if(!empleado.getListaEventosOrganizados().contains(this)){
                empleado.getListaEventosOrganizados().add(this);
            }
        }
    }
    
    public void addSocio(Socio socio){
        if(!this.listaSocios.contains(socio)){
            this.listaSocios.add(socio);
            
            if(!socio.listaEventos.contains(this)){
                socio.listaEventos.add(this);
            }
        }
    }
    
}
