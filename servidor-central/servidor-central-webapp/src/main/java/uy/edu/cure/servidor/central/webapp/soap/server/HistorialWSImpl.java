/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;


import javax.jws.WebService;
import uy.edu.cure.servidor.central.lib.HistorialControllerImpl;

/**
 *
 * @author juan
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.HistorialWS")
public class HistorialWSImpl implements HistorialWS{

    @Override
    public void crearHistorial(String ipAdd, String userAgent, String url) {
       HistorialControllerImpl estadisticaController = new  HistorialControllerImpl();
         estadisticaController.crearEstadistica(ipAdd, userAgent, url);     
    }

    
}
