/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.rest.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import uy.edu.cure.servidor.central.dto.Categoria;
import uy.edu.cure.servidor.central.lib.CategoriaControllerImpl;

/**
 *
 * @author SCN
 */
@Path("/ObtenerCategorias")
public class obtenerCategoriasRest {
    @GET
	@Produces("Application/json")
	public String json() throws JsonProcessingException {
            
            CategoriaControllerImpl categoriacontroller = new CategoriaControllerImpl();
            List<Categoria> categoriasAux;
            categoriasAux = categoriacontroller.obtenerTodosCategorias();
            ObjectMapper map = new ObjectMapper();
            return map.writeValueAsString(categoriasAux);
            
	}
    
}
