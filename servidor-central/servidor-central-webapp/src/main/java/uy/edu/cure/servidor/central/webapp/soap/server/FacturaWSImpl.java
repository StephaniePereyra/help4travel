/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import uy.edu.cure.servidor.central.dto.Factura;
import uy.edu.cure.servidor.central.dto.ItemsFactura;

/**
 *
 * @author guido
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.FacturaWS")
public class FacturaWSImpl implements FacturaWS {


    @Override
    public void persistirFactura(String nickNameCliente, int numeroReservaCliente, List<ItemsFactura> items) {

        Factura nuevaFactura = new Factura();
        nuevaFactura.setNickCliente(nickNameCliente);
        nuevaFactura.setNumeroReserva(numeroReservaCliente);      

        try {
            EntityManagerFactory emf;
            emf = Persistence.createEntityManagerFactory("jpaDS");
            EntityManager em = (EntityManager) emf.createEntityManager();
            
            //Comienza transaccion
            em.getTransaction().begin();
            
            //Persisto los items
            for (int i = 0; i < items.size(); i++) {
                ItemsFactura item = new ItemsFactura();
                item = items.get(i);
                item.setFactura(nuevaFactura);

                em.persist(item);
            }
            //Termino persistir los items
            
            //Persistir la Factura
            nuevaFactura.setItems(items);
            em.persist(nuevaFactura);
            //Fin persistir factura
            em.getTransaction().commit();
            //Fin Transaccion

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



                
                
  
    