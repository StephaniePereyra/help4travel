/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import uy.edu.cure.servidor.central.dto.Historial;


/**
 *
 * @author juan
 */
public class HistorialServiceImpl implements Historialervice{
    
    private static List<Historial> Historial = new ArrayList<Historial>();

    @Override
    public boolean guardadEstadistica(Historial estadistica) {
         if (estadistica != null) {
              try {
            EntityManagerFactory emf;
            emf = Persistence.createEntityManagerFactory("jpaDS");
            EntityManager em = (EntityManager) emf.createEntityManager();
            
            //Comienza transaccion
            em.getTransaction().begin();
            
            //Persisto los items
           
                em.persist(estadistica);
            
            em.getTransaction().commit();
            //Fin Transaccion

        } catch (Exception e) {
            return false;
        }
            //Historial.add(estadistica);
            return true;
        }
        return false;
       
    }
    
}
