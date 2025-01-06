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
            
            Empleado emp2 = new Empleado("María", "Álvarez", LocalDate.of(1998, Month.APRIL, 13), "maria@correo.com", "954534251", "Calle espontaneidad", LocalDate.now(), "Administradora");
            empControl.addEmpleado(emp2);
            
            Empleado emp3 = new Empleado("Pablo", "Galván", LocalDate.of(2000, 02, 10), "pablo@correo.com", "765493827", "Bami", LocalDate.now(), "Bibliotecario");
            empControl.addEmpleado(emp3);

            //Socios
            Socio socio1 = new Socio("Adrian", "Lopez", LocalDate.of(1992, Month.MARCH, 2), "vegetarianguy@gmail.com", "765456543", "Calle brocoli", LocalDate.now());
            soControl.addSocio(socio1);
            
            Socio socio2 = new Socio("Jose", "Rodríguez", LocalDate.of(1997, Month.APRIL, 3), "pokemaster@gmail.com", "765463728", "Calle superball", LocalDate.now());
            soControl.addSocio(socio2);
            
            Socio socio3 = new Socio("Victor", "Colorado", LocalDate.of(2003, Month.FEBRUARY, 12), "pingpongmaster@gmail.com", "647352413", "Calle manrique", LocalDate.now());
            soControl.addSocio(socio3);
            
            //Usuarios
            Usuario usuarioEmp1 = new Usuario("fvera", "1234", "Administrador");
            usuControl.addUsuario(usuarioEmp1);
            
            Usuario usuarioSocio1 = new Usuario("AdriVeganFury", "1234", "Socio");
            usuControl.addUsuario(usuarioSocio1);
            
            Usuario usuarioEmp2 = new Usuario("MariaAlvarez", "1234", "Administrador");
            usuControl.addUsuario(usuarioEmp2);
            
            Usuario usuarioEmp3 = new Usuario("Pagama00", "1234", "Empleado");
            usuControl.addUsuario(usuarioEmp3);
            
            

            //Relaciones
            
            //Usuario-Empleado
            emp1.addUsuario(usuarioEmp1);
            emp2.addUsuario(usuarioEmp2);
            emp3.addUsuario(usuarioEmp3);
            
            //Usuario-Socio
            socio1.addUsuario(usuarioSocio1);
            
            //Club-EmpleadoOrganizador
            club1.addEmpleado(emp1);
            
            //Socio-Club
            socio1.addClub(club1);
            
            //Evento-SocioAsistencia
            evento1.addSocio(socio1);
            evento1.addSocio(socio2);
            evento2.addSocio(socio3);
            evento2.addSocio(socio1);
            
            //Evento-EmpleadoOrganizador
            evento1.addEmpleadoOrganizador(emp1);
            evento2.addEmpleadoOrganizador(emp3);
            
            //Evento-EmpleadoAsistencia
            evento1.addEmpleadoAsistencia(emp2);
            evento1.addEmpleadoAsistencia(emp3);
            
            evento2.addEmpleadoAsistencia(emp1);
            evento2.addEmpleadoAsistencia(emp2);
            
            

            //Actualizar los datos con las relaciones.
            
            //Empleados
            empControl.editEmpleado(emp1);
            empControl.editEmpleado(emp2);
            empControl.editEmpleado(emp3);
            
            //Clubs
            clubControl.editClub(club1);
            
            //Socios
            soControl.editSocio(socio1);
            
            //Eventos
            evenControl.editEvento(evento1);
            evenControl.editEvento(evento2);
  
            
        } catch (Exception ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
