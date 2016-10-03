/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import uy.edu.cure.servidor.central.dto.*;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author SCN
 */
public class PromocionControllerImpl implements PromocionController {

    @Jeringa(value = "promocionservice")
    private PromocionServiceImpl promocionService;
    private UsuarioControllerImpl usuarioController;
    private ServicioControllerImpl servicioController;

    public PromocionControllerImpl() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        usuarioController = new UsuarioControllerImpl();
        servicioController = new ServicioControllerImpl();

    }

    @Override
    public String crearPromocion(String nombre, int descuento, String nickProveedor, List<String> servicios) {
        if(!nombre.isEmpty()) {
            if(usuarioController.existeProveedor(nickProveedor)) {
                if(!promocionService.existePromocion(nombre, nickProveedor)) {
                    if(descuento > 0 && descuento < 100) {
                        if(!servicios.isEmpty()) {
                            double precioTotal = 0;
                            Proveedor proveedor = usuarioController.obtenerProveedor(nickProveedor);
                            Promocion promocion = new Promocion(nombre, descuento, 0, proveedor);
                            for(int i=0; i<servicios.size(); i++) {
                                promocion.setServicios(servicioController.obtenerServicio(servicios.get(i), nickProveedor));
                                precioTotal = precioTotal + servicioController.obtenerServicio(servicios.get(i), nickProveedor).getPrecio();
                            }
                            promocion.setPrecioTotal(precioTotal);
                            promocionService.guardarPromocion(promocion);
                            return "OK";
                        } else {
                            return "Debe agregar servicios";
                        }
                    } else {
                        return "Descuento invalido";
                    }
                } else {
                    return "Ya existe una promocion con ese nombre";
                }
            } else {
                return "No existe proveedor";
            }
        } else {
            return "Nombre no puede quedar en blanco";
        }
    }

    @Override
    public boolean existePromocion(String nombre, String nickNameProveedor) {
        return promocionService.existePromocion(nombre, nickNameProveedor);
    }

    @Override
    public Promocion obtenerPromocion(String Nombre, String nickNameProveedor) {
        return promocionService.obtenerPromocion(Nombre, nickNameProveedor);
    }

    @Override
    public List<Promocion> obtenerTodasPromociones() {
        return promocionService.obtenerTodasPromociones();
    }

}
