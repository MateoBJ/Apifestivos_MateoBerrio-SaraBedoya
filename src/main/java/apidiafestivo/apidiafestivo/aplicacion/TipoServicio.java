package apidiafestivo.apidiafestivo.aplicacion;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import apidiafestivo.apidiafestivo.core.interfaces.repositorios.ITipoRepositorio;
import apidiafestivo.apidiafestivo.core.interfaces.servicios.ITipoServicio;
import apidiafestivo.apidiafestivo.dominio.Tipo;

@Service // Añade esta anotación para que Spring reconozca la clase como un servicio.
public class TipoServicio implements ITipoServicio {

    private final ITipoRepositorio repositorio;

    // Permite la inyección de dependencias.
    public TipoServicio(ITipoRepositorio repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public List<Tipo> listar() {
        return repositorio.findAll(); // Retorna todos los tipos.
    }

    @Override
    public Tipo obtener(Integer id) {
        Optional<Tipo> tipo = repositorio.findById(id);
        return tipo.orElse(null); // Retorna null si no se encuentra.
    }

    @Override
    public List<Tipo> buscar(String nombre) {
        return repositorio.findByNombre(nombre); // Implementa este método en ITipoRepositorio.
    }

    @Override
    public Tipo agregar(Tipo tipo) {
        return repositorio.save(tipo); // Guarda un nuevo tipo.
    }

    @Override
    public Tipo modificar(Tipo tipo) {
        return repositorio.save(tipo); // Guarda un tipo modificado.
    }

    @Override
    public boolean eliminar(Integer id) {
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id); // Elimina el tipo por su ID.
            return true;
        }
        return false; // Retorna false si no existe.
    }
}

