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
 * @author Rodrigo "Lobo Plateado" Pérez
 */
public class ServicioControllerImpl implements ServicioController {

    @Jeringa(value = "servicioservice")
    private ServicioService servicioService;
    @Jeringa(value = "ciudadservice")
    private CiudadService ciudadService;
    @Jeringa(value = "usuarioservice")
    private UsuarioService usuarioService;

    //
    private UsuarioControllerImpl usuarioController;
    private CiudadControllerImpl ciudadController;
    private CategoriaControllerImpl categoriaController;
    //

    public ServicioControllerImpl() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //
        usuarioController = new UsuarioControllerImpl();
        ciudadController = new CiudadControllerImpl();
        categoriaController = new CategoriaControllerImpl();
        //
    }

    @Override
    public String crearServicio(String nombreServicio, String descripcion, double precio, String ciudadOrigen, String ciudadDestino, List<String> categorias, List<String> imagenes, String nickNameProveedor) {
        if (!nombreServicio.isEmpty()) {
            if (usuarioController.existeProveedor(nickNameProveedor)) {
                if (!servicioService.existeServicio(nombreServicio, nickNameProveedor)) {
                    if (!descripcion.isEmpty()) {
                        if (precio > 0) {
                            if (ciudadController.existeCiudad(ciudadOrigen)) {
                                if (ciudadDestino != null) {
                                    if (ciudadController.existeCiudad(ciudadDestino) || ciudadDestino.equals("<null>")) {
                                        if (!ciudadOrigen.equals(ciudadDestino)) {
                                            if (!categorias.isEmpty()) {
                                                Ciudad origen = ciudadController.obtenerCiudad(ciudadOrigen);
                                                Ciudad destino;
                                                if (ciudadDestino.equals("<null>")) {
                                                    destino = null;
                                                } else {
                                                    destino = ciudadController.obtenerCiudad(ciudadDestino);
                                                }
                                                Proveedor proveedor = usuarioController.obtenerProveedor(nickNameProveedor);
                                                Servicio servicio = new Servicio(nombreServicio, descripcion, precio, origen, destino, proveedor);
                                                for (int i = 0; i < categorias.size(); i++) {
                                                    Categoria categoria = categoriaController.obtenerCategoria(categorias.get(i));
                                                    servicio.setCategorias(categoria);
                                                }
                                                for (int i = 0; i < imagenes.size(); i++) {
                                                    String imagen = imagenes.get(i);
                                                    servicio.setImagen(imagen);
                                                }
                                                servicioService.guardarServicio(servicio);
                                                proveedor.setServicio(servicio);
                                                return "OK";
                                            } else {
                                                return "Debe ingresar como minimo una categoria";
                                            }
                                        } else {
                                            return "Ciudad de origen debe ser diferente a ciudad de destino";
                                        }
                                    } else {
                                        return "Ciudad de destino invalido";
                                    }
                                } else {
                                    return "Elija ciudad de destino";
                                }
                            } else {
                                return "Ciudad de origen invalido";
                            }
                        } else {
                            return "Precio debe ser mayor a 0";
                        }
                    } else {
                        return "Descripcion no puede quedar vacio";
                    }
                } else {
                    return "Ya existe servicio";
                }
            } else {
                return "No existe proveedor";
            }
        } else {
            return "Nombre servicio no puede quedar vacio";
        }
    }

    @Override
    public boolean existeServicio(String nombreServicio, String nickNameProveedor) {
        return servicioService.existeServicio(nombreServicio, nickNameProveedor);
    }

    @Override
    public Servicio obtenerServicio(String nombreServicio, String nickNameProveedor) {
        return servicioService.obtenerServicio(nombreServicio, nickNameProveedor);
    }

    @Override
    public List<Servicio> obtenerTodosServicios() {
        return servicioService.obtenerTodosServicios();
    }

    @Override
    public int cantidadServicios() {
        return servicioService.cantidadServicios();
    }

    @Override
    public void vaciarPersistenciaServicio() {
        servicioService.vaciarPersistenciaServicio();
    }

    @Override
    public String verificarPrecio(String precio) {
        if (!precio.isEmpty()) {
            int validadorPrecio = 0;
            for (int i = 0; i < precio.length(); i++) {
                if (precio.charAt(i) == '.') {
                    validadorPrecio++;
                }
            }
            if (validadorPrecio <= 1 && !precio.endsWith(".") && !precio.startsWith(".")) {
                return "OK";
            } else {
                return "Precio no valido";
            }
        } else {
            return "Precio no puede quedar vacio";
        }
    }

}
