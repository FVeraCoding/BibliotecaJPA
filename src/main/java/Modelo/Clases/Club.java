
package Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Club implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    
    @Basic
    private String nombre;
    private String descripcion;
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    Empleado empleado;
    
    @OneToMany(mappedBy="club")
    List<Socio> listaSocios;
    
    public Club() {
    }

    public Club(String nombre, String descripcion, LocalDate fechaCreacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.listaSocios = new ArrayList<Socio>();
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Socio> getListaSocios() {
        return listaSocios;
    }

    public void setListaSocios(List<Socio> listaSocios) {
        this.listaSocios = listaSocios;
    }
    
    
    public void addSocio(Socio socio){
        if(!this.listaSocios.contains(socio)){
            this.listaSocios.add(socio);
            
            if(socio.getClub() != null){
                socio.setClub(this);
            }
        }
    }
    
    public void addEmpleado(Empleado emp){
        if(this.empleado == null){
            this.empleado = emp;
            
            if(!empleado.getListaClubes().contains(this)){
                empleado.getListaClubes().add(this);
            }
        }
    }
    
    
}
