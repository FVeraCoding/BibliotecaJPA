
package Controlador;

import Modelo.Clases.Socio;
import Modelo.Persistencia.SocioJpaController;
import Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;


public class SocioController {
    SocioJpaController control = new SocioJpaController();
    
    public void addSocio(Socio socio){
        control.create(socio);
    }
    
    public Socio readSocio(int id){
        return control.findSocio(id);
    }
    
    public ArrayList<Socio> readAllSocios(){
        List<Socio> lista = control.findSocioEntities();
        ArrayList<Socio> listaSocios = new ArrayList<Socio>(lista);
        return listaSocios;
    }
    
    public void editSocio(Socio socio) throws Exception{
        control.edit(socio);
    }
    
    public void deleteSocio(int id) throws NonexistentEntityException{
        control.destroy(id);
    }
    
    
}
