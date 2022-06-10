/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    public Nomina getNominaById(Long idnom) throws FileNotFoundException, DocumentException {
        Optional<Nomina> nomina = repositorioNomina.findByIdnomina(idnom);
        if (nomina.isPresent()) {
            generarPDF(nomina.get());
            return nomina.get();
        } else {
            return null;
        }
    }

    //segundo requisito
    public com.itextpdf.text.Document generarPDF(Nomina nomina) throws FileNotFoundException, DocumentException {

        //crear el objeto trabajador y nómina al que pertenece ese id 
        Trabajador trabajador = getTrabajadorById(nomina.getIdtrab());
        Empresa empresa = getEmpresaById(trabajador.getIdemp());

        //creamos el pdf con el nombre y fecha de la nómina del trabajador
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream("C:\\Users\\Alvaro\\Desktop\\nominas\\" + empresa.getIdEmp() + "\\" + trabajador.getNaf() + "_" + nomina.getFechafin() + ".pdf"));

        //inicio
        document.open();
        //asignamos estilos
        Font titulos = FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK);
        Font miniTitulos = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
        Font contenidos = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
        Paragraph parrafo = new Paragraph();
        Chunk parrafo1 = new Chunk(" ", titulos);

        //Añadimos los campos requeridos en cualquier nómina y los dotamos de formato
        //Empresa
        Chunk empresas = new Chunk("EMPRESA", titulos);
        Chunk nomempr = new Chunk("-Nombre empresa: " + empresa.getNomEmp(), contenidos);
        Chunk domicilioemp = new Chunk("-Domicilio de la empresa: " + empresa.getDomicilio(), contenidos);
        Chunk cif = new Chunk("-CIF: " + empresa.getCif(), contenidos);
        Chunk ccc = new Chunk("-CCC: " + empresa.getCcc(), contenidos);

        //Trabajador
        Chunk trabajadores = new Chunk("TRABAJADOR", titulos);
        Chunk nomtrab = new Chunk("-Nombre del trabajador: " + trabajador.getNomtrab() + " " + trabajador.getApe1emp() + " " + trabajador.getApe2emp(), contenidos);
        Chunk niftrab = new Chunk("-NIF: " + trabajador.getDni(), contenidos);
        Chunk nastrab = new Chunk("-Nº afiliacón S.S: " + trabajador.getNaf(), contenidos);
        Chunk gruppro = new Chunk("-Grupo profesional: " + trabajador.getGrupoprofesional(), contenidos);
        Chunk grupcot = new Chunk("-Grupo cotización: " + trabajador.getGrupocotizacion(), contenidos);
        Chunk nivelcot = new Chunk("-Nivel cotización: " + trabajador.getNivelcotizacion(), contenidos);
        Chunk letra = new Chunk("-Letra: " + trabajador.getLetra(), contenidos);
        Chunk feac = new Chunk("-Fecha de antigüedad: " + trabajador.getFechaantiguedad(), contenidos);

        //Tierra de nadie
        Chunk perliq = new Chunk("-Periodo de liquidación: " + nomina.getFechainicio() + " - " + nomina.getFechafin(), contenidos);
        Chunk totdias = new Chunk("           Total de días: " + nomina.getDiastrabajados(), contenidos);

        //Devengos 
        Chunk devengos = new Chunk("DEVENGOS", titulos);
        //Percepciones salariales
        Chunk persal = new Chunk("·Percepciones salariales: ", miniTitulos);
        Chunk salba = new Chunk("     -Salario Base: " + nomina.getSalariobase(), contenidos);
        Chunk compsa = new Chunk("     -Complementos salariales: " + nomina.getComplementos(), contenidos);
        Chunk compsa1 = new Chunk("          *Plus transporte: " + nomina.getPlustransporte(), contenidos);
        Chunk compsa2 = new Chunk("          *Capacitación profesional: " + nomina.getCapacitacionprofesional(), contenidos);
        Chunk pagex = new Chunk("     -Pagas extraordinarias prorroteadas: " + nomina.getPagasextra(), contenidos);
        //Percepciones no salariales
        Chunk pernosal = new Chunk("·Percepciones no salariales: ", miniTitulos);
        Chunk dieta = new Chunk("     -Dieta: " + nomina.getDieta(), contenidos);
        //Total devengado
        Chunk totdev = new Chunk("·Total devengado: " + nomina.getTotaldevengado(), miniTitulos);

        //Tierra de nadie
        Chunk firmaemp = new Chunk("Firma y sello de la empresa: ", contenidos);
        Chunk fecact = new Chunk("               Fecha: " + LocalDate.now(), contenidos);
        Chunk firmtrab = new Chunk("               Recibí: ", contenidos);

        //Deducciones
        Chunk deduc = new Chunk("DEDUCCIONES", titulos);

        //Deducciones trabajador
        Chunk segsoc = new Chunk(" ·Deducciones del trabajador a la seguridad social: ", miniTitulos);
        Chunk contcom = new Chunk("     -Contingencias comunes: 4.7%        " + nomina.getCctrab(), contenidos);
        double porcentaje = 0;
        if ("temporal".equals(trabajador.getTipocontrato())) {
            porcentaje = 1.60;
        } else {
            porcentaje = 1.55;
        }
        Chunk desempleo = new Chunk("     -Desempleo:             " + porcentaje + "%        " + nomina.getDestrab(), contenidos);
        Chunk fp = new Chunk("     -Formación profesional: 0.10%        " + nomina.getFptrab(), contenidos);
        Chunk horasex = new Chunk("     -Horas extraordinarias ordinarias: " + nomina.getHetrab(), contenidos);
        Chunk horasexfu = new Chunk("     -Horas extraordinarias por fuerza mayor: " + nomina.getHetrabfm(), contenidos);
        Chunk hacienda = new Chunk(" ·Deducciones del trabajador a hacienda: ", miniTitulos);
        Chunk irpf = new Chunk("     -IRPF: " + nomina.getIrpf(), contenidos);
        //Total a deducir
        Chunk totded = new Chunk(" ·Total a deducir: " + nomina.getTotaldeducir(), miniTitulos);
        //Total liquido
        Chunk totliq = new Chunk(" ·Liquido total a percibir: " + nomina.getTotalliquido(), miniTitulos);

        //Deducciones empresa
        Chunk dedemp = new Chunk(" ·Deducciones de la empresa: ", miniTitulos);
        Chunk contcomemp = new Chunk("  -Contingencias comunes: 23.6%        " + nomina.getCcEmp(), contenidos);
        Chunk atyet = new Chunk("  -AT y ET:                1.5%         " + nomina.getAtep(), contenidos);
        double porcentajeEmpresa = 0;
        if ("temporal".equals(trabajador.getTipocontrato())) {
            porcentajeEmpresa = 6.7;
        } else {
            porcentajeEmpresa = 5.5;
        }
        Chunk desempleoemp = new Chunk("  -Desempleo:              " + porcentajeEmpresa + "%         " + nomina.getDesemp(), contenidos);
        Chunk fpemp = new Chunk("  -Formación profesional:  0.6%          " + nomina.getFpemp(), contenidos);
        Chunk horasexemp = new Chunk("  -Horas extraordinarias ordinarias: " + nomina.getHeemp(), contenidos);
        Chunk horasexfuemp = new Chunk("  -Horas extraordinarias por fuerza mayor: " + nomina.getHeempfm(), contenidos);
        Chunk fogasa = new Chunk("  -FOGASA:                 0.2%          " + nomina.getFogasa(), contenidos);
        //Total aportación empresa
        Chunk totdedemp = new Chunk(" ·Total aportación de la empresa: " + nomina.getTotalaporemp(), miniTitulos);

        //Adición al pdf
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
        document.add(nivelcot);
        document.add(parrafo);
        document.add(letra);
        document.add(parrafo);
        document.add(feac);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        //Tierra de nadie
        document.add(perliq);
        document.add(totdias);
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
        document.add(compsa2);
        document.add(parrafo);
        document.add(parrafo);
        document.add(pagex);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        //Percepciones no salariales
        document.add(pernosal);
        document.add(parrafo);
        document.add(dieta);
        document.add(parrafo);
        document.add(parrafo1);
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
        document.add(parrafo1);
        document.add(parrafo);
        //Total a deducir
        document.add(totded);
        document.add(parrafo);
        //Total liquido
        document.add(totliq);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
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
        document.add(fogasa);
        document.add(parrafo);
        document.add(horasexemp);
        document.add(parrafo);
        document.add(horasexfuemp);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        //Total empresa
        document.add(totdedemp);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        //Tierra de nadie
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        document.add(parrafo);
        document.add(parrafo1);
        document.add(parrafo);
        document.add(firmaemp);
        document.add(fecact);
        document.add(firmtrab);

        //final
        document.close();
        return document;
    }

    //tercer requisito
    public void generarZIP() {
        //asignamosla direccion de las creacion y nombre del zip.
        String zipFile = "C:\\Users\\Alvaro\\Desktop\\zipnomina\\Todas_Nominas.zip";

        //hacemos un array al que le asignamos el contenido de file
        File f1 = new File("C:\\Users\\Alvaro\\Desktop\\nominas\\");

        String srcFiles[] = f1.list();

        try {
            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            //recorremos las carpetas.
            for (int e = 0; e < srcFiles.length; e++) {

                File f2 = new File(f1.getPath() + "/" + srcFiles[e]);
                String[] direccion = f2.list();

                // creamps un buffer de tipo byte.
                byte[] buffer = new byte[1024];

                //recorremos los archvios para poder almacenarlos en el zip.
                for (int i = 0; i < direccion.length; i++) {

                    File srcFile = new File(direccion[i]);

                    FileInputStream fis = new FileInputStream(f2.getPath() + "/" + srcFile);

                    zos.putNextEntry(new ZipEntry(srcFile.getName()));
                    //entrada del nuevo zip y comiezo de insercion de datos.

                    int length;

                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();

                    fis.close();
                }
            }
            //cuando cerramos el for se cierra el zip y asi evitamos que al cerrarlo antes y no abrirlo se eliminen en cada vuelta del for.
            zos.close();

        } catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }

    }

    public void generarZIPTrabajador(Long id) {
        Trabajador trabajador = getTrabajadorById(id);

        //asignamosla direccion de las creacion y nombre del zip.
        String cortar[] = new String[2];
        String zipFile = "C:\\Users\\Alvaro\\Desktop\\zipnomina\\" + trabajador.getNomtrab() + "_" + id + " .zip";

        //hacemos un array al que le asignamos el contenido de file
        File f1 = new File("C:\\Users\\Alvaro\\Desktop\\nominas");
        String srcFiles[] = f1.list();

        try {
            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int e = 0; e < srcFiles.length; e++) {

                File f2 = new File(f1.getPath() + "/" + srcFiles[e]);
                String[] direccion = f2.list();

                // creamps un buffer de tipo byte.
                byte[] buffer = new byte[1024];

                //recorremos los archvios para poder almacenarlos en el zip.
                for (int i = 0; i < direccion.length; i++) {
                    //cortamos del nombre del archivo la parte que nos interesa y la comprobamos para decidir que mandamos al zip y que no.
                    cortar = direccion[i].split("_");
                    if (cortar[0].equals(trabajador.getNaf())) {

                        File srcFile = new File(direccion[i]);

                        FileInputStream fis = new FileInputStream(f2.getPath() + "/" + srcFile);

                        zos.putNextEntry(new ZipEntry(srcFile.getName()));
                        //entrada del nuevo zip y comiezo de insercion de datos.

                        int length;

                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }

                        zos.closeEntry();

                        fis.close();
                    }
                }
            }

            //cuando cerramos el for se cierra el zip y asi evitamos que al cerrarlo antes y no abrirlo se eliminen en cada vuelta del for.
            zos.close();

        } catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }

    }

    public void generarZIPEmpresa(Long idemp) {
        Empresa empresa = getEmpresaById(idemp);

        //asignamosla direccion de las creacion y nombre del zip.
        String zipFile = "C:\\Users\\Alvaro\\Desktop\\zipnomina\\" + empresa.getNomEmp() + ".zip";

        //hacemos un array al que le asignamos el contenido de file
        File f1 = new File("C:\\Users\\Alvaro\\Desktop\\nominas\\");

        String srcFiles[] = f1.list();

        try {
            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            //comparamos el id que le damos con las carpetas para selecionar la que queremos.
            for (int e = 0; e < srcFiles.length; e++) {
                if (Long.toString(idemp).equals(srcFiles[e])) {

                    File f2 = new File(f1.getPath() + "/" + srcFiles[e]);
                    String[] direccion = f2.list();

                    // creamps un buffer de tipo byte.
                    byte[] buffer = new byte[1024];

                    //recorremos los archvios para poder almacenarlos en el zip.
                    for (int i = 0; i < direccion.length; i++) {

                        File srcFile = new File(direccion[i]);

                        FileInputStream fis = new FileInputStream(f2.getPath() + "/" + srcFile);

                        zos.putNextEntry(new ZipEntry(srcFile.getName()));
                         //entrada del nuevo zip y comiezo de insercion de datos.


                        int length;

                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }

                        zos.closeEntry();

                      
                        fis.close();
                    }
                }
            }
         //cuando cerramos el for se cierra el zip y asi evitamos que al cerrarlo antes y no abrirlo se eliminen en cada vuelta del for.
            zos.close();

        } catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }
    }
//Requisito extra

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
    public void generarNomina(Trabajador t, String archivo) throws ParseException {
        Nomina nomina = new Nomina();

        /**
         * ******************DATOS
         * EMPRESA***************************************************
         */
        Empresa empresa = getEmpresaById(t.getIdemp());
        nomina.setDomicilio(empresa.getDomicilio());

        /**
         * ******************DATOS
         * TRABAJADOR*************************************************
         */
        nomina.setIdtrab(t.getIdtrab());
        nomina.setCategoria(t.getCategoria());
        nomina.setGrupocotizacion(t.getGrupocotizacion());
        nomina.setGrupoprofesional(t.getGrupoprofesional());
        nomina.setNivelcotizacion(t.getNivelcotizacion());
        nomina.setConvenio(t.getConvenio());

        /**
         * *******************DEVENGOS**************************************************
         */
        /**
         * ********Percepciones salariales********************
         */
        Date inicio;
        String fechaInicio;
        SimpleDateFormat antiguedad = new SimpleDateFormat("yyyy-MM-dd");
        int numeroMes;
        int diasMes;
        int numeroDiasInicio;
        int numeroDiasFin;
        do {
            //conseguir un mes aleatorio 
            numeroMes = (int) (Math.random() * (12) + 1);
            diasMes = buscarDiasMes(numeroMes);
            //conseguir un día aleatorio, entre 1 y los días máximos del mes 
            numeroDiasInicio = (int) (Math.random() * (diasMes) + 1);
            numeroDiasFin = (int) (Math.random() * (diasMes) + 1);
            //requisito: la fecha de inicio anterior a la de fin
            if (numeroDiasFin < numeroDiasInicio) {
                int control;
                control = numeroDiasFin;
                numeroDiasFin = numeroDiasInicio;
                numeroDiasInicio = control;
            }
            fechaInicio = "2022-" + numeroMes + "-" + numeroDiasInicio;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            inicio = dateFormat.parse(fechaInicio);
            //repetir código hasta que la fecha de inicio sea posterior a la fecha de antigüedad
        } while (inicio.before(antiguedad.parse(t.getFechaantiguedad())));

        nomina.setFechainicio(fechaInicio);
        String fechaFin = "2022-" + numeroMes + "-" + numeroDiasFin;
        nomina.setFechafin(fechaFin);
        int diasTrabajados = (numeroDiasFin - numeroDiasInicio) + 1;
        nomina.setDiastrabajados(diasTrabajados);

        //salario anual extraído del XML
        double salarioAnual = conseguirSalario(t.getGrupocotizacion(), t.getNivelcotizacion(), t.getLetra(), archivo);
        //salario base, dividido entre 15 (12 meses del año + 3 pagas) 
        double salarioBase = (salarioAnual / 15) * diasTrabajados / diasMes;
        nomina.setSalariobase(salarioBase);

        //plus transporte: sacar un número aleatorio, entre 1 y 10, con la misma probabilidad
        double plusTransporte;
        int aleatorio = (int) (Math.random() * (10) + 1);
        if (aleatorio <= 5) {
            //extraemos valor del XML para calcular el total de plus transporte
            plusTransporte = getElemento(archivo, "plus_transporte") * diasTrabajados;
        } else {
            plusTransporte = 0;
        }
        nomina.setPlustransporte(plusTransporte);

        //capacitación Profesional
        double capacitacionProfesional;
        int aleatorio2 = (int) (Math.random() * (10) + 1);
        if (aleatorio2 <= 5) {
            capacitacionProfesional = 20 * salarioBase / 100;
        } else {
            capacitacionProfesional = 0;
        }
        nomina.setCapacitacionprofesional(capacitacionProfesional);

        //complementos, suma de todos los pluses del convenio
        double complementos = plusTransporte + capacitacionProfesional;
        nomina.setComplementos(complementos);

        //pagas extra, todas prorrateadas. En este convenio son 3
        double pagasExtraPro = salarioBase * 3 / 12;
        nomina.setPagasextra(pagasExtraPro);

        //horas extra aleatorias, un valor pequeño como máximo para tener coherencia si salen pocos días trabajados
        double horasExtra = (int) (Math.random() * (10));
        nomina.setHorasextra(horasExtra);

        //horas extra fuerza mayor
        double horasExtraFM; //es más probable que no tengas que que tengas 
        int aleatorio3 = (int) (Math.random() * (10) + 1);
        if (aleatorio3 <= 3) {
            horasExtraFM = (int) (Math.random() * (5));
        } else {
            horasExtraFM = 0;
        }
        nomina.setHorasextrafm(horasExtraFM);

        /**
         * *****Percepciones no salariales*************
         */
        int diasConDieta;
        double dieta;
        int aleatorio4;
        //generar un aleatorio4 para que haya la misma probabilidad de tener o no dieta, 
        //en el caso de que haya, otro aleatorio establece los días con dieta, que no serán superiores a los trabajados
        do {
            aleatorio4 = (int) (Math.random() * (10) + 1);
            diasConDieta = (int) (Math.random() * (diasTrabajados) + 1);
            if (aleatorio4 <= 5) {
                dieta = getElemento(archivo, "dieta_media") * diasConDieta;
            } else {
                dieta = 0;
            }
        } while (dieta > 26.67); //no cotiza si es <= a 26.67
        nomina.setDieta(dieta);

        /**
         * ******total devengos********
         */
        double totalDevengado = salarioBase + complementos + pagasExtraPro + horasExtra + horasExtraFM + dieta;
        nomina.setTotaldevengado(totalDevengado);

        /**
         * **************DEDUCCIONES*******************************************************
         */
        /**
         * ***** 3 bases ********
         */
        double bccc = salarioBase + complementos + pagasExtraPro;
        double bhe = horasExtra + horasExtraFM;
        double bccp = bccc + bhe;

        /**
         * **** tipo de contrato para hallar el desempleo **********
         */
        //tipo de contrato extraido de la base de datos
        String tipoContrato = t.getTipocontrato();
        //IRPF, se genera un aleatorio,en nuestro convenio solo tiene sentido entre 2 y 20%
        int porcentajeIRPF = (int) (Math.random() * (20) + 2);
        double desTrab = 0;
        double desEmp = 0;
        //en función del tipo de contrato, el desempleo del trabajador y de la empresa cambian
        if ("temporal".equals(tipoContrato)) {
            //trabajador
            desTrab = bccp * 1.6 / 100;
            nomina.setDestrab(desTrab);
            //empresa
            desEmp = bccp * 6.7 / 100;
            nomina.setDesemp(desEmp);
        } else {
            //trabajador
            desTrab = bccp * 1.55 / 100;
            nomina.setDestrab(desTrab);
            //empresa
            desEmp = bccp * 5.5 / 100;
            nomina.setDesemp(desEmp);
        }

        /**
         * *****deducciones trabajador*********
         */
        //contingencias comunes
        double ccTrab = bccc * 4.7 / 100;
        nomina.setCctrab(ccTrab);
        //formación profesional
        double fpTrab = bccp * 0.1 / 100;
        nomina.setFptrab(fpTrab);
        //horas extra
        double horasExtraTrab = bhe * 4.7 / 100;
        nomina.setHetrab(horasExtraTrab);
        //horas extra fuerza mayor
        double heFMtrab = bhe * 2 / 100;
        nomina.setHetrabfm(heFMtrab);
        //irpf
        double irpf = bccp * porcentajeIRPF / 100;
        nomina.setIrpf(irpf);

        /**
         * ********total a deducir trabajador*********
         */
        //total SS
        double totalSeguridadSocialTrab = (ccTrab + fpTrab + horasExtraTrab + heFMtrab + desTrab);
        //total hacienda
        double totalHacienda = irpf;
        //total a deducir = SS + Hacienda
        double totalDeduccion = totalSeguridadSocialTrab + totalHacienda;
        nomina.setTotaldeducir(totalDeduccion);
        nomina.setTotalliquido(totalDevengado - totalDeduccion);

        /**
         * ********deducciones empresa****************
         */
        //contingencias comunes
        double ccEmp = bccc * 23.6 / 100;
        nomina.setCcEmp(ccEmp);
        //horas extra
        double horasExtraEmp = bhe * 23.6 / 200;
        nomina.setHeemp(horasExtraEmp);
        //horas extra fuerza mayor
        double heFMEmp = bhe * 12 / 100;
        nomina.setHeempfm(heFMEmp);
        //fogasa
        double fogasa = bccp * 0.2 / 100;
        nomina.setFogasa(fogasa);
        //at-ep
        double atep = bccp * 1.5 / 100; //CNAE propio de la empresa
        nomina.setAtep(atep);
        //formación profesional
        double formacionEmp = bccp * 0.6 / 100;
        nomina.setFpemp(formacionEmp);

        /**
         * ********total a deducir empresa***********
         */
        double totalSeguridadSocialEmpresa = (ccEmp + horasExtraEmp + heFMEmp + fogasa + atep + formacionEmp + desEmp);
        nomina.setTotalaporemp(totalSeguridadSocialEmpresa);
        repositorioNomina.save(nomina);
    }

    //primer requisito
    public double conseguirSalario(int grupo, int nivel, String letra, String devuelveArchivo) {
        double salarioTrab = 0;
        //crear nueva instancia del documento
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;

        try {
            builder = factory.newDocumentBuilder();
            doc = (Document) builder.parse(new InputSource(new StringReader(devuelveArchivo)));
            //verificar el documento y que no haya problemas
            doc.getDocumentElement().normalize();
            //que busque todos los elementos con la etiqueta grupo_profesional
            NodeList grupoProf = doc.getElementsByTagName("grupo_profesional");
            //recorrer dentro de grupo_profesional
            for (int temp = 0; temp < grupoProf.getLength(); temp++) {
                //verifica que el nodo exista, opcional
                Node node = grupoProf.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //el nodo se transforma a un elemento
                    Element element = (Element) node;
                    //seleccionamos atributo grupo dentro de grupo profesional
                    String profesional = element.getAttribute("grupo");
                    //conversión a entero y comparar con la base de datos
                    if (Integer.parseInt(profesional) == grupo) {
                        //coger los salarios
                        NodeList salarios = element.getElementsByTagName("salarios");
                        //recorrer los salarios
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
                                        //comparar los atributos con la base de datos y en caso de ser correctos se añaden a una variable para hacer el return
                                        if ((Integer.parseInt(salarioFinal) == nivel) && (letraSalario.equals(letra))) {
                                            String salarioGeneral = salarioComp.item(temp3).getTextContent();

                                            salarioTrab = Double.parseDouble(salarioGeneral);
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
        return salarioTrab;
    }

    //primer requisito
    //según el mes que le pases te devuelve los dias máximos 
    public int buscarDiasMes(int mes) {
        int dias = 0;
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dias = 31;
                break;
            case 2:
                dias = 28;
                break;
            default:
                dias = 30;
                break;
        }
        return dias;
    }

    //primer requisito
    //extrae el valor de los pluses del XML
    public double getElemento(String xml, String element) {
        String elemento = "";
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
        double elementoCantidad = Double.parseDouble(elemento);
        return elementoCantidad;
    }

}
