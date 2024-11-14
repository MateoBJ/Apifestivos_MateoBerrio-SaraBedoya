package apidiafestivo.apidiafestivo.aplicacion;


import apidiafestivo.apidiafestivo.core.interfaces.servicios.IFestivoServicio;
import apidiafestivo.apidiafestivo.dominio.Festivo;
import apidiafestivo.apidiafestivo.core.interfaces.repositorios.IFestivoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FestivoServicio implements IFestivoServicio {
    
    @Autowired
    private IFestivoRepositorio festivoRepositorio;

    @Override
    public List<Festivo> listar() {
        return festivoRepositorio.findAll();
    }

    @Override
    public Festivo obtener(Integer id) {
        return festivoRepositorio.findById(id).orElse(null);
    }

    @Override
    public Festivo agregar(Festivo festivo) {
        return festivoRepositorio.save(festivo);
    }

    @Override
    public Festivo modificar(Festivo festivo) {
        return festivoRepositorio.save(festivo);
    }

    @Override
    public boolean eliminar(Integer id) {
        if (festivoRepositorio.existsById(id)) {
            festivoRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean esDiaFestivo(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1; // Se suma 1 porque los meses comienzan en 0
        for (Festivo festivo : listar()) {
            if (festivo.getDia() == dia && festivo.getMes() == mes) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Date getDomingoRamos(Integer año) {
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 19;

        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        int dia = 15 + dias;
        int mes = 3;
        if (dia > 31) {
            dia -= 31;
            mes = 4;
        }
        return new Date(año - 1900, mes - 1, dia);
    }

    @Override
    public Date incrementarDias(Date fecha, Integer dias) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(fecha);
        cld.add(Calendar.DATE, dias);
        return cld.getTime();
    }

    @Override
    public Date siguienteLunes(Date fecha) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(fecha);

        int diaSemana = cld.get(Calendar.DAY_OF_WEEK);
        if (diaSemana != Calendar.MONDAY) {
            if (diaSemana > Calendar.MONDAY) {
                fecha = incrementarDias(fecha, 9 - diaSemana);
            } else {
                fecha = incrementarDias(fecha, 1);
            }
        }
        return fecha;
    }
}
