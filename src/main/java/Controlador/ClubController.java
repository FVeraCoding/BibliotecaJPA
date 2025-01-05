package Controlador;

import Modelo.Clases.Club;
import Modelo.Persistencia.ClubJpaController;
import Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;

public class ClubController {

    ClubJpaController control = new ClubJpaController();

    public void addClub(Club club) {
        control.create(club);
    }

    public Club readClub(int id) {
        return control.findClub(id);
    }

    public ArrayList<Club> readAllClubs() {
        List<Club> lista = control.findClubEntities();
        ArrayList<Club> listaClubs = new ArrayList<Club>(lista);
        return listaClubs;
    }

    public void editClub(Club club) throws Exception {
        control.edit(club);
    }

    public void deleteClub(int id) throws NonexistentEntityException {
        control.destroy(id);
    }

}
