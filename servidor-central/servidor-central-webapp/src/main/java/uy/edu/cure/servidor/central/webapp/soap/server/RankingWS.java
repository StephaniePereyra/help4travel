/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Ranking;

/**
 *
 * @author juan
 */
@WebService
public interface RankingWS {
    
    @WebMethod
    public void rankear(String servicio, String proveedor);
    
    @WebMethod
     public List<Ranking> obtenerRanking();
    
}
