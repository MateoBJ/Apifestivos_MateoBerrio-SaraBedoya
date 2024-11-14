package apidiafestivo.apidiafestivo.aplicacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import apidiafestivo.apidiafestivo.core.interfaces.servicios.IFestivoServicio;
import apidiafestivo.apidiafestivo.dominio.Festivo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    @Autowired
    private IFestivoServicio festivoServicio;

    @GetMapping
    public ResponseEntity<List<Festivo>> listarFestivos() {
        List<Festivo> festivos = festivoServicio.listar();
        return new ResponseEntity<>(festivos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Festivo> obtenerFestivo(@PathVariable Integer id) {
        Festivo festivo = festivoServicio.obtener(id);
        if (festivo != null) {
            return new ResponseEntity<>(festivo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Festivo> agregarFestivo(@RequestBody Festivo festivo) {
        Festivo nuevoFestivo = festivoServicio.agregar(festivo);
        return new ResponseEntity<>(nuevoFestivo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Festivo> modificarFestivo(@PathVariable Integer id, @RequestBody Festivo festivo) {
        festivo.setId(id);
        Festivo festivoModificado = festivoServicio.modificar(festivo);
        if (festivoModificado != null) {
            return new ResponseEntity<>(festivoModificado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFestivo(@PathVariable Integer id) {
        boolean eliminado = festivoServicio.eliminar(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/verificar/{fecha}")
    public ResponseEntity<Boolean> verificarDiaFestivo(@PathVariable String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date fechaVerificacion = sdf.parse(fecha);
            boolean esFestivo = festivoServicio.esDiaFestivo(fechaVerificacion);
            return new ResponseEntity<>(esFestivo, HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}


