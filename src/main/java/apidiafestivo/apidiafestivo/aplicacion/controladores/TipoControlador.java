package apidiafestivo.apidiafestivo.aplicacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import apidiafestivo.apidiafestivo.core.interfaces.servicios.ITipoServicio;
import apidiafestivo.apidiafestivo.dominio.Tipo;

import java.util.List;

@RestController
@RequestMapping("/tipos") // Define la ruta base para el controlador.
public class TipoControlador {

    @Autowired
    private ITipoServicio tipoServicio;

    @GetMapping
    public ResponseEntity<List<Tipo>> listarTipos() {
        List<Tipo> tipos = tipoServicio.listar();
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> obtenerTipo(@PathVariable Integer id) {
        Tipo tipo = tipoServicio.obtener(id);
        if (tipo != null) {
            return new ResponseEntity<>(tipo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Tipo> agregarTipo(@RequestBody Tipo tipo) {
        Tipo nuevoTipo = tipoServicio.agregar(tipo);
        return new ResponseEntity<>(nuevoTipo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> modificarTipo(@PathVariable Integer id, @RequestBody Tipo tipo) {
        tipo.setId(id); // Asegúrate de que la clase Tipo tenga el método setId.
        Tipo tipoModificado = tipoServicio.modificar(tipo);
        if (tipoModificado != null) {
            return new ResponseEntity<>(tipoModificado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipo(@PathVariable Integer id) {
        boolean eliminado = tipoServicio.eliminar(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

