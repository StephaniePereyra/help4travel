/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;
import uy.edu.cure.servidor.central.soap.client.UsuarioWS;
import uy.edu.cure.servidor.central.soap.client.UsuarioWSImplService;

/**
 *
 * @author SCN
 */
@ManagedBean
//@RequestScoped
//@ViewScoped
@SessionScoped

public class DatosProveedor {

    private Proveedor proveedor;
    private List<Proveedor> proveedores;
    private List<Servicio> servicios;
    private String nickName;
    private String nombreServicio;
    private UsuarioWSImplService usuarioWSImplService;
    private UsuarioWS portUsuario;
    private Converter convertidor;
    
    public DatosProveedor() {
        convertidor = new Converter();
        servicios = new ArrayList<>();
        proveedores = new ArrayList<>();
        try {
            usuarioWSImplService = new UsuarioWSImplService(new URL("http://localhost:8080/servidor-central-webapp/soap/UsuarioWSImplService?wsdl"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(DatosProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        portUsuario = usuarioWSImplService.getUsuarioWSImplPort();
        List auxiliar = portUsuario.obtenerTodosProveedoresWS();
        
        for(int i=0;i<auxiliar.size();i++){
            proveedores.add(i,convertidor.convertirProveedor(portUsuario.obtenerTodosProveedoresWS().get(i)));
        }
        
    }

    public void accionProveedor() {
        proveedor = convertidor.convertirProveedor(portUsuario.obtenerProveedorWS(nickName));
        //this.servicios = proveedor.getServicios();
    }
    
    
    public List<Servicio> getServicios() {
        return this.servicios;

    }
    
    
    public Proveedor getProveedor() {
        try {
            if (!this.proveedor.equals(null)) {
                proveedor = convertidor.convertirProveedor(portUsuario.obtenerProveedorWS(nickName));
                return proveedor;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = null;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

}
