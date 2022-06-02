
package com.severoochoa.SpringBootReto;

import com.itextpdf.text.DocumentException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author inmag
 */
@RestController
@RequestMapping("/nomina")
public class NominaController {

    @Autowired
    NominaService service;
    
    //primer requisito
    @PostMapping("/leerarchivo")
    public void procesaFichero(HttpServletRequest request) throws IOException {
        List<Trabajador> listaTrabajadores = service.dimeTrabajadores();
        String devuelveArchivo = service.getFileContent(request);
        for (Trabajador t : listaTrabajadores){
            service.generarNomina(t,devuelveArchivo);
        }
                    service.conseguirSalario(5, 2, "B", devuelveArchivo);

    }

    @GetMapping("/empresa/{idemp}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("idemp") Long idemp) {
        Empresa empresa = service.getEmpresaById(idemp);
        if (empresa == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(empresa);
        }
    }

    @GetMapping("/trabajador/{idtrab}")
    public ResponseEntity<Trabajador> getTrabajadorById(@PathVariable("idtrab") Long idtrab) {
        Trabajador trabajador = service.getTrabajadorById(idtrab);
        if (trabajador == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(trabajador);
        }
    }
  
   
  //segundo requisito
  @GetMapping("/nomina/{idnom}")
  public ResponseEntity<Nomina> getNominaById(@PathVariable("idnom")Long idnom) throws DocumentException, FileNotFoundException{
        Nomina nomina = service.getNominaById(idnom);
        if(nomina == null){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(nomina);
        }
    }
  /*
    //tercer requisito
  @GetMapping("/nominas")
  public void getAllNominas() {
        List<Nomina> listaNominas = service.dimeNominas();
        for (Nomina nomina : listaNominas){
            service.generarZIP(service.generarPDF(nomina));
        }
    }*/

}
