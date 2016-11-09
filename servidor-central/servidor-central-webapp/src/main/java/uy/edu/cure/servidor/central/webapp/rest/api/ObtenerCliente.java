/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.rest.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;

/**
 *
 * @author SCN
 */

@Path("/ObtenerCliente")
public class ObtenerCliente {
    @GET
    @Path("{nickName}")
	@Produces("Application/json")
	public String json(@PathParam("nickName") String nickName) throws JsonProcessingException {
            
            UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
            Cliente c = new Cliente();
            c = usuariocontroller.obtenerCliente(nickName);
            ObjectMapper map = new ObjectMapper();
            return map.writeValueAsString(c);
            
	}
    
}
