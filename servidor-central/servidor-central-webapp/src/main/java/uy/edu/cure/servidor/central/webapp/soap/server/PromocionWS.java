/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Servicio;

/**
 *
 * @author SCN
 */
@WebService
public interface PromocionWS {

    @WebMethod
    public Promocion obtenerPromocionWS(String nombrePromocion, String nickProveedor);

    @WebMethod
    public String crearPromocionWS(String nombre, int descuento, String nickProveedor, List<String> servicios);
    
    @WebMethod
    public List<Servicio> serviciosPromocionWS (String nombrePromocion, String nickProveedor);
    
    @WebMethod
    public List<Promocion> obtenerTodasPromociones ();
    
    @WebMethod
    public List<Servicio> obtenerServiciosPromoWS (String nombrePromocion, String nickProveedor);
}
