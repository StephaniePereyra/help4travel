/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Cliente;
import uy.edu.cure.servidor.central.dto.Proveedor;

/**
 *
 * @author guido
 */
public class UsuarioService implements UsuarioServiceInterface {

    private static List<Cliente> clientes = new ArrayList<Cliente>();
    private static List<Proveedor> proveedores = new ArrayList<Proveedor>();

    public UsuarioService() {

    }

    @Override
    public boolean guardarCliente(Cliente cliente) {
        if (cliente != null) {
            clientes.add(cliente);
            return true;
        }
        return false;
    }

    @Override
    public boolean guardarProveedor(Proveedor proveedor) {
        if (proveedor != null) {
            proveedores.add(proveedor);
            return true;
        }
        return false;
    }

    @Override
    public boolean existeCliente(String nickName) {
        Iterator<Cliente> iteratorClientes = clientes.iterator();
        while (iteratorClientes.hasNext()) {
            Cliente clienteAuxiliar = iteratorClientes.next();
            if (clienteAuxiliar.getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existeProveedor(String nickName) {
        Iterator<Proveedor> iteratorProveedores = proveedores.iterator();
        while (iteratorProveedores.hasNext()) {
            Proveedor proveedorAuxiliar = iteratorProveedores.next();
            if (proveedorAuxiliar.getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Cliente obtenerCliente(String nickName) {
        Iterator<Cliente> iteratorClientes = clientes.iterator();
        while (iteratorClientes.hasNext()) {
            Cliente clienteAuxiliar = iteratorClientes.next();
            if (clienteAuxiliar.getNickName().equals(nickName)) {
                return clienteAuxiliar;
            }
        }
        return null;
    }

    @Override
    public Proveedor obtenerProveedor(String nickName) {
        Iterator<Proveedor> iteratorProveedores = proveedores.iterator();
        while (iteratorProveedores.hasNext()) {
            Proveedor proveedorAuxiliar = iteratorProveedores.next();
            if (proveedorAuxiliar.getNickName().equals(nickName)) {
                return proveedorAuxiliar;
            }
        }
        return null;
    }

    @Override
    public boolean existeCorreo(String correo) {
        Iterator<Cliente> iteratorClientes = clientes.iterator();
        while (iteratorClientes.hasNext()) {
            Cliente clienteAuxiliar = iteratorClientes.next();
            if (clienteAuxiliar.getCorreo().equals(correo)) {
                return true;
            }
        }
        Iterator<Proveedor> iteratorProveedores = proveedores.iterator();
        while (iteratorProveedores.hasNext()) {
            Proveedor proveedorAuxiliar = iteratorProveedores.next();
            if (proveedorAuxiliar.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Proveedor> obtenerProveedores() {
        return proveedores;
    }

    @Override
    public List<Cliente> obtenerCientes() {
        return clientes;
    }

    @Override
    public void vaciarPersistenciaP() {
        proveedores.clear();
    }

    @Override
    public void vaciarPersistenciaC() {
        clientes.clear();
    }
}
