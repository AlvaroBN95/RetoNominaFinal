/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.pdf;

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
        Font titulos = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
        Font contenidos = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
        Paragraph parrafo = new Paragraph();
        Chunk empresa = new Chunk("EMPRESA", titulos);
        Chunk domicilioemp = new Chunk("Domicilio de la empresa: ", contenidos);
        Chunk cif = new Chunk("CIF: ", contenidos);
        Chunk ccc = new Chunk("CCC: ", contenidos);
        Chunk trabajador = new Chunk("TRABAJADOR", titulos);
        Chunk nomtrab = new Chunk("Nombre del trabajador: ", contenidos);
        Chunk niftrab = new Chunk("NIF: ", contenidos);
        Chunk nustrab = new Chunk("NUS: ", contenidos);
        Chunk gruppro = new Chunk("Grupo profesional: ", contenidos);
        Chunk grupcot = new Chunk("Grupo cotización: ", contenidos);
        document.add(empresa);
        document.add(parrafo);
        document.add(domicilioemp);
        document.add(parrafo);
        document.add(cif);
        document.add(parrafo);
        document.add(ccc);
        document.add(parrafo);
        document.add(trabajador);
        document.add(parrafo);
        document.add(nomtrab);
        document.add(parrafo);
        document.add(niftrab);
        document.add(parrafo);
        document.add(nustrab);
        document.add(parrafo);
        document.add(gruppro);
        document.add(parrafo);
        document.add(grupcot);

        document.close();
    }
}
