/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.webapp.soap.server;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;

/**
 *
 * @author SCN
 */
@WebService
public interface UsuarioWS {

    @WebMethod
    public Cliente obtenerClienteWS(String nickName);

    @WebMethod
    public Proveedor obtenerProveedorWS(String nickName);
    
    @WebMethod
    public List<Servicio> serviciosProveedor (String nickName);

    @WebMethod
    public List<Proveedor> obtenerTodosProveedoresWS();

    @WebMethod
    public int crearClienteWS(String nickName, String nombre, String apellido, String correo, int dia, int mes, int anio, String rutaImg, String password, String confirmPassword);

    @WebMethod
    public int crearProeveedorWS(String nickName, String nombre, String apellido, String correo, int dia, int mes, int anio, String nombreEmpresa, String linkEmpresa, String rutaImg, String password, String confirmPassword);

    @WebMethod
    public Reserva obtenerCarritoClienteWS(String nickName);
    
    @WebMethod
    public List<Integer> obtenerCantServiciosCarroWS (String nickName);
    
    @WebMethod
    public List<Integer> obtenerCantPromosCarroWS(String nickName);

    @WebMethod
    public boolean logInClienteWS(String nickName, String password);
    
    @WebMethod
    public boolean logInProveedorWS(String nickName, String password);

    @WebMethod
    public boolean existeCorreoWS(String correo);

    @WebMethod
    public boolean correoValidoWS(String correo);

    @WebMethod
    public boolean existeClienteWS(String nickName);

    @WebMethod
    public boolean existeProveedorWS(String nickName);
    
    @WebMethod
    public List<Promocion> obtenerPromocionesCarroWS (String nickName);
    
    @WebMethod
    public List<Servicio> obtenerServiciosCarroWS (String nickName);
    
    @WebMethod
    public List<Reserva> obtenerReservasClienteWS (String nickName); 
    
    @WebMethod
    public List<Cliente> obtenerTodosClientes ();
    
    @WebMethod
    public void agregarServicioWS(String nickName,String servicio,String proveedor, int cantidad);
    
    @WebMethod
    public void agregarPromocionWS(String nickName,String promocion,String proveedor, int cantidad);
    
    @WebMethod
    public void eliminarServicioCarroWS (String nickName, String servicio, String proveedor);
    
    @WebMethod
    public void eliminarPromoCarroWS (String nickName, int index);
    
    @WebMethod
    public boolean carroContieneServicioWS (String nickCliente, String nombreServicio, String proveedor);
    
    @WebMethod
    public boolean carroContienePromocionWS (String nickCliente, String nombrePromo, String proveedor);
    
    @WebMethod
    public void modCantidadServicioCarro (String nickCliente, int index, int cantidad);
    
    @WebMethod
    public void modCantidadPromoCarro (String nickCliente, int index, int cantidad);
    
    @WebMethod
    public void eliminarCantidadServicioCarro (String nickCliente, int index);
    
    @WebMethod
    public void eliminarCantidadPromoCarro (String nickCliente, int index);
    
    @WebMethod
    public void setPrecioCarroWS (String nickCliente, double precio);
    
    @WebMethod
    public int obtenerPosicionServicioEnCarro (String nickCliente, String nombreServicio, String proveedor);
    
    @WebMethod
    public int obtenerPosicionPromoEnCarro (String nickCliente, String nombrePromo, String proveedor);
    
    @WebMethod
    public List<Promocion> obtenerPormoProveedor (String nickProveedor);
}
