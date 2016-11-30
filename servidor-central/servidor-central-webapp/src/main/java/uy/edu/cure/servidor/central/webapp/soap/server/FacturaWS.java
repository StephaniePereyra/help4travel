package uy.edu.cure.servidor.central.webapp.soap.server;


import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Factura;
import uy.edu.cure.servidor.central.dto.ItemsFactura;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author guido
 */

@WebService
public interface FacturaWS {
        
    @WebMethod
    public void persistirFactura(String nickNameCliente,int numeroReservaCliente,List<ItemsFactura> items);
   
}
