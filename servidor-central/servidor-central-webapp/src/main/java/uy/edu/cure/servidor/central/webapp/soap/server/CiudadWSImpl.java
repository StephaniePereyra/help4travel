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
import uy.edu.cure.servidor.central.lib.CiudadControllerImpl;

/**
 *
 * @author SCN
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.CiudadWS")
public class CiudadWSImpl implements CiudadWS {

    @Override
    public boolean crearCiudadWS(String nombreCiudad, String nombrePais) {
        CiudadControllerImpl ciudadcontroller = new CiudadControllerImpl();
        boolean retorno;
        retorno = ciudadcontroller.crearCiudad(nombreCiudad, nombrePais);
        return retorno;
    }
  
    @Override
    public Ciudad obtenerCiudadWS(String nombre){
        CiudadControllerImpl ciudadController = new CiudadControllerImpl();
        return ciudadController.obtenerCiudad(nombre);
        
    }

    @Override
    public List<Ciudad> obtenerTodasCiudadesWS() {
        CiudadControllerImpl ciudadController = new CiudadControllerImpl();
        List<Ciudad> CiudadesAux;
        CiudadesAux = ciudadController.obtenerTodosCiudades();
        return CiudadesAux;
    }

}
