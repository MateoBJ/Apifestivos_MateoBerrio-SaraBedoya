package apidiafestivo.apidiafestivo.core.interfaces.servicios;

import apidiafestivo.apidiafestivo.dominio.Festivo;

import java.util.Date;
import java.util.List;

public interface IFestivoServicio {
    List<Festivo> listar();
    Festivo obtener(Integer id);
    Festivo agregar(Festivo festivo);
    Festivo modificar(Festivo festivo);
    boolean eliminar(Integer id);
    
    boolean esDiaFestivo(Date fecha);
    Date getDomingoRamos(Integer año);
    Date incrementarDias(Date fecha, Integer dias);
    Date siguienteLunes(Date fecha);
}

