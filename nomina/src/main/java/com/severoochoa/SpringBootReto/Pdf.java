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
        Chunk compsa = new Chunk(" -Complementos salariales: ", miniTitulos);
        Chunk compsa1 = new Chunk("  -Complementos salariales: ", contenidos);
        Chunk compsa2 = new Chunk("  -Complementos salariales: ", contenidos);
        Chunk pagex = new Chunk(" -Pagas extraordinarias: ", contenidos);
        //Devengos Percepciones no salariales
        Chunk pernosal = new Chunk("·Percepciones no salariales: ", miniTitulos);
        Chunk dieta = new Chunk(" -Dieta: ", contenidos);
        //Total devengado
        Chunk totdev = new Chunk("·Total devengado: ", miniTitulos);
        //Empresa
        document.add(empresa);
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
        document.close();
    }
}

