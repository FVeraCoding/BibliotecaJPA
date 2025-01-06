
package Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String nombre_usuario;
    private String contraseña;
    private String rol;
    
    @OneToOne(mappedBy = "usuario")
    private Empleado emp;
    
    @OneToOne(mappedBy="usuario")
    private Socio socio;

    public Usuario() {
    }

    public Usuario(String nombre_usuario, String contraseña, String rol) {
        this.nombre_usuario = nombre_usuario;
        this.contraseña = contraseña;
        this.rol = rol;

    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Empleado getEmp() {
        return emp;
    }

    public void setEmp(Empleado emp) {
        this.emp = emp;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    
    
    
}
