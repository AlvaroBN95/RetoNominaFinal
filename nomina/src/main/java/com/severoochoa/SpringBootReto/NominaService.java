/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;

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

    public double conseguirSalario(int grupo, int nivel, String letra, String devuelveArchivo) {
        double salarios = 0;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;
        Trabajador T1 = new Trabajador();
        try {
            builder = factory.newDocumentBuilder();
            doc = (Document) builder.parse(new InputSource(new StringReader(devuelveArchivo)));
            doc.getDocumentElement().normalize();
            System.out.println("Root : " + doc.getDocumentElement().getNodeName());
            System.out.println("\nSalarios in the xml: ");
            NodeList nameList = doc.getElementsByTagName("salarios");
            NodeList grupoList = doc.getElementsByTagName("grupo_profesional");
            Node grupos = grupoList.item(T1.getGrupocotizacion() - 1);

            Element grupocot = (Element) grupos;

            grupocot.getElementsByTagName("salarios");
            for (int j = 0; j < nameList.getLength(); j++) {
                Node node = nameList.item(j);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    /* String anyo = element.getAttribute("anyo");
                                String salario = element.getElementsByTagName("salario").item(0).getTextContent();*/
                    String prueba = grupocot.getAttribute("grupo");

                    System.out.println(grupocot.getAttribute("grupo"));
                }
            }

        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.err.println("Error! " + e.getMessage());
        }
        System.out.println("grupo: " + grupo + "nivel: " + nivel + "letra: " + letra);
        return salarios;
    }
}
