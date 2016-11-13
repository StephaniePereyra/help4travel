/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import uy.edu.cure.servidor.central.dto.Ciudad;
import uy.edu.cure.servidor.central.lib.PaisControllerImpl;

/**
 *
 * @author SCN
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.PaisWS")
public class PaisWSImpl implements PaisWS{

    @Override
    public boolean crearPaisWS(String nombre) {
        PaisControllerImpl paiscontroller = new PaisControllerImpl();
        boolean retorno;
        retorno = paiscontroller.crearPais(nombre);
        return retorno;
    }

    @Override
    public List<Ciudad> obtenerCiudadesWS(String nombre) {
        PaisControllerImpl paiscontroller = new PaisControllerImpl();
        List<Ciudad> ciudadesAux;
        ciudadesAux = paiscontroller.obtenerPais(nombre).getCiudades();
        return ciudadesAux;
    }


}
