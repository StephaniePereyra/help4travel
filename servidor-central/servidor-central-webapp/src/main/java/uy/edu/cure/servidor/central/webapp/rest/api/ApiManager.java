/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.rest.api;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author SCN
 */

public class ApiManager extends Application {
    
    private Set<Object> singletons = new HashSet<Object>();

	public ApiManager() {
            singletons.add(new ObtenerTodasCategorias());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
    
}
