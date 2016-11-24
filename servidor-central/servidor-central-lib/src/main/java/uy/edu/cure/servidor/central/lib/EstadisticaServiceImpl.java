/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Estadisticas;

/**
 *
 * @author juan
 */
public class EstadisticaServiceImpl implements EstadisticaService{
    
    private static List<Estadisticas> estadisticas = new ArrayList<Estadisticas>();

    @Override
    public boolean guardadEstadistica(Estadisticas estadistica) {
         if (estadistica != null) {
            estadisticas.add(estadistica);
            return true;
        }
        return false;
       
    }

    @Override
    public List<Estadisticas> obtenerTodosEstadisticas() {
        return estadisticas;
        
    }
    
}
