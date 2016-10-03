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
public interface PromocionService {

    public boolean guardarPromocion(Promocion promocion);

    public boolean existePromocion(String nombre, String nickNameProveedor);

    public Promocion obtenerPromocion(String nombrem, String nickNameProveedor);

    public List<Promocion> obtenerTodasPromociones();

}
