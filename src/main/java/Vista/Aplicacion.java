package Vista;

import Controlador.ClubController;
import Controlador.EmpleadoController;
import Controlador.EventoController;
import Controlador.SocioController;
import Controlador.UsuarioController;
import Modelo.Clases.Club;
import Modelo.Clases.Empleado;
import Modelo.Clases.Evento;
import Modelo.Clases.Socio;
import Modelo.Clases.Usuario;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aplicacion {

    public static void main(String[] args) {
        try {
            EmpleadoController empControl = new EmpleadoController();
            SocioController soControl = new SocioController();
            UsuarioController usuControl = new UsuarioController();
            ClubController clubControl = new ClubController();
            EventoController evenControl = new EventoController();
            
            //Eventos
            Evento evento1 = new Evento("Lectura en voz alta de Novela", "Esta es una descripción de evento de prueba", LocalDate.now());
            evenControl.addEvento(evento1);
            
            Evento evento2 = new Evento("Evento2 prueba", "Evento2 prueba descripcion", LocalDate.now());
            evenControl.addEvento(evento2);
            
            //Clubs
            Club club1 = new Club("Nuevos horizontes", "Leemos un libro al mes y hacemos reuniones para comentarlos", LocalDate.now());
            clubControl.addClub(club1);
            
            //Empleados
            Empleado emp1 = new Empleado("Fer", "Test", LocalDate.of(1999, Month.NOVEMBER, 02), "fvera@correo.com", "722281525", "Calle Falsa 123", LocalDate.now(), "Jefe");
            empControl.addEmpleado(emp1);

            //Socios
            Socio socio1 = new Socio("Adrian", "Lopez", LocalDate.of(1992, Month.MARCH, 2), "vegetarianguy@gmail.com", "765456543", "Calle brocoli", LocalDate.now());
            soControl.addSocio(socio1);
            
            Socio socio2 = new Socio("Jose", "Rodríguez", LocalDate.of(1997, Month.APRIL, 3), "pokemaster@gmail.com", "765463728", "Calle superball", LocalDate.now());
            soControl.addSocio(socio2);
            
            Socio socio3 = new Socio("Victor", "Colorado", LocalDate.of(2003, Month.FEBRUARY, 12), "pingpongmaster@gmail.com", "647352413", "Calle manrique", LocalDate.now());
            soControl.addSocio(socio3);
            
            //Usuarios
            Usuario usuario1 = new Usuario("fvera", "1234", "Administrador", emp1);
            usuControl.addUsuario(usuario1);
            
            Usuario usuario2 = new Usuario("AdriVeganFury", "1234", "Socio", socio1);
            usuControl.addUsuario(usuario2);
            
            

            //Relaciones
            emp1.addUsuario(usuario1);
            socio1.addUsuario(usuario2);
            club1.addEmpleado(emp1);
            socio1.addClub(club1);
            evento1.addSocio(socio1);
            evento1.addSocio(socio2);
            evento2.addSocio(socio3);
            evento2.addSocio(socio1);

            empControl.editEmpleado(emp1);
            clubControl.editClub(club1);
            soControl.editSocio(socio1);
            evenControl.editEvento(evento1);
            evenControl.editEvento(evento2);
            
        } catch (Exception ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
