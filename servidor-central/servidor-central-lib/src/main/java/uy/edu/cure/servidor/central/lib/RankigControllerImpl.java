/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import uy.edu.cure.servidor.central.dto.Ranking;

/**
 *
 * @author juan
 */
public class RankigControllerImpl implements RankingController {

    @Override
    public void rankear(String servicio, String proveedor) {

        RankingServiceImpl rankingService = new RankingServiceImpl();

        if (rankingService.obtenerPosicion(servicio, proveedor) != null) {
            Ranking posicion = rankingService.obtenerPosicion(servicio, proveedor);
            posicion.setVisitas(posicion.getVisitas() + 1);
        } else {
            Ranking posicion = new Ranking(servicio, proveedor);
            rankingService.guardadRanking(posicion);
        }

    }

}
