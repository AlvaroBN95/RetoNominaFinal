/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.severoochoa.SpringBootReto;

/**
 *
 * @author usuario
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

   
    
   
    public void generarTodo() {
        String zipFile = "C:\\Users\\Alvaro\\Desktop\\zipnomina\\Todas_Nominas.zip";

      
        //hacer un for para que lea primero empresa1 luego empresa2 y ya entre para lo archivos.hecho
        File f1 = new File("C:\\Users\\Alvaro\\Desktop\\nominas\\");
        
        String srcFiles[] = f1.list();

        try {
            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int e = 0; e < srcFiles.length; e++) {

                File f2 = new File(f1.getPath() + "/" + srcFiles[e]);
                String[] direccion = f2.list();

                // create byte buffer
                byte[] buffer = new byte[1024];

                for (int i = 0; i < direccion.length; i++) {

                    File srcFile = new File(direccion[i]);

                    FileInputStream fis = new FileInputStream(f2.getPath() + "/" + srcFile);

                    zos.putNextEntry(new ZipEntry(srcFile.getName()));
                    // begin writing a new ZIP entry, positions the stream to the start of the entry data

                    int length;

                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();

                    // close the InputStream
                    fis.close();
                }
            }
            // close the ZipOutputStream
            zos.close();

        } catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }
    
    }
  //buscar un trabajador.
        
    public void generarPorNaf(String cif,String Naf) {
        String zipFile = "C:/Users/Alvaro/Desktop/prueba/.zip/";

        
        File f1 = new File("C:/Users/Alvaro/Desktop/archivos/"+ Naf );
        String srcFiles[] = f1.list();
        for (int i = 0; i < srcFiles.length; i++) {

            System.out.println(f1.getPath() + "/" + srcFiles[i] + "/");
        }

        try {

            // create byte buffer
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i = 0; i < srcFiles.length; i++) {

                File srcFile = new File(srcFiles[i]);

                FileInputStream fis = new FileInputStream(f1.getPath() + "/" + srcFile);

                // begin writing a new ZIP entry, positions the stream to the start of the entry data
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();

                // close the InputStream
                fis.close();

            }

            // close the ZipOutputStream
            zos.close();

        } catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }
    }
    
    //buscar por empresa.
    public void generarPorempresa(String cif) {
        String zipFile = "C:/Users/Alvaro/Desktop/prueba/Nomina.zip/";

        
        File f1 = new File("C:/Users/Alvaro/Desktop/archivos/"+ cif );
        String srcFiles[] = f1.list();
        for (int i = 0; i < srcFiles.length; i++) {

            System.out.println(f1.getPath() + "/" + srcFiles[i] + "/");
        }

        try {

            // create byte buffer
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i = 0; i < srcFiles.length; i++) {

                File srcFile = new File(srcFiles[i]);

                FileInputStream fis = new FileInputStream(f1.getPath() + "/" + srcFile);

                // begin writing a new ZIP entry, positions the stream to the start of the entry data
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();

                // close the InputStream
                fis.close();

            }

            // close the ZipOutputStream
            zos.close();

        } catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }
    }

    public static void main(String[] args) {

    }
}
