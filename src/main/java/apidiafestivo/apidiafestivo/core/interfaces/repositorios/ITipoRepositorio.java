package apidiafestivo.apidiafestivo.core.interfaces.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import apidiafestivo.apidiafestivo.dominio.Tipo;

@Repository
public interface ITipoRepositorio extends JpaRepository<Tipo, Integer> {
    List<Tipo> findByNombre(String nombre); // Agrega este método para buscar por nombre.
}

