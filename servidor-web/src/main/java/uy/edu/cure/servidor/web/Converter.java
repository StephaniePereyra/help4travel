/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import uy.edu.cure.servidor.central.dto.Categoria;
import uy.edu.cure.servidor.central.dto.Ciudad;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Pais;
import uy.edu.cure.servidor.central.dto.Promocion;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Reserva;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.soap.client.CategoriaWS;
import uy.edu.cure.servidor.central.soap.client.CategoriaWSImplService;
import uy.edu.cure.servidor.central.soap.client.PaisWS;
import uy.edu.cure.servidor.central.soap.client.PaisWSImplService;
import uy.edu.cure.servidor.central.soap.client.PromocionWS;
import uy.edu.cure.servidor.central.soap.client.PromocionWSImplService;
import uy.edu.cure.servidor.central.soap.client.ReservaWS;
import uy.edu.cure.servidor.central.soap.client.ReservaWSImplService;
import uy.edu.cure.servidor.central.soap.client.ServicioWS;
import uy.edu.cure.servidor.central.soap.client.ServicioWSImplService;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

/**
 *
 * @author SCN
 */

public class Converter {
    
    private Cliente clienteAuxiliar;
    private Proveedor proveedorAuxiliar;
    private Categoria categoriaAuxiliar;
    private Pais paisAuxiliar;
    private Ciudad ciudadAuxiliar;
    private Servicio servicioAuxiliar;
    private Promocion promocionAuxiliar;
    private Reserva reservaAuxliar;
    
    public Converter (){
        clienteAuxiliar = new Cliente();
        proveedorAuxiliar = new Proveedor();
        categoriaAuxiliar = new Categoria();
        paisAuxiliar = new Pais();
        ciudadAuxiliar = new Ciudad();
        servicioAuxiliar = new Servicio();
        promocionAuxiliar = new Promocion();
        reservaAuxliar = new Reserva();
    }
    
    public Cliente convertirCliente (uy.edu.cure.servidor.central.soap.client.Cliente cliente){
        clienteAuxiliar = new Cliente();      
        clienteAuxiliar.setNickName(cliente.getNickName());
        clienteAuxiliar.setNombre(cliente.getNombre());
        clienteAuxiliar.setApellido(cliente.getApellido());
        clienteAuxiliar.setCorreo(cliente.getCorreo());
        Date fechaAux = new Date();
        fechaAux.setDate(cliente.getFechanacimiento().getDay());
        fechaAux.setMonth(cliente.getFechanacimiento().getMonth());
        fechaAux.setYear(cliente.getFechanacimiento().getYear());
        clienteAuxiliar.setFechanacimiento(fechaAux);
        clienteAuxiliar.setImagenPerfil(cliente.getImagenPerfil());
        clienteAuxiliar.setPassWord(cliente.getPassWord());
        return clienteAuxiliar;
    }
    
    public Proveedor convertirProveedor (uy.edu.cure.servidor.central.soap.client.Proveedor proveedor){
        proveedorAuxiliar = new Proveedor();
        proveedorAuxiliar.setNickName(proveedor.getNickName());
        proveedorAuxiliar.setNombre(proveedor.getNombre());
        proveedorAuxiliar.setApellido(proveedor.getApellido());
        proveedorAuxiliar.setCorreo(proveedor.getCorreo());
        Date fechaAux = new Date();
        fechaAux.setDate(proveedor.getFechanacimiento().getDay());
        fechaAux.setMonth(proveedor.getFechanacimiento().getMonth());
        fechaAux.setYear(proveedor.getFechanacimiento().getYear());
        proveedorAuxiliar.setFechanacimiento(fechaAux);
        proveedorAuxiliar.setImagenPerfil(proveedor.getImagenPerfil());
        proveedorAuxiliar.setNombreEmpresa(proveedor.getNombreEmpresa());
        proveedorAuxiliar.setLinkEmpresa(proveedor.getLinkEmpresa());
        
        /*UsuarioWSImplService usuarioWSImplService = null;
        try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsuarioWS portUsuario = usuarioWSImplService.getUsuarioWSImplPort();
        
        for(int i=0;i<portUsuario.serviciosProveedor(proveedor.getNickName()).size();){
            proveedorAuxiliar.setServicio(this.convertirServicio(portUsuario.serviciosProveedor(proveedor.getNickName()).get(i)));
        }*/
        
        return proveedorAuxiliar;
    }
    
    public Categoria convertirCategoria (uy.edu.cure.servidor.central.soap.client.Categoria categoria){
        categoriaAuxiliar = new Categoria();

        categoriaAuxiliar.setNombre(categoria.getNombre());

        CategoriaWSImplService categoriaWSImplService = null;
        try {
            categoriaWSImplService = new CategoriaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/CategoriaWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        CategoriaWS portCategoria = categoriaWSImplService.getCategoriaWSImplPort();
        
        for(int i=0;i<portCategoria.obtenerHijosWS(categoria.getNombre()).size();i++){
            categoriaAuxiliar.setHijos(this.convertirCategoria(portCategoria.obtenerHijosWS(categoria.getNombre()).get(i)));
        }
        categoriaAuxiliar.setPadre(this.convertirCategoria(categoria.getPadre()));
        return categoriaAuxiliar;
    }
    
    public Pais convertirPais (uy.edu.cure.servidor.central.soap.client.Pais pais){
        paisAuxiliar = new Pais();

        paisAuxiliar.setNombre(pais.getNombre());
        
        PaisWSImplService paisWSImplService = null;
        try {
            paisWSImplService = new PaisWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/PaisWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
                PaisWS portPais = paisWSImplService.getPaisWSImplPort();
        
                for(int i = 0;i<portPais.obtenerCiudadesWS(pais.getNombre()).size();i++){
                   paisAuxiliar.setCiudades(convertirCiudad(portPais.obtenerCiudadesWS(pais.getNombre()).get(i)));
                }
                
        return paisAuxiliar;
    }
    
    public Ciudad convertirCiudad (uy.edu.cure.servidor.central.soap.client.Ciudad ciudad){
        ciudadAuxiliar = new Ciudad();
        
        ciudadAuxiliar.setNombre(ciudad.getNombre());
        ciudadAuxiliar.setPais(this.convertirPais(ciudad.getPais()));
        return ciudadAuxiliar;
    }
    
    public Servicio convertirServicio (uy.edu.cure.servidor.central.soap.client.Servicio servicio){
        servicioAuxiliar = new Servicio();
        
        servicioAuxiliar.setNombre(servicio.getNombre());
        servicioAuxiliar.setPrecio(servicio.getPrecio());
        servicioAuxiliar.setDescripcion(servicio.getDescripcion());
        servicioAuxiliar.setOrigen(this.convertirCiudad(servicio.getOrigen()));
        servicioAuxiliar.setDestino(this.convertirCiudad(servicio.getDestino()));
        servicioAuxiliar.setProveedor(this.convertirProveedor(servicio.getProveedor()));
        
        ServicioWSImplService servicioWSImplService = null;
        try {
            servicioWSImplService = new ServicioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ServicioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServicioWS portServicio = servicioWSImplService.getServicioWSImplPort();

        for(int i =0;i<portServicio.obtenerImagenesServicioWS(servicio.getNombre(), servicio.getProveedor().getNickName()).size();i++){
            servicioAuxiliar.setImagen(portServicio.obtenerImagenesServicioWS(servicio.getNombre(), servicio.getProveedor().getNickName()).get(i));
        }
        
        /*for(int u =0;u<portServicio.obtenerCategoriasServicioWS(servicio.getNombre(), servicio.getProveedor().getNickName()).size();u++){
            servicioAuxiliar.setCategorias(this.convertirCategoria(portServicio.obtenerCategoriasServicioWS(servicio.getNombre(), servicio.getProveedor().getNickName()).get(u)));
        }*/
        return servicioAuxiliar;
    }
    
    public Promocion convertirPromocion (uy.edu.cure.servidor.central.soap.client.Promocion promocion){
        promocionAuxiliar = new Promocion();
       
        promocionAuxiliar.setNombre(promocion.getNombre());
        promocionAuxiliar.setDescuento(promocion.getDescuento());
        promocionAuxiliar.setPrecioTotal(promocion.getPrecioTotal());
        promocionAuxiliar.setProveedor(this.convertirProveedor(promocion.getProveedor()));
        
        PromocionWSImplService promocionWSImplService = null;
        try {            
            promocionWSImplService = new PromocionWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/PromocionWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        PromocionWS portPromocion = promocionWSImplService.getPromocionWSImplPort();
        
        for(int i=0;i<portPromocion.serviciosPromocionWS(promocion.getNombre(), promocion.getProveedor().getNickName()).size();i++){
            promocionAuxiliar.setServicios(this.convertirServicio(portPromocion.serviciosPromocionWS(promocion.getNombre(), promocion.getProveedor().getNickName()).get(i)));
        }
        
        return promocionAuxiliar;
    }
    
    public Reserva convertirReserva (uy.edu.cure.servidor.central.soap.client.Reserva reserva){
        reservaAuxliar = new Reserva();
        reservaAuxliar.setNumero(reserva.getNumero());
        reservaAuxliar.setPrecio(reserva.getPrecio());
        reservaAuxliar.setEstado(reserva.getEstado());
        reservaAuxliar.setCliente(this.convertirCliente(reserva.getCliente()));
        Date fechaCreacion = new Date();
        fechaCreacion.setDate(reserva.getFechaCreacion().getDay());
        fechaCreacion.setMonth(reserva.getFechaCreacion().getMonth());
        fechaCreacion.setYear(reserva.getFechaCreacion().getYear());
        reservaAuxliar.setFechaCreacion(fechaCreacion);
        
       ReservaWSImplService reservaWSImplService = null;
        try {
            reservaWSImplService = new ReservaWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/ReservaWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        ReservaWS portReserva = reservaWSImplService.getReservaWSImplPort();

        for(int i=0;i<portReserva.obtenerServiciosReservaWS(reserva.getNumero()).size();i++){
            reservaAuxliar.setServicio(this.convertirServicio(portReserva.obtenerServiciosReservaWS(reserva.getNumero()).get(i)));
        }
        
        for(int u=0;u<portReserva.obtenerPromocionesReservaWS(reserva.getNumero()).size();u++){
            reservaAuxliar.setPromocion(this.convertirPromocion(portReserva.obtenerPromocionesReservaWS(reserva.getNumero()).get(u)));
        }
        
        return reservaAuxliar;
    }
    
}
