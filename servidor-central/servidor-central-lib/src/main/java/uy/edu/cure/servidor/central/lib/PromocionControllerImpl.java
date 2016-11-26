/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
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
                if(!existePromocion(nombre, nickProveedor)) {
                    if(descuento > 0 && descuento < 100) {
                        if(!servicios.isEmpty()) {
                            double precioTotal = 0;
                            Proveedor proveedor = usuarioController.obtenerProveedor(nickProveedor);
                            List<Servicio> serviciosAux = new ArrayList<>();
                            for(int i=0; i<servicios.size(); i++) {
                                Servicio servicioAuxiliar = servicioController.obtenerServicio(servicios.get(i), nickProveedor);
                                serviciosAux.add(servicioAuxiliar);
                                precioTotal = precioTotal + servicioAuxiliar.getPrecio();
                            }
                            precioTotal = precioTotal - (precioTotal*descuento/100);
                            Promocion promocion = new Promocion(nombre, descuento, precioTotal, serviciosAux, proveedor);
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
        if(obtenerPromocion(nombre, nickNameProveedor) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Promocion obtenerPromocion(String Nombre, String nickNameProveedor) {
        return promocionService.obtenerPromocion(Nombre, nickNameProveedor);
    }

    @Override
    public List<Promocion> obtenerTodasPromociones() {
        return promocionService.obtenerTodasPromociones();
    }
    
    @Override
    public void vaciarPersistenciaPromociones() {
        promocionService.obtenerTodasPromociones().clear();
    }

}
