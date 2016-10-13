/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.List;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Proveedor;

/**
 *
 * @author guido
 */
public interface UsuarioController {

    public int crearCliente(String nickName, String nombre, String apellido, String correo, int dia, int mes, int anio, String imagenPerfil, String passWord, String passWordConfirm);

    public int crearProveedor(String nickName, String nombre, String apellido, String correo, int dia, int mes, int anio, String nombreEmpresa, String linkEmpresa, String imagenPerfil, String passWord, String passWordConfirm);

    public boolean existeCliente(String nickName);

    public boolean existeProveedor(String nickName);
    
    public boolean existeNickName (String nickName);

    public boolean correoValido(String correo);

    public boolean LogInCliente(String nickName, String passWord);

    public boolean existeCorreo(String correo);

    public boolean LogInProveedor(String nickName, String passWord);
    
    public boolean validarFecha(int dia, int mes, int anio);

    public Cliente obtenerCliente(String nickName);

    public Proveedor obtenerProveedor(String nickName);

    public List<Proveedor> obtenerProveedores();

    public List<Cliente> obtenerCientes();

    public void vaciarPeristenciaP();

    public void vaciarPeristenciaC();

}
