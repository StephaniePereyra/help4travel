/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import uy.edu.cure.servidor.central.dto.Ranking;

/**
 *
 * @author juan
 */
public interface RankignService {
    
    boolean guardadRanking (Ranking ranking);
    Ranking obtenerPosicion (String servicio, String proveedor);
    List <Ranking> obtenerRanking ();
    
}
