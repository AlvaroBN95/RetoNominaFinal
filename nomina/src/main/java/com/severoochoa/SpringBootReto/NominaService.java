/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;

import java.io.ByteArrayOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author inmag
 */
@Service
public class NominaService {

    @Autowired
    EmpresaRepository repositorioEmpresa;

    @Autowired
    TrabajadorRepository repositorioTrabajador;
    
    @Autowired
    NominaRepository repositorioNomina;
    
    //primer requisito
    public String getFileContent(HttpServletRequest request) throws IOException {
        String fileContent = "";
        try {
            InputStream input = request.getInputStream();

            byte[] data = new byte[1024];
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            while ((nRead = input.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            fileContent = new String(buffer.toByteArray());

        } catch (IOException ex) {
            System.err.println("Error 1! " + ex.getMessage());
        }
        return fileContent;
    }

    public Empresa getEmpresaById(Long idemp) {
        Optional<Empresa> empresa = repositorioEmpresa.findByIdemp(idemp);
        if (empresa.isPresent()) {
            return empresa.get();
        } else {
            return null;
        }
    }
    
    //segundo requisito
    
     public Nomina getNominaById(Long idnom) throws FileNotFoundException, DocumentException{
        Optional<Nomina> nomina = repositorioNomina.findByIdnomina(idnom);
        if (nomina.isPresent()) {
            generarPDF(nomina.get());
            return nomina.get();
        } else {
            return null;
        }
    }
     
    //segundo requisito
    public com.itextpdf.text.Document generarPDF(Nomina nomina) throws FileNotFoundException, DocumentException{
         
        Trabajador trabajador = getTrabajadorById(nomina.getIdtrab());
        Empresa empresa = getEmpresaById(trabajador.getIdemp());
         
        //Document document = new Document();
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream("D:\\Nomina.pdf"));

        document.open();
        Font titulos = FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK);
        Font miniTitulos = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
        Font contenidos = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
        Paragraph parrafo = new Paragraph();
        Chunk parrafo1 = new Chunk(" ", titulos);
        //Empresa
        Chunk empresas = new Chunk("EMPRESA", titulos);
        Chunk nomempr = new Chunk("-Nombre empresa: " + empresa.getNomEmp(), contenidos);
        Chunk domicilioemp = new Chunk("-Domicilio de la empresa: " + empresa.getDomicilio(), contenidos);
        Chunk cif = new Chunk("-CIF: "+ empresa.getCif(), contenidos);
        Chunk ccc = new Chunk("-CCC: " + empresa.getCcc(), contenidos);
        //Chunk cnae = new Chunk("-CNAE: " + empresa., contenidos);
        //Trabajador
        Chunk trabajadores = new Chunk("TRABAJADOR", titulos);
        Chunk nomtrab = new Chunk("-Nombre del trabajador: "+trabajador.getApe1emp() + " " + trabajador.getApe2emp(), contenidos);
        Chunk niftrab = new Chunk("-NIF: "+ trabajador.getDni(), contenidos);
        Chunk nastrab = new Chunk("-Nº afiliacón S.S: "+ trabajador.getNaf(), contenidos);
        Chunk gruppro = new Chunk("-Grupo profesional: "+ trabajador.getGrupoprofesional(), contenidos);
        Chunk grupcot = new Chunk("-Grupo cotización: " + trabajador.getGrupocotizacion(), contenidos);
        Chunk feac = new Chunk("-Fecha de antigüedad: " + trabajador.getFechaantiguedad(), contenidos);
        //Devengos 
        Chunk devengos = new Chunk("DEVENGOS", titulos);
        //Percepciones salariales
        Chunk persal = new Chunk("·Percepciones salariales: ", miniTitulos);
        Chunk salba = new Chunk(" -Salario Base: "+ nomina.getSalariobase(), contenidos);
        Chunk compsa = new Chunk(" -Complementos salariales: ", contenidos);
        Chunk compsa1 = new Chunk("  -Plus transporte: "+nomina.getGratificaciones() , contenidos);
        //Chunk compsa2 = new Chunk("  -Complementos salariales: "+nomina.getCctrab() , contenidos); ??
        Chunk pagex = new Chunk(" -Pagas extraordinarias: "+nomina.getPagasextra(), contenidos);
        //Devengos Percepciones no salariales
        Chunk pernosal = new Chunk("·Percepciones no salariales: ", miniTitulos);
        Chunk dieta = new Chunk(" -Dieta: "+ nomina.getDieta(), contenidos);
        //Total devengado
        Chunk totdev = new Chunk("·Total devengado: "+nomina.getTotaldevengado(), miniTitulos);
        //Deducciones
        Chunk deduc = new Chunk("DEDUCCIONES", titulos);
        //Deducciones trabajador
        Chunk segsoc = new Chunk(" ·Deducciones del trabajador a la seguridad social: ", miniTitulos);
        Chunk contcom = new Chunk("  -Contingencias comunes: "+ nomina.getCctrab(), contenidos);
        Chunk desempleo = new Chunk("  -Desempleo: "+nomina.getDestrab(), contenidos);
        Chunk fp = new Chunk("  -Formación profesional: "+ nomina.getFptrab(), contenidos);
        Chunk horasex = new Chunk("  -Horas extraordinarias ordinarias: "+ nomina.getHetrab(), contenidos);
        Chunk horasexfu = new Chunk("  -Horas extraordinarias por fuerza mayor: "+nomina.getHetrabfm(), contenidos);
        Chunk hacienda = new Chunk(" ·Deducciones del trabajador a hacienda: ", miniTitulos);
        Chunk irpf = new Chunk("  -IRPF: "+nomina.getIrpf(), contenidos);
        //Total a deducir
        Chunk totded = new Chunk(" ·Total a deducir: "+nomina.getTotaldeducir(), miniTitulos);
        //Total liquido
        Chunk totliq = new Chunk(" ·Liquido total a percibir: "+ nomina.getTotalliquido(), miniTitulos);
        //Deducciones empresa
        Chunk dedemp = new Chunk(" ·Deducciones de la empresa: ", miniTitulos);
        Chunk contcomemp = new Chunk("  -Contingencias comunes: "+nomina.getCcEmp(), contenidos);
        Chunk atyet = new Chunk("  -AT y ET: "+ nomina.getAtep(), contenidos);
        Chunk desempleoemp = new Chunk("  -Desempleo: " + nomina.getDesemp(), contenidos);
        Chunk fpemp = new Chunk("  -Formación profesional: "+ nomina.getFpemp(), contenidos);
        Chunk horasexemp = new Chunk("  -Horas extraordinarias ordinarias: "+ nomina.getHeemp(), contenidos);
        Chunk horasexfuemp = new Chunk("  -Horas extraordinarias por fuerza mayor: "+ nomina.getHeempfm(), contenidos);
        Chunk fogasa = new Chunk("  -Fogasa: "+ nomina.getFogasa(), contenidos);
        Chunk totdedemp = new Chunk(" ·Total aportación de la empresa: "+ nomina.getTotalaporemp(), miniTitulos);
        //Empresa
        document.add(empresas);
        document.add(parrafo);
        document.add(nomempr);
        document.add(parrafo);
        document.add(domicilioemp);
        document.add(parrafo);
        document.add(cif);
        document.add(parrafo);
        document.add(ccc);
        document.add(parrafo);
        //document.add(cnae);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        //Trabajador
        document.add(trabajadores);
        document.add(parrafo);
        document.add(nomtrab);
        document.add(parrafo);
        document.add(niftrab);
        document.add(parrafo);
        document.add(nastrab);
        document.add(parrafo);
        document.add(gruppro);
        document.add(parrafo);
        document.add(grupcot);
        document.add(parrafo);
        document.add(feac);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        //Devengos
        document.add(devengos);
        document.add(parrafo);
        //Percepciones salariales
        document.add(persal);
        document.add(parrafo);
        document.add(salba);
        document.add(parrafo);
        document.add(compsa);
        document.add(parrafo);
        document.add(compsa1);
        document.add(parrafo);
        //document.add(compsa2);
        document.add(parrafo);
        document.add(pagex);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        //Devengos Percepciones no salariales
        document.add(pernosal);
        document.add(parrafo);
        document.add(dieta);
        document.add(parrafo);
        //Total devengado
        document.add(totdev);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        //Deducciones
        document.add(deduc);
        document.add(parrafo);
        //Deducciones trabajador
        document.add(segsoc);
        document.add(parrafo);
        document.add(contcom);
        document.add(parrafo);
        document.add(desempleo);
        document.add(parrafo);
        document.add(fp);
        document.add(parrafo);
        document.add(horasex);
        document.add(parrafo);
        document.add(horasexfu);
        document.add(parrafo);
        document.add(hacienda);
        document.add(parrafo);
        document.add(irpf);
        document.add(parrafo);
        //Total a deducir
        document.add(totded);
        document.add(parrafo);
        //Total liquido
        document.add(totliq);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        //Deducciones empresa
        document.add(dedemp);
        document.add(parrafo);
        document.add(contcomemp);
        document.add(parrafo);
        document.add(atyet);
        document.add(parrafo);
        document.add(desempleoemp);
        document.add(parrafo);
        document.add(fpemp);
        document.add(parrafo);
        document.add(horasexemp);
        document.add(parrafo);
        document.add(horasexfuemp);
        document.add(parrafo);
        document.add(fogasa);
        document.add(parrafo);
        document.add(horasexfuemp);
        document.add(parrafo);
        document.add(totdedemp);
        document.add(parrafo);
        //final
        document.close();
        return document;
     }
     
     //tercer requisito
     public void generarZIP (Document nominaPDF){
         
     }

    public Trabajador getTrabajadorById(Long idtrab) {
        Optional<Trabajador> trabajador = repositorioTrabajador.findByIdtrab(idtrab);
        if (trabajador.isPresent()) {
            return trabajador.get();
        } else {
            return null;
        }
    }

    public List dimeTrabajadores() {
        List<Trabajador> listaTrabajadores = repositorioTrabajador.findAll();
        return listaTrabajadores;
    }
    
    
    //tercer requisito
     public List dimeNominas() {
        List<Nomina> listaNominas = repositorioNomina.findAll();
        return listaNominas;
    }
    
    //primer requisito
    public void generarNomina(Trabajador t, String archivo){
        Nomina nomina = new Nomina();
        
        /********************DATOS EMPRESA****************************************************/
   
        Empresa empresa = getEmpresaById(t.getIdemp());
        nomina.setDomicilio(empresa.getDomicilio());
        
        /********************DATOS TRABAJADOR**************************************************/

        nomina.setIdtrab(t.getIdtrab());
        nomina.setCategoria(t.getCategoria());
        nomina.setGrupocotizacion(t.getGrupocotizacion());
        nomina.setGrupoprofesional(t.getGrupoprofesional());
        nomina.setNivelcotizacion(t.getNivelcotizacion());
        nomina.setConvenio(t.getConvenio());
        
       /*********************DEVENGOS***************************************************/

                 /**********Percepciones salariales*********************/
        int numeroMes = (int)(Math.random()*(12)+1);
        int diasMes = buscarDiasMes(numeroMes);
       
        int numeroDiasInicio = (int)(Math.random()*(diasMes)+1);
        int numeroDiasFin = (int)(Math.random()*(diasMes)+1);
        if (numeroDiasFin<numeroDiasInicio){
            int control;
            control=numeroDiasFin;
            numeroDiasFin=numeroDiasInicio;
            numeroDiasInicio=control;
        }
        String fechaInicio = "2022/"+numeroMes+"/"+numeroDiasInicio; 
        nomina.setFechainicio(fechaInicio);
        String fechaFin = "2022/"+numeroMes+"/"+numeroDiasFin;  
        nomina.setFechafin(fechaFin);
        int diasTrabajados=(numeroDiasFin-numeroDiasInicio)+1;
         
        double salarioAnual = conseguirSalario(t.getGrupocotizacion(), t.getNivelcotizacion(), t.getLetra(),archivo); 
        double salarioBase=(salarioAnual/15)*diasTrabajados/diasMes;
        nomina.setSalariobase(salarioBase);
        
        double plusTransporte = getPlusTransporte(archivo)*diasTrabajados; 
        double gratificaciones = plusTransporte;
        nomina.setGratificaciones(gratificaciones); //por ahora solo tenemos el plusTransporte si hay que añadir más esto hay que cambiarlo
        
        double pagasExtraPro = salarioBase*3/12;
        nomina.setPagasextra(pagasExtraPro);
        
        double horasExtra = (int)(Math.random()*(10));
        nomina.setHorasextra(horasExtra);
        
        double horasExtraFM = (int)(Math.random()*(5));
        nomina.setHorasextrafm(horasExtraFM);
        
            /*******Percepciones no salariales**************/
        int diasConDieta=(int)(Math.random()*(diasTrabajados)+1); 
        double dieta;
        if (diasConDieta!=0){
            dieta= getDieta(archivo)*diasConDieta;
        } else{
            dieta=0;
        }
        nomina.setDieta(dieta); //meter en base de datos
       
        
            /********total devengos*********/
        double totalDevengado= salarioBase + gratificaciones + pagasExtraPro + horasExtra + horasExtraFM + dieta;
        nomina.setTotaldevengado(totalDevengado);
        
        
        /****************DEDUCCIONES********************************************************/
               /******* 3 bases *********/
        double bccc = salarioBase + gratificaciones + pagasExtraPro;
        double bhe = horasExtra + horasExtraFM;
        double bccp = bccc + bhe;
       
               /****** tipo de contrato para hallar el desempleo ***********/
        String tipoContrato = t.getTipocontrato() ; //llamar a bbdd
        int porcentajeIRPF = (int)(Math.random()*(20)+2); //aleatorio, en nuestro convenio solo tiene sentido entre 2 y 20%
        double desTrab=0;
        double desEmp=0;
         if ("temporal".equals(tipoContrato)){
             //trabajador
             desTrab = bccp * 1.6/100;
             nomina.setDestrab(desTrab);
             //empresa
             desEmp = bccp * 6.7/100;
             nomina.setDesemp(desEmp);
         } else {
             //trabajador
             desTrab = bccp * 1.55/100;
             nomina.setDestrab(desTrab);
             //empresa
             desEmp = bccp * 5.5/100;
             nomina.setDesemp(desEmp);
         }
         
                   /*******deducciones trabajador**********/
         double ccTrab = bccc * 4.7/100;
         nomina.setCctrab(ccTrab);
         double fpTrab = bccp * 0.1/100;
         nomina.setFptrab(fpTrab);
         double horasExtraTrab = bhe * 4.7/100;
         nomina.setHetrab(horasExtraTrab);
         double heFMtrab = bhe * 2/100;
         nomina.setHetrabfm(heFMtrab);
         double irpf = bccp * porcentajeIRPF/100;
         nomina.setIrpf(irpf);
                     
              /**********total a deducir trabajador**********/
        double totalSeguridadSocialTrab=(ccTrab+fpTrab+horasExtraTrab+heFMtrab+desTrab);
        double totalHacienda = irpf;  
        double totalDeduccion = totalSeguridadSocialTrab + totalHacienda;
        nomina.setTotaldeducir(totalDeduccion);
        nomina.setTotalliquido(totalDevengado-totalDeduccion);
        
              /**********deducciones empresa*****************/
        double ccEmp = bccc * 23.6/100;
        nomina.setCcEmp(ccEmp);
        double horasExtraEmp = bhe * 23.6/200; 
        nomina.setHeemp(horasExtraEmp);
        double heFMEmp = bhe * 12/100;
        nomina.setHeempfm(heFMEmp);
        double fogasa = bccp * 0.2/100;
        nomina.setFogasa(fogasa);
        double atep = bccp *1.5/100; //CNAE
        nomina.setAtep(atep);
        double formacionEmp = bccp * 0.6/100;
        nomina.setFpemp(formacionEmp);
        
           /**********total a deducir empresa************/
        double totalSeguridadSocialEmpresa = (ccEmp+horasExtraEmp+heFMEmp+fogasa+atep+formacionEmp+desEmp);
        nomina.setTotalaporemp(totalSeguridadSocialEmpresa);
        repositorioNomina.save(nomina);
    }
    

    //primer requisito
   public double conseguirSalario(int grupo, int nivel, String letra, String devuelveArchivo) {
        double salarioTrab = 0;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;

        try {
            builder = factory.newDocumentBuilder();
            doc = (Document) builder.parse(new InputSource(new StringReader(devuelveArchivo)));
            doc.getDocumentElement().normalize();
            NodeList grupoProf = doc.getElementsByTagName("grupo_profesional");

            for (int temp = 0; temp < grupoProf.getLength(); temp++) {

                Node node = grupoProf.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String profesional = element.getAttribute("grupo");

                    if (Integer.parseInt(profesional) == grupo) {
                        NodeList salarios = element.getElementsByTagName("salarios");

                        for (int temp2 = 0; temp2 < salarios.getLength(); temp2++) {
                            Node nodonivel = salarios.item(temp2);

                            if (nodonivel.getNodeType() == Node.ELEMENT_NODE) {

                                Element elementoSalario = (Element) nodonivel;

                                NodeList salarioComp = elementoSalario.getElementsByTagName("salario");

                                for (int temp3 = 0; temp3 < salarioComp.getLength(); temp3++) {
                                    Node nodoSalario = salarioComp.item(temp3);

                                    if (nodoSalario.getNodeType() == Node.ELEMENT_NODE) {

                                        Element atribSalario = (Element) nodoSalario;

                                        String salarioFinal = atribSalario.getAttribute("nivel");
                                        String letraSalario = atribSalario.getAttribute("letra");

                                        if ((Integer.parseInt(salarioFinal) == nivel) && (letraSalario.equals(letra))) {
                                            String salarioGeneral = salarioComp.item(temp3).getTextContent();

                                            salarioTrab = Double.parseDouble(salarioGeneral);
                                            System.out.println(salarioTrab);
                                            return salarioTrab;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.err.println("Error! " + e.getMessage());

        }
        System.out.println(salarioTrab);
        return salarioTrab;
    }

    
    //primer requisito
    public int buscarDiasMes(int mes){
            int dias=0;
            switch(mes){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                        dias=31;
                        break;
                case 2: 
                        dias=28;
                        break;
                default:
                        dias=30;
                        break;
            }
            return dias;
        }
    
    //primer requisito MEJORADO: poner nombres genéricos y dejar en un solo método para transporte y dieta
    public double getElemento(String xml, String element){
        String elemento ="";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            doc = (Document) builder.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().normalize();
            NodeList valorElemento = doc.getElementsByTagName(element);
            elemento = valorElemento.item(0).getTextContent();
            } catch (SAXException | IOException | ParserConfigurationException e) {
            System.err.println("Error! " + e.getMessage());
        }
        double elementoCantidad=Double.parseDouble(elemento);
        return elementoCantidad;
    }
    
    
    //primer requisito
    public double getPlusTransporte(String xml){
        String transporte ="";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            doc = (Document) builder.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().normalize();
            NodeList valorTransporte = doc.getElementsByTagName("plus_transporte");
            transporte = valorTransporte.item(0).getTextContent();
            } catch (SAXException | IOException | ParserConfigurationException e) {
            System.err.println("Error! " + e.getMessage());
        }
        double transporteCantidad=Double.parseDouble(transporte);
        return transporteCantidad;
    }
    
    //primer requisito
    public double getDieta(String xml){
        String dieta ="";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            doc = (Document) builder.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().normalize();
            NodeList valorDieta = doc.getElementsByTagName("dieta_media");
            dieta = valorDieta.item(0).getTextContent();
            } catch (SAXException | IOException | ParserConfigurationException e) {
            System.err.println("Error! " + e.getMessage());
        }
        double dietaCantidad=Double.parseDouble(dieta);
        return dietaCantidad;
}
    }


