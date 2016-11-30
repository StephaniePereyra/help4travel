/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.rest.api;

import entities.NewClass;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import uy.edu.cure.servidor.central.dto.DatosRest;
import uy.edu.cure.servidor.central.dto.Factura;
import uy.edu.cure.servidor.central.lib.CategoriaControllerImpl;

/**
 *
 * @author guido
 */
@Path("/ObtenerFactura")
public class ObtenerFactura {
    @GET
    @Path("traer/{numeroReserva}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPorNumeroReserva(@PathParam("numeroReserva") int numeroReserva){
        
        Factura facturaAuxiliar = null;
        
        try{
            EntityManagerFactory emf;
            emf = Persistence.createEntityManagerFactory("jpaDS");
            EntityManager em = (EntityManager) emf.createEntityManager();
            
            //Comienza Transaccion
                em.getTransaction().begin();
                Query q = em.createNamedQuery("getFacturaXnumeroReserva"); //query definida en la clase Factura
                q.setParameter("numeroReserva", numeroReserva);
                facturaAuxiliar = (Factura) q.getSingleResult();
                em.getTransaction().commit();
            //Fin Transaccion
        
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return Response.status(Response.Status.OK).entity(facturaAuxiliar).build();
    }
    
}
    
