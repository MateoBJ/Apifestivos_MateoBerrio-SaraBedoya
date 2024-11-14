package apidiafestivo.apidiafestivo.core.interfaces.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import apidiafestivo.apidiafestivo.dominio.Festivo;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer> {
    List<Festivo> findByNombre(String nombre); // Agrega este método
}
