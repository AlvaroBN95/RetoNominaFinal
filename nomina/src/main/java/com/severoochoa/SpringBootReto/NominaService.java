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


import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author inmag
 */
@Service
public class NominaService {
    public void getFileContent(HttpServletRequest request) throws IOException{
        String fileContent= "";
        try {
           InputStream input = request.getInputStream();

           byte[] data = new byte [1024];
           ByteArrayOutputStream buffer = new ByteArrayOutputStream();
           int nRead; 
           while ((nRead = input.read(data, 0, data.length)) != -1){
               buffer.write(data, 0, nRead);
           }
           buffer.flush();
           fileContent = new String(buffer.toByteArray());
           
        }catch (IOException ex){
            System.err.println("Error 1! " + ex.getMessage());
        }
       
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    Document doc;
    try {
      builder = factory.newDocumentBuilder();
      doc = (Document) builder.parse(new InputSource(new StringReader(fileContent)));
      doc.getDocumentElement().normalize();
      System.out.println("Root : " + doc.getDocumentElement().getNodeName());
        System.out.println("\nSalarios in the xml: ");
        NodeList nameList = doc.getElementsByTagName("salario");
        for (int i = 0; i < nameList.getLength(); i++) {
        Element el = (Element) nameList.item(i);
        System.out.println(el.getNodeName() + "element : " + el.getTextContent());
    }
    } catch (SAXException | IOException | ParserConfigurationException e) {
        System.err.println("Error! " + e.getMessage());
        }
    
    
        
    }
}
    

