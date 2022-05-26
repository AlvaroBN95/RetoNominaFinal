/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.severoochoa.SpringBootReto;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author usuario
 */
public class Pdf {

    public static void main(String[] args) throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("D:\\Nomina.pdf"));

        document.open();
        Font titulos = FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK);
        Font miniTitulos = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
        Font contenidos = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
        Paragraph parrafo = new Paragraph();
        //Empresa
        Chunk empresa = new Chunk("EMPRESA", titulos);
        Chunk nomempr = new Chunk("-Nombre empresa: ", contenidos);
        Chunk domicilioemp = new Chunk("-Domicilio de la empresa: ", contenidos);
        Chunk cif = new Chunk("-CIF: ", contenidos);
        Chunk ccc = new Chunk("-CCC: ", contenidos);
        Chunk cnae = new Chunk("-CNAE: ", contenidos);
        //Trabajador
        Chunk trabajador = new Chunk("TRABAJADOR", titulos);
        Chunk nomtrab = new Chunk("-Nombre del trabajador: ", contenidos);
        Chunk niftrab = new Chunk("-NIF: ", contenidos);
        Chunk nastrab = new Chunk("-Nº afiliacón S.S: ", contenidos);
        Chunk gruppro = new Chunk("-Grupo profesional: ", contenidos);
        Chunk grupcot = new Chunk("-Grupo cotización: ", contenidos);
        Chunk feac = new Chunk("-Fecha de antigüedad: ", contenidos);
        //Devengos 
        Chunk devengos = new Chunk("DEVENGOS", titulos);
        //Percepciones salariales
        Chunk persal = new Chunk("·Percepciones salariales: ", miniTitulos);
        Chunk salba = new Chunk(" -Salario Base: ", contenidos);
        Chunk compsa = new Chunk(" -Complementos salariales: ", contenidos);
        Chunk compsa1 = new Chunk("  -Plus transporte: ", contenidos);
        Chunk compsa2 = new Chunk("  -Complementos salariales: ", contenidos);
        Chunk pagex = new Chunk(" -Pagas extraordinarias: ", contenidos);
        //Devengos Percepciones no salariales
        Chunk pernosal = new Chunk("·Percepciones no salariales: ", miniTitulos);
        Chunk dieta = new Chunk(" -Dieta: ", contenidos);
        //Total devengado
        Chunk totdev = new Chunk("·Total devengado: ", miniTitulos);
        //Deducciones
        Chunk deduc = new Chunk("Deducciones:", titulos);
        //Deducciones trabajador
        Chunk segsoc = new Chunk(" ·Deducciones del trabajador a la seguridad social: ", miniTitulos);
        Chunk contcom = new Chunk("  -Contingencias comunes: ", contenidos);
        Chunk desempleo = new Chunk("  -Desempleo: ", contenidos);
        Chunk fp = new Chunk("  -Formación profesional: ", contenidos);
        Chunk horasex = new Chunk("  -Horas extraordinarias ordinarias: ", contenidos);
        Chunk horasexfu = new Chunk("  -Horas extraordinarias por fuerza mayor: ", contenidos);
        Chunk hacienda = new Chunk(" ·Deducciones del trabajador a hacienda: ", miniTitulos);
        Chunk irpf = new Chunk("  -IRPF: ", contenidos);
        //Total a deducir
        Chunk totded = new Chunk(" ·Total a deducir: ", miniTitulos);
        //Total liquido
        Chunk totliq = new Chunk(" ·Liquido total a percibir: ", miniTitulos);
        //Deducciones empresa
        Chunk dedemp = new Chunk(" ·Deducciones de la empresa: ", miniTitulos);
        Chunk contcomemp = new Chunk("  -Contingencias comunes: ", contenidos);
        Chunk atyet = new Chunk("  -AT y ET: ", contenidos);
        Chunk desempleoemp = new Chunk("  -Desempleo: ", contenidos);
        Chunk fpemp = new Chunk("  -Formación profesional: ", contenidos);
        Chunk horasexemp = new Chunk("  -Horas extraordinarias ordinarias: ", contenidos);
        Chunk horasexfuemp = new Chunk("  -Horas extraordinarias por fuerza mayor: ", contenidos);
        Chunk fogasa = new Chunk("  -Fogasa: ", contenidos);
        Chunk totdedemp = new Chunk(" ·Total a deducir: ", miniTitulos);
        //Empresa
        document.add(empresa);
        document.add(parrafo);
        document.add(nomempr);
        document.add(parrafo);
        document.add(domicilioemp);
        document.add(parrafo);
        document.add(cif);
        document.add(parrafo);
        document.add(ccc);
        document.add(parrafo);
        document.add(cnae);
        document.add(parrafo);
        //Trabajador
        document.add(trabajador);
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
        document.add(pagex);
        document.add(parrafo);
        //Devengos Percepciones no salariales
        document.add(pernosal);
        document.add(parrafo);
        document.add(dieta);
        document.add(parrafo);
        //Total devengado
        document.add(totdev);
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
    }
}

