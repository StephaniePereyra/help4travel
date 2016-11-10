/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Reserva;
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

}