package uy.edu.cure.servidor.central.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Categoria;

/**
 *
 * @author guido
 */
public class CategoriaServiceImpl implements CategoriaService {

    private static List<Categoria> categorias = new ArrayList<Categoria>();

    public CategoriaServiceImpl() {
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    @Override
    public boolean existeCategoria(String nombre) {
        Iterator<Categoria> iteratorCategorias = categorias.iterator();
        while (iteratorCategorias.hasNext()) {
            Categoria categoriaAuxiliar = iteratorCategorias.next();
            if (categoriaAuxiliar.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Categoria obtenerCategoria(String nombre) {
        Iterator<Categoria> iteratorCategorias = categorias.iterator();
        while (iteratorCategorias.hasNext()) {
            Categoria categoriaAuxiliar = iteratorCategorias.next();
            if (categoriaAuxiliar.getNombre().equals(nombre)) {
                return categoriaAuxiliar;
            }
        }
        return null;
    }

    @Override
    public List<Categoria> obtenerTodosCategorias() {
        return categorias;
    }

    @Override
    public void vaciarPersistenciaCategoria() {
        categorias.clear();
    }

}
