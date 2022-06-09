
package com.severoochoa.SpringBootReto;

import com.itextpdf.text.DocumentException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

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
    public void procesaFichero(HttpServletRequest request) throws IOException, ParseException {
        List<Trabajador> listaTrabajadores = service.dimeTrabajadores();
        String devuelveArchivo = service.getFileContent(request);
        for (Trabajador t : listaTrabajadores){
            service.generarNomina(t,devuelveArchivo);
        }
    }
    
    //extra
  @GetMapping("/empresa/{idemp}")
    public void getEmpresaById(@PathVariable("idemp") Long idemp) {
        service.generarZIPEmpresa(idemp);
    }
    
    //extra
    @GetMapping("/trabajador/{idtrab}")
    public void getTrabajadorById(@PathVariable("idtrab") Long idtrab) {
        service.generarZIPTrabajador(idtrab);
        
        
        
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
  
    //tercer requisito
  @GetMapping("/nominas")
  public void getAllNominas() throws DocumentException, FileNotFoundException {
        List<Nomina> listaNominas = service.dimeNominas();
        for (Nomina nomina : listaNominas){
             service.generarPDF(nomina);
        }
        service.generarZIP();
    }

}
