/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import uy.edu.cure.servidor.central.dto.Promocion;

/**
 *
 * @author SCN
 */
public interface PromocionController {

    public String crearPromocion(String nombre, int descuento, String nickProveedor, List<String> servicios);

    boolean existePromocion(String nombre, String nickNameProveedor);

    public Promocion obtenerPromocion(String Nombre, String nickNameProveedor);

    public List<Promocion> obtenerTodasPromociones();

}
