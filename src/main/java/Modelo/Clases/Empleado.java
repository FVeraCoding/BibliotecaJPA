
package Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Empleado implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    
    private String nombre;
    private String apellidos;
    
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNac;
    
    @Column(name = "correo_electronico")
    private String correo;
    private String telefono;
    private String direccion;
    
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;
    private String cargo;
    
    @OneToOne
    @JoinColumn(name = "usuario_id")
    Usuario usuario;
    
    @OneToMany(mappedBy="empleado")
    List<Club> listaClubes;

    public Empleado() {
    }

    public Empleado(String nombre, String apellidos, LocalDate fechaNac, String correo, String telefono, String direccion, LocalDate fechaAlta, String cargo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.cargo = cargo;
        this.listaClubes = new ArrayList<Club>();
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Club> getListaClubes() {
        return listaClubes;
    }

    public void setListaClubes(List<Club> listaClubes) {
        this.listaClubes = listaClubes;
    }
    
    public void addClub(Club club){
        if(!this.listaClubes.contains(club)){
            this.listaClubes.add(club);
            
            if(club.getEmpleado() == null){
                club.setEmpleado(this);
            }
        }
    }
    
    public void addUsuario(Usuario usuario){
        if(usuario != null){
            this.setUsuario(usuario);
            usuario.setEmp(this);
        }
    }
    
}