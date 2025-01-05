/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Persistencia;

import Modelo.Clases.Socio;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
public class SocioJpaController implements Serializable {

    public SocioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public SocioJpaController(){
        this.emf = Persistence.createEntityManagerFactory("JPUnit");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Socio socio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = socio.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                socio.setUsuario(usuario);
            }
            em.persist(socio);
            if (usuario != null) {
                Socio oldSocioOfUsuario = usuario.getSocio();
                if (oldSocioOfUsuario != null) {
                    oldSocioOfUsuario.setUsuario(null);
                    oldSocioOfUsuario = em.merge(oldSocioOfUsuario);
                }
                usuario.setSocio(socio);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Socio socio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Socio persistentSocio = em.find(Socio.class, socio.getId());
            Usuario usuarioOld = persistentSocio.getUsuario();
            Usuario usuarioNew = socio.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                socio.setUsuario(usuarioNew);
            }
            socio = em.merge(socio);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setSocio(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Socio oldSocioOfUsuario = usuarioNew.getSocio();
                if (oldSocioOfUsuario != null) {
                    oldSocioOfUsuario.setUsuario(null);
                    oldSocioOfUsuario = em.merge(oldSocioOfUsuario);
                }
                usuarioNew.setSocio(socio);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = socio.getId();
                if (findSocio(id) == null) {
                    throw new NonexistentEntityException("The socio with id " + id + " no longer exists.");
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
            Socio socio;
            try {
                socio = em.getReference(Socio.class, id);
                socio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The socio with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = socio.getUsuario();
            if (usuario != null) {
                usuario.setSocio(null);
                usuario = em.merge(usuario);
            }
            em.remove(socio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Socio> findSocioEntities() {
        return findSocioEntities(true, -1, -1);
    }

    public List<Socio> findSocioEntities(int maxResults, int firstResult) {
        return findSocioEntities(false, maxResults, firstResult);
    }

    private List<Socio> findSocioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Socio.class));
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

    public Socio findSocio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Socio.class, id);
        } finally {
            em.close();
        }
    }

    public int getSocioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Socio> rt = cq.from(Socio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
