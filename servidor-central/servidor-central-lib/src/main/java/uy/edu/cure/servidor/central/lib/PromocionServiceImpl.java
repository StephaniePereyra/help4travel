/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Promocion;

/**
 *
 * @author SCN
 */
public class PromocionServiceImpl implements PromocionService {

    private static List<Promocion> promociones = new ArrayList<>();

    public PromocionServiceImpl() {

    }

    @Override
    public boolean guardarPromocion(Promocion promocion) {
        if (promocion != null) {
            promociones.add(promocion);
            return true;
        }
        return false;
    }

    @Override
    public boolean existePromocion(String nombre, String nickNameProveedor) {
        Iterator<Promocion> iteratorPromociones = promociones.iterator();
        while (iteratorPromociones.hasNext()) {
            Promocion promocionAuxiliar = iteratorPromociones.next();
            if (promocionAuxiliar.getNombre().equals(nombre) && promocionAuxiliar.getProveedor().getNickName().equals(nickNameProveedor)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Promocion obtenerPromocion(String nombre, String nickNameProveedor) {
        Iterator<Promocion> iteratorPromociones = promociones.iterator();
        while (iteratorPromociones.hasNext()) {
            Promocion promocionAuxiliar = iteratorPromociones.next();
            if (promocionAuxiliar.getNombre().equals(nombre) && promocionAuxiliar.getProveedor().getNickName().equals(nickNameProveedor)) {
                return promocionAuxiliar;
            }
        }
        return null;
    }

    @Override
    public List<Promocion> obtenerTodasPromociones() {
        return promociones;
    }

}
