/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.rest.api;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import uy.edu.cure.servidor.central.dto.Historial;

/**
 *
 * @author juan
 */
@Path("/ObtenerHistorial")
public class obtenerHistorial {
       
    @GET
    @Path("traer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodas(){
        List<Historial> historial = null;
        
        try{
            EntityManagerFactory emf;
            emf = Persistence.createEntityManagerFactory("jpaDS");
            EntityManager em = (EntityManager) emf.createEntityManager();
            
            //Comienza Transaccion
                em.getTransaction().begin();
                Query q = em.createNamedQuery("getHistorial");              
                historial= (List<Historial>) q.getResultList();
                em.getTransaction().commit();
            //Fin Transaccion
        
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return Response.status(Response.Status.OK).entity(historial).build();
    }
    
    

}
