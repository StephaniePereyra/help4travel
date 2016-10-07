/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Proveedor;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

/**
 *
 * @author guido
 */
public class UsuarioControllerImpl implements UsuarioController {

    @Jeringa(value = "usuarioservice")
    UsuarioService usuarioService;

    public UsuarioControllerImpl() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int crearCliente(String nickName, String nombre, String apellido, String correo, int dia, int mes, int anio, String imagenPerfil, String passWord) {
        if (!usuarioService.existeCliente(nickName) && !usuarioService.existeProveedor(nickName)) {
            if (!usuarioService.existeCorreo(correo)) {
                if (correoValido(correo)) {
                    if (validarFecha(dia, mes, anio)) {
                        Date fechanacimiento = new Date();
                        fechanacimiento.setDate(dia);
                        fechanacimiento.setMonth(mes);
                        fechanacimiento.setYear(anio);
                        Cliente cliente = new Cliente(nickName, nombre, apellido, correo, fechanacimiento, imagenPerfil, passWord);
                        usuarioService.guardarCliente(cliente);
                    } else {
                        return 4;
                    }
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
        return -1;
    }

    @Override
    public int crearProveedor(String nickName, String nombre, String apellido, String correo, int dia, int mes, int anio, String nombreEmpresa, String linkEmpresa, String imagenPerfil, String passWord) {
        if (!usuarioService.existeCliente(nickName) && !usuarioService.existeProveedor(nickName)) {
            if (!usuarioService.existeCorreo(correo)) {
                if (correoValido(correo)) {
                    if (validarFecha(dia, mes, anio)) {
                        Date fechanacimiento = new Date();
                        fechanacimiento.setDate(dia);
                        fechanacimiento.setMonth(mes);
                        fechanacimiento.setYear(anio);
                        Proveedor proveedor = new Proveedor(nickName, nombre, apellido, correo, fechanacimiento, nombreEmpresa, linkEmpresa, imagenPerfil, passWord);
                        usuarioService.guardarProveedor(proveedor);
                    } else {
                        return 4;
                    }
                } else {
                    return 3;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
        return -1;

    }

    @Override
    public boolean existeCliente(String nickName) {
        return usuarioService.existeCliente(nickName);
    }

    @Override
    public boolean existeProveedor(String nickName) {
        return usuarioService.existeProveedor(nickName);
    }

    @Override
    public Cliente obtenerCliente(String nickName) {
        return usuarioService.obtenerCliente(nickName);
    }

    @Override
    public Proveedor obtenerProveedor(String nickName) {
        return usuarioService.obtenerProveedor(nickName);
    }

    @Override
    public List<Proveedor> obtenerProveedores() {
        return usuarioService.obtenerProveedores();
    }

    @Override
    public List<Cliente> obtenerCientes() {
        return usuarioService.obtenerCientes();
    }

    @Override
    public void vaciarPeristenciaP() {
        usuarioService.vaciarPersistenciaP();
    }

    @Override
    public void vaciarPeristenciaC() {
        usuarioService.vaciarPersistenciaC();
    }

    @Override
    public boolean LogInCliente(String nickName, String passWord) {
        if (existeCliente(nickName)) {
            Cliente clienteAux = obtenerCliente(nickName);
            if (clienteAux.getPassWord().equals(passWord)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean LogInProveedor(String nickName, String passWord) {
        if (existeProveedor(nickName)) {
            Proveedor proveedorAux = obtenerProveedor(nickName);
            if (proveedorAux.getPassWord().equals(passWord)) {
                return true;
            }
        }
        return false;
    }

    private boolean validarFecha(int dia, int mes, int anio) {
        if (anio > 0 && anio < 2017) {
            if (mes > 0 && mes < 13) {
                if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                    if (dia > 0 && dia < 32) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                    if (dia > 0 && dia < 31) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (mes == 2 && anio % 4 == 0) {
                    if (dia > 0 && dia < 30) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (mes == 2) {
                    if (dia > 0 && dia < 29) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean correoValido(String correo) {
        int cantArroba = 0;
        for (int i = 0; i < correo.length(); i++) {
            if (correo.charAt(i) == '@') {
                cantArroba++;
            }
        }
        if (cantArroba == 1) {
            if (!correo.startsWith("@") && !correo.endsWith("@")) {
                return true;
            }
        }
        return false;
    }
}
