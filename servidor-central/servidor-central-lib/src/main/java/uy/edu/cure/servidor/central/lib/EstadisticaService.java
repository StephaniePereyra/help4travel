/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import uy.edu.cure.servidor.central.dto.Estadisticas;

/**
 *
 * @author juan
 */
public interface EstadisticaService {
    
    boolean guardadEstadistica (Estadisticas estadistica);
    List <Estadisticas> obtenerTodosEstadisticas ();
    
}
