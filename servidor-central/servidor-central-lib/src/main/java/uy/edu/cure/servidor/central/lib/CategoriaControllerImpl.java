package uy.edu.cure.servidor.central.lib;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import uy.edu.cure.servidor.central.dto.Categoria;
import uy.edu.cure.servidor.central.lib.jeringa.Jeringa;
import uy.edu.cure.servidor.central.lib.jeringa.JeringaInjector;

public class CategoriaControllerImpl implements CategoriaController {

    @Jeringa(value = "categoriaservice")
    private CategoriaService categoriaService;

    public CategoriaControllerImpl() {
        try {
            JeringaInjector.getInstance().inyectar(this);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean darAltaCategoria(String nombre, String nombrePadre) {
        Categoria categoria = new Categoria(nombre);
        Categoria categoriaPadre = categoriaService.obtenerCategoria(nombrePadre);
        if (!categoriaService.existeCategoria(nombre)) {
            if (categoriaPadre == null) {
                categoria.setPadre(null);
            } else {
                categoriaPadre.setHijos(categoria);
                categoria.setPadre(categoriaPadre);
            }
            guardarCategoria(categoria);
            return true;
        }
        return false;
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
    }

    @Override
    public boolean existeCategoria(String nombre) {
        return categoriaService.existeCategoria(nombre);
    }

    @Override
    public Categoria obtenerCategoria(String nombre) {
        return categoriaService.obtenerCategoria(nombre);
    }

    @Override
    public List<Categoria> obtenerTodosCategorias() {
        return categoriaService.obtenerTodosCategorias();
    }

    @Override
    public void vaciarPersistenciaCategoria() {
        categoriaService.vaciarPersistenciaCategoria();
    }

}
