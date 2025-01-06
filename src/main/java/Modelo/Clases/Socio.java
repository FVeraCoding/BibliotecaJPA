
package Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Socio implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Basic
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellidos")
    private String apellidos;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNac;

    @Column(name = "correo_electronico")
    private String correo;
    private String telefono;
    private String direccion;

    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "club_id")
    Club club;
    
    @ManyToMany(mappedBy = "listaSocios", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    List<Evento> listaEventos;

    public Socio() {
    }
    
    public Socio(String nombre, String apellidos, LocalDate fechaNac, String correo, String telefono, String direccion, LocalDate fechaAlta) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.listaEventos = new ArrayList<Evento>();
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
    
    public void addClub(Club club){
        if(this.club == null){
            this.setClub(club);
            
            if(!club.getListaSocios().contains(this)){
                club.getListaSocios().add(this);
            }
        }
    }
    
    public void addUsuario(Usuario usuario){
        if(usuario != null){
            this.setUsuario(usuario);
            usuario.setSocio(this);
        }
    }
    
    public void addEvento(Evento evento){
        if(!this.listaEventos.contains(evento)){
            this.listaEventos.add(evento);
            
            if(!evento.listaSocios.contains(this)){
                evento.listaSocios.add(this);
            }
        }
    }
}
