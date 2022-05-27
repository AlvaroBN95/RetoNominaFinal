/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.severoochoa.SpringBootReto;
/**
 *
 * @author usuario
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
 public static void main( String[] args )
    {
      byte[] buffer = new byte[1024];
      
      try{
        
        FileOutputStream fos = new FileOutputStream("D:\\Nomina.zip");
        ZipOutputStream zos = new ZipOutputStream(fos);
        ZipEntry ze= new ZipEntry("Nomina.pdf");
        zos.putNextEntry(ze);
        FileInputStream in = new FileInputStream("D:\\Nomina.pdf");
        
        int len;
        while ((len = in.read(buffer)) > 0) {
          zos.write(buffer, 0, len);
        }
        in.close();
        zos.closeEntry();
           
        zos.close();
          
        System.out.println("Hecho");
      }catch(IOException ex){
         ex.printStackTrace();
      }
    }
}

