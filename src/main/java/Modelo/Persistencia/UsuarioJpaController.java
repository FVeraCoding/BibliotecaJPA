/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Persistencia;

import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import Modelo.Clases.Empleado;
import Modelo.Clases.Usuario;
import Modelo.Persistencia.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public UsuarioJpaController(){
        this.emf = Persistence.createEntityManagerFactory("JPUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado emp = usuario.getEmp();
            if (emp != null) {
                emp = em.getReference(emp.getClass(), emp.getId());
                usuario.setEmp(emp);
            }
            em.persist(usuario);
            if (emp != null) {
                Usuario oldUsuarioOfEmp = emp.getUsuario();
                if (oldUsuarioOfEmp != null) {
                    oldUsuarioOfEmp.setEmp(null);
                    oldUsuarioOfEmp = em.merge(oldUsuarioOfEmp);
                }
                emp.setUsuario(usuario);
                emp = em.merge(emp);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Empleado empOld = persistentUsuario.getEmp();
            Empleado empNew = usuario.getEmp();
            if (empNew != null) {
                empNew = em.getReference(empNew.getClass(), empNew.getId());
                usuario.setEmp(empNew);
            }
            usuario = em.merge(usuario);
            if (empOld != null && !empOld.equals(empNew)) {
                empOld.setUsuario(null);
                empOld = em.merge(empOld);
            }
            if (empNew != null && !empNew.equals(empOld)) {
                Usuario oldUsuarioOfEmp = empNew.getUsuario();
                if (oldUsuarioOfEmp != null) {
                    oldUsuarioOfEmp.setEmp(null);
                    oldUsuarioOfEmp = em.merge(oldUsuarioOfEmp);
                }
                empNew.setUsuario(usuario);
                empNew = em.merge(empNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Empleado emp = usuario.getEmp();
            if (emp != null) {
                emp.setUsuario(null);
                emp = em.merge(emp);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
