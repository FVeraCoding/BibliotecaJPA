
package Controlador;

import Modelo.Clases.Empleado;
import Modelo.Persistencia.EmpleadoJpaController;
import Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;


public class EmpleadoController {
    EmpleadoJpaController control = new EmpleadoJpaController();
    
    public void addEmpleado(Empleado emp){
        control.create(emp);
    }
    
    public Empleado readEmpleado(int id){
        return control.findEmpleado(id);
    }
    
    public ArrayList<Empleado> readAllEmpleados(){
        List<Empleado> lista = control.findEmpleadoEntities();
        ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>(lista);
        return listaEmpleados;
    }
    
    public void editEmpleado(Empleado emp) throws Exception{
        control.edit(emp);
    }
    
    public void deleteEmpleado(int id) throws NonexistentEntityException{
        control.destroy(id);
    }
}
