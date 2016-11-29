/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import uy.edu.cure.servidor.central.dto.Categoria;
import uy.edu.cure.servidor.central.dto.Ciudad;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.CategoriaControllerImpl;
import uy.edu.cure.servidor.central.lib.ServicioControllerImpl;
import uy.edu.cure.servidor.central.lib.CiudadControllerImpl;

/**
 *
 * @author SCN
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.ServicioWS")
public class ServicioWSImpl implements ServicioWS {

    @Override
    public Servicio obtenerServicioWS(String nombreServicio, String nickProveedor) {
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        Servicio servicioAux;
        servicioAux = serviciocontroller.obtenerServicio(nombreServicio, nickProveedor);
        return servicioAux;
    }

    @Override
    public String crearServicioWS(String nombreServicio, String descripcion, double precio, String ciudadOrigen, String ciudadDestino, List<String> categorias, List<String> imagenes, String nickNameProveedor) {
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        String retorno;
        retorno = serviciocontroller.crearServicio(nombreServicio, descripcion, precio, ciudadOrigen, ciudadDestino, categorias, imagenes, nickNameProveedor);
        return retorno;
    }

    @Override
    public List<Servicio> obtenerTodosServiciosWS() {
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        List<Servicio> serviciosAux;
        serviciosAux = serviciocontroller.obtenerTodosServicios();
        return serviciosAux;
    }

    @Override
    public List<String> obtenerImagenesServicioWS(String nombreServicio, String nickProveedor) {
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        List<String> imagenesAux;
        imagenesAux = serviciocontroller.obtenerServicio(nombreServicio, nickProveedor).getImagenes();
        return imagenesAux;
    }

    @Override
    public List<Categoria> obtenerCategoriasServicioWS(String nombreServicio, String nickProveedor) {
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        List<Categoria> categoriasAux;
        categoriasAux = serviciocontroller.obtenerServicio(nombreServicio, nickProveedor).getCategorias();
        return categoriasAux;
    }

    @Override
    public String verificarPrecio(String precio) {
        ServicioControllerImpl servicioController = new ServicioControllerImpl();
        return servicioController.verificarPrecio(precio);
    }

    @Override
    public void editarDescripcion(String servicio, String proveedor, String descripcion) {
        ServicioControllerImpl servicioController = new ServicioControllerImpl();
        servicioController.obtenerServicio(servicio, proveedor).setDescripcion(descripcion);

    }

    @Override
    public void editarPrecio(String servicio, String proveedor, double precio) {
        ServicioControllerImpl servicioController = new ServicioControllerImpl();
        servicioController.obtenerServicio(servicio, proveedor).setPrecio(precio);

    }

    @Override
    public void editarCiudadOrigen(String servicio, String proveedor, String ciudad) {
        ServicioControllerImpl servicioController = new ServicioControllerImpl();
        CiudadControllerImpl ciudadController = new CiudadControllerImpl();
        Ciudad ciudadAux = ciudadController.obtenerCiudad(ciudad);
        servicioController.obtenerServicio(servicio, proveedor).setOrigen(ciudadAux);

    }

    @Override
    public void editarCiudadDestino(String servicio, String proveedor, String ciudad) {
        ServicioControllerImpl servicioController = new ServicioControllerImpl();
        CiudadControllerImpl ciudadController = new CiudadControllerImpl();
        Ciudad ciudadAux = ciudadController.obtenerCiudad(ciudad);
        servicioController.obtenerServicio(servicio, proveedor).setDestino(ciudadAux);

    }

    @Override
    public void editarCategorias(String servicio, String proveedor, List<String> categorias) {
        ServicioControllerImpl servicioController = new ServicioControllerImpl();
        CategoriaControllerImpl categoriaController = new CategoriaControllerImpl();
        Categoria categoriaAux;
        servicioController.obtenerServicio(servicio, proveedor).getCategorias().clear();
        for (int i = 0; i < categorias.size(); i++) {
            categoriaAux = categoriaController.obtenerCategoria(categorias.get(i));
            servicioController.obtenerServicio(servicio, proveedor).setCategorias(categoriaAux);
        }


    }

    List<String> imagenes;
}
