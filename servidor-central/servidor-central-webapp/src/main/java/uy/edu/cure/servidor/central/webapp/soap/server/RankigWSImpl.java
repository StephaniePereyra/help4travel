/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Ranking;
import uy.edu.cure.servidor.central.lib.RankigControllerImpl;
import uy.edu.cure.servidor.central.lib.RankingServiceImpl;

/**
 *
 * @author juan
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.RankingWS")
public class RankigWSImpl implements RankingWS{

    @Override
    public void rankear(String servicio, String proveedor) {
        RankigControllerImpl rankigController = new RankigControllerImpl();
        rankigController.rankear(servicio, proveedor);
    }

    @Override
    public List<Ranking> obtenerRanking() {
         RankingServiceImpl rankigService = new RankingServiceImpl();
         return rankigService.obtenerRanking(); 
    }
    
}
