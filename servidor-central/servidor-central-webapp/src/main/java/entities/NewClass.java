/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.ejb.EntityManagerFactoryImpl;

/**
 *
 * @author guido
 */
public class NewClass {
    
    public static void main() {
               try{
                EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("jpaDS");
		EntityManager em = (EntityManager) emf.createEntityManager();

		em.getTransaction().begin();

		Factura p = new Factura();
		p.setCliente("juan-pepe-luis");
		p.setPrecioTotal("milpeso");
		em.persist(p);
		
		//p = em.find(Factura.class, p.getId());
		//p.setNombre("pepe");
		
		em.getTransaction().commit();
               }
            catch(Exception e){
                e.printStackTrace();
            }
    }
    
}
