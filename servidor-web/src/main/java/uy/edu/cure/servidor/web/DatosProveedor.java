/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.dto.Servicio;
import uy.edu.cure.servidor.central.lib.UsuarioControllerImpl;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

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
    @Jeringa(value = "usuariocontroller")
    private UsuarioControllerImpl usuariocontroller;

    public DatosProveedor() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        servicios = new ArrayList<>();
        proveedores = new ArrayList<>();
        proveedores.addAll(usuariocontroller.obtenerProveedores());
    }

    public void accionProveedor() {
        proveedor = usuariocontroller.obtenerProveedor(this.nickName);
        this.servicios = proveedor.getServicios();
    }
    
    
    public List<Servicio> getServicios() {
        return this.servicios;

    }
    
    
    public Proveedor getProveedor() {
        try {
            if (!this.proveedor.equals(null)) {
                proveedor = usuariocontroller.obtenerProveedor(this.nickName);
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
