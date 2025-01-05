
package Controlador;

import Modelo.Clases.Usuario;
import Modelo.Persistencia.UsuarioJpaController;
import Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioController {
    UsuarioJpaController control = new UsuarioJpaController();
    
    public void addUsuario(Usuario usuario){
        control.create(usuario);
    }
    
    public Usuario readUsuario(int id){
        return control.findUsuario(id);
    }
    
    public ArrayList<Usuario> readAllUsuarios(){
        List<Usuario> lista = control.findUsuarioEntities();
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>(lista);
        return listaUsuarios;
    }
    
    public void editUsuario(Usuario usuario) throws Exception{
        control.edit(usuario);
    }
    
    public void deleteUsuario(int id) throws NonexistentEntityException{
        control.destroy(id);
    }
}
