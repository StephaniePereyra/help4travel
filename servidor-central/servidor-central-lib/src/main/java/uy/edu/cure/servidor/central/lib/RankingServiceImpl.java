/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Ranking;

/**
 *
 * @author juan
 */
public class RankingServiceImpl implements RankignService{
    
    private static List<Ranking> tablaRanking = new ArrayList<Ranking>();

    @Override
    public boolean guardadRanking(Ranking ranking) {
        if (ranking != null) {
            tablaRanking.add(ranking);
            return true;
        }
        return false;
        
    }

    @Override
    public Ranking obtenerPosicion(String servicio, String proveedor) {
        for (int i=0; i<tablaRanking.size();i++){
            if(tablaRanking.get(i).getServicio().equals(servicio)){
                if (tablaRanking.get(i).getProveedor().equals(proveedor)){
                    return tablaRanking.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public List<Ranking> obtenerRanking() {
        return tablaRanking;
    }
    
}
