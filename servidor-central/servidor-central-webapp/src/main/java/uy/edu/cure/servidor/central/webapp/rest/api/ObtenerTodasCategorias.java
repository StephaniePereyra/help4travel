/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.rest.api;

import entities.NewClass;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import uy.edu.cure.servidor.central.dto.DatosRest;
import uy.edu.cure.servidor.central.lib.CategoriaControllerImpl;

/**
 *
 * @author guido
 */
@Path("/ObtenerCategorias")
public class ObtenerTodasCategorias {
    @GET
    @Path("traer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodas(){
        CategoriaControllerImpl categoriaController = new CategoriaControllerImpl();
        DatosRest categoriasAux = new DatosRest();
        categoriasAux.setCategorias(categoriaController.obtenerTodosCategorias());
        return Response.status(Response.Status.OK).entity(categoriasAux).build();
    }
    
}
