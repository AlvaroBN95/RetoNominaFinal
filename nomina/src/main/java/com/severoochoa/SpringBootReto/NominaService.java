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

}
