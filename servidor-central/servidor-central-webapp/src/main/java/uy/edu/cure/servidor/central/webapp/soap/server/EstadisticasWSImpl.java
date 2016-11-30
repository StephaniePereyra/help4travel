/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Estadisticas;
import uy.edu.cure.servidor.central.lib.EstadisticaControllerImpl;
import uy.edu.cure.servidor.central.lib.EstadisticaServiceImpl;

/**
 *
 * @author juan
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.EstadistiasWS")
public class EstadisticasWSImpl implements EstadisticasWS{

    @Override
    public void crearEstadistica(String ipAdd, String userAgent, String url) {
         EstadisticaControllerImpl estadisticaController = new EstadisticaControllerImpl();
         estadisticaController.crearEstadistica(ipAdd, userAgent, url);
         
    }
    
    @Override
    public List<Estadisticas> obtenerTodosEstadisticas() {
         EstadisticaServiceImpl estadisticaService = new EstadisticaServiceImpl();
        return estadisticaService.obtenerTodosEstadisticas();
    }
    
}
