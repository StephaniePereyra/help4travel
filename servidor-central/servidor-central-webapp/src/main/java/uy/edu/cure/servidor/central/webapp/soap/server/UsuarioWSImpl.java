/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.PromocionController;
import uy.edu.cure.servidor.central.lib.PromocionControllerImpl;
import uy.edu.cure.servidor.central.lib.ServicioControllerImpl;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;

/**
 *
 * @author SCN
 */
@WebService(endpointInterface = "uy.edu.cure.servidor.central.webapp.soap.server.UsuarioWS")
public class UsuarioWSImpl implements UsuarioWS {

    @Override
    public Cliente obtenerClienteWS(String nickName) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        Cliente clienteAux;
        usuariocontroller.crearCliente("NickPrueba", "NombrePrueba", "ApellidoPrueba", "correo@prueba", 1, 2, 1995, "rutaprueba", "pass", "pass");
        clienteAux = usuariocontroller.obtenerCliente(nickName);
        return clienteAux;
    }

    @Override
    public Proveedor obtenerProveedorWS(String nickName) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        Proveedor proveedorAux;
        proveedorAux = usuariocontroller.obtenerProveedor(nickName);
        return proveedorAux;
    }

    @Override
    public int crearClienteWS(String nickName, String nombre, String apellido, String correo, int dia, int mes, int anio, String rutaImg, String password, String confirmPassword) {
        int retorno;
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        retorno = usuariocontroller.crearCliente(nickName, nombre, apellido, correo, dia, mes, anio, rutaImg, password, confirmPassword);
        return retorno;
    }

    @Override
    public Reserva obtenerCarritoClienteWS(String nickName) {
        Reserva carritoAux;
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        carritoAux = usuariocontroller.obtenerCliente(nickName).getCarrito();
        return carritoAux;
    }

    @Override
    public boolean logInClienteWS(String nickName, String password) {
        boolean retorno;
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        retorno = usuariocontroller.LogInCliente(nickName, password);
        return retorno;
    }

    @Override
    public List<Proveedor> obtenerTodosProveedoresWS() {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        List<Proveedor> proveedoresAux;
        proveedoresAux = usuariocontroller.obtenerProveedores();
        return proveedoresAux;
    }

    @Override
    public int crearProeveedorWS(String nickName, String nombre, String apellido, String correo, int dia, int mes, int anio, String nombreEmpresa, String linkEmpresa, String rutaImg, String password, String confirmPassword) {
        int retorno;
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        retorno = usuariocontroller.crearProveedor(nickName, nombre, apellido, correo, dia, mes, anio, nombreEmpresa, linkEmpresa, rutaImg, password, confirmPassword);
        return retorno;
    }

    @Override
    public boolean existeCorreoWS(String correo) {
        boolean retorno;
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        retorno = usuariocontroller.existeCorreo(correo);
        return retorno;
    }

    @Override
    public boolean correoValidoWS(String correo) {
        boolean retorno;
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        retorno = usuariocontroller.correoValido(correo);
        return retorno;
    }

    @Override
    public boolean existeClienteWS(String nickName) {
        boolean retorno;
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        retorno = usuariocontroller.existeCliente(nickName);
        return retorno;
    }

    @Override
    public boolean existeProveedorWS(String nickName) {
        boolean retorno;
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        retorno = usuariocontroller.existeProveedor(nickName);
        return retorno;
    }

    @Override
    public List<Promocion> obtenerPromocionesCarroWS(String nickName) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        List<Promocion> promocionesAux;
        promocionesAux = usuariocontroller.obtenerCliente(nickName).getCarrito().getPromociones();
        return promocionesAux;
    }

    @Override
    public List<Servicio> obtenerServiciosCarroWS(String nickName) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        List<Servicio> serviciosAux;
        serviciosAux = usuariocontroller.obtenerCliente(nickName).getCarrito().getServicios();
        return serviciosAux;
    }

    @Override
    public List<Servicio> serviciosProveedor(String nickName) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        List<Servicio> serviciosAux;
        serviciosAux = usuariocontroller.obtenerProveedor(nickName).getServicios();
        return serviciosAux;
    }

    @Override
    public List<Reserva> obtenerReservasClienteWS(String nickName) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        List<Reserva> reservasAux;
        reservasAux = usuariocontroller.obtenerCliente(nickName).getReservas();
        return reservasAux;
    }

    @Override
    public List<Cliente> obtenerTodosClientes() {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        List<Cliente> clientesAux;
        clientesAux = usuariocontroller.obtenerCientes();
        return clientesAux;
    }
    
    @Override
    public void agregarServicioWS(String nickName,String servicio,String proveedor, int cantidad){
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        Servicio servicioObj = new Servicio();
        servicioObj = serviciocontroller.obtenerServicio(servicio, proveedor);
        usuariocontroller.obtenerCliente(nickName).getCarrito().setServicio(servicioObj);
        usuariocontroller.obtenerCliente(nickName).getCarrito().getCantidadServicios().add(cantidad);
    }
    
    @Override
    public void agregarPromocionWS(String nickName,String promocion,String proveedor, int cantidad){
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        PromocionController promocioncontroller = new PromocionControllerImpl();
        Promocion promocionObj = new Promocion();
        promocionObj = promocioncontroller.obtenerPromocion(promocion,proveedor);
        usuariocontroller.obtenerCliente(nickName).getCarrito().setPromocion(promocionObj);
        usuariocontroller.obtenerCliente(nickName).getCarrito().getCantidadPromociones().add(cantidad);
    }

    @Override
    public List<Integer> obtenerCantServiciosCarroWS(String nickName) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        List<Integer> aux;
        aux = usuariocontroller.obtenerCliente(nickName).getCarrito().getCantidadServicios();
        return aux;
    }

    @Override
    public List<Integer> obtenerCantPromosCarroWS(String nickName) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        List<Integer> aux;
        aux = usuariocontroller.obtenerCliente(nickName).getCarrito().getCantidadPromociones();
        return aux;
    }

    @Override
    public void eliminarServicioCarroWS(String nickName, String servicio, String proveedor) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        Servicio auxiliar = serviciocontroller.obtenerServicio(servicio, proveedor);
        usuariocontroller.obtenerCliente(nickName).getCarrito().getServicios().remove(auxiliar);
    }

    @Override
    public void eliminarPromoCarroWS(String nickName, int index) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        usuariocontroller.obtenerCliente(nickName).getCarrito().getPromociones().remove(index);
    }

    @Override
    public boolean carroContieneServicioWS(String nickCliente, String nombreServicio, String proveedor) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        Servicio servicioAux;
        servicioAux = serviciocontroller.obtenerServicio(nombreServicio, proveedor);
        boolean retorno;
        retorno = usuariocontroller.obtenerCliente(nickCliente).getCarrito().getServicios().contains(servicioAux);
        return retorno;
    }

    @Override
    public boolean carroContienePromocionWS(String nickCliente, String nombrePromo, String proveedor) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        PromocionControllerImpl promocioncontrller = new PromocionControllerImpl();
        boolean retorno;
        Promocion promocionAux;
        promocionAux = promocioncontrller.obtenerPromocion(nombrePromo, proveedor);
        retorno = usuariocontroller.obtenerCliente(nickCliente).getCarrito().getPromociones().contains(promocionAux);
        return retorno;
    }

    @Override
    public void modCantidadServicioCarro(String nickCliente, int index, int cantidad) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        usuariocontroller.obtenerCliente(nickCliente).getCarrito().getCantidadServicios().remove(index);
        usuariocontroller.obtenerCliente(nickCliente).getCarrito().getCantidadServicios().add(index, cantidad);
    }

    @Override
    public void modCantidadPromoCarro(String nickCliente, int index, int cantidad) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        usuariocontroller.obtenerCliente(nickCliente).getCarrito().getCantidadPromociones().remove(index);
        usuariocontroller.obtenerCliente(nickCliente).getCarrito().getCantidadPromociones().add(index, cantidad);
    }

    @Override
    public void setPrecioCarroWS(String nickCliente, double precio) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        usuariocontroller.obtenerCliente(nickCliente).getCarrito().setPrecio(precio);
    }

    @Override
    public int obtenerPosicionServicioEnCarro(String nickCliente, String nombreServicio, String proveedor) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        int retorno;
        Servicio aux;
        ServicioControllerImpl serviciocontroller = new ServicioControllerImpl();
        aux = serviciocontroller.obtenerServicio(nombreServicio, proveedor);
        retorno = usuariocontroller.obtenerCliente(nickCliente).getCarrito().getServicios().indexOf(aux);
        return retorno;
    }

    @Override
    public int obtenerPosicionPromoEnCarro(String nickCliente, String nombrePromo, String proveedor) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        int retorno;
        PromocionControllerImpl promocioncontrller = new PromocionControllerImpl();
        Promocion aux;
        aux = promocioncontrller.obtenerPromocion(nombrePromo, proveedor);
        retorno = usuariocontroller.obtenerCliente(nickCliente).getCarrito().getPromociones().indexOf(aux);
        return retorno;
    }

    @Override
    public void eliminarCantidadServicioCarro(String nickCliente, int index) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        usuariocontroller.obtenerCliente(nickCliente).getCarrito().getCantidadServicios().remove(index);
    }

    @Override
    public void eliminarCantidadPromoCarro(String nickCliente, int index) {
        UsuarioControllerImpl usuariocontroller = new UsuarioControllerImpl();
        usuariocontroller.obtenerCliente(nickCliente).getCarrito().getCantidadPromociones().remove(index);
    }
    
}
