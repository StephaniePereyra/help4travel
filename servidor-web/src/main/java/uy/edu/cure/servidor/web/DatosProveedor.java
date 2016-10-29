/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.web;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
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
//@ViewScoped
@SessionScoped

public class DatosProveedor {

    private Proveedor proveedor;
    private ArrayList<Proveedor> proveedores;
    private ArrayList<Servicio> servicios;
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
    }
    
    
    public ArrayList<Servicio> getServicios() {
        try {
            if (this.servicios.isEmpty()) {
                servicios.addAll(usuariocontroller.obtenerProveedor(this.nickName).getServicios());
            }
            return servicios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(ArrayList<Proveedor> proveedores) {
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

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

}
