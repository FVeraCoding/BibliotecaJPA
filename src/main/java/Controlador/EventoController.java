
package Controlador;

import Modelo.Clases.Club;
import Modelo.Clases.Evento;
import Modelo.Persistencia.EventoJpaController;
import Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;

public class EventoController {
     EventoJpaController control = new EventoJpaController();

    public void addEvento(Evento evento) {
        control.create(evento);
    }

    public Evento readEvento(int id) {
        return control.findEvento(id);
    }

    public ArrayList<Evento> readAllEventos() {
        List<Evento> lista = control.findEventoEntities();
        ArrayList<Evento> listaEventos = new ArrayList<Evento>(lista);
        return listaEventos;
    }

    public void editEvento(Evento evento) throws Exception {
        control.edit(evento);
    }

    public void deleteEvento(int id) throws NonexistentEntityException {
        control.destroy(id);
    }
}
