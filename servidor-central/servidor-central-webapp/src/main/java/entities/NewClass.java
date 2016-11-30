/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import uy.edu.cure.servidor.central.dto.ItemsFactura;
import uy.edu.cure.servidor.central.dto.Factura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

		Factura f = new Factura();
		f.setNickCliente("Pepe");
                f.setNumeroReserva(1);
                ItemsFactura item1 = new ItemsFactura();
                item1.setCantidadItem(2);
                item1.setNickProveedor("proveedor1");
                item1.setNombreItem("hotel");
                item1.setTipoItem("servicio");
                item1.setFactura(f);
                em.persist(item1);
		List<ItemsFactura> listItems = new ArrayList();
                listItems.add(item1);
                f.setItems(listItems);
                em.persist(f);
		//p = em.find(Factura.class, p.getId())
		em.getTransaction().commit();
                
                em.getTransaction().begin();
                Query q = em.createNamedQuery("getFacturaXnumeroReserva");
                q.setParameter("numeroReserva", 1);
                Factura aux = (Factura) q.getSingleResult();
                em.getTransaction().commit();
               }
            catch(Exception e){
                e.printStackTrace();
            }
    }
    
}
