/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.severoochoa.SpringBootReto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author inmag
 */
public class NominaServiceJUnitTest {
    
    public NominaServiceJUnitTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void diaFinalMes(){
        NominaService nomina = new NominaService();
        int numeroDias = nomina.buscarDiasMes(6);
        assertEquals(numeroDias,30);
    }
    
    @Test
    public void diaFinalMes2(){
        NominaService nomina = new NominaService();
        int numeroDias = nomina.buscarDiasMes(7);
        assertEquals(numeroDias,31);
    }
    
    @Test
    public void diaFinalMes3(){
        NominaService nomina = new NominaService();
        int numeroDias = nomina.buscarDiasMes(2);
        assertEquals(numeroDias,28);
    }
    
    @Test
    public void conseguirSalario(){
        NominaService nomina = new NominaService();
        String devuelveArchivo = 
                "<convenio>\n" +
                    "<nombre>Convenio colectivo provincial de oficinas y despachos de alicante</nombre>\n" +
                    "<ambito_territorial>Alicante</ambito_territorial>\n" +
                    "<grupos_profesionales>\n" +
                        "\n" +
                        "    <grupo_profesional grupo=\"1\">\n" +
                        "        <nivel_profesional nivel=\"\">\n" +
                        "            <categoria_profesional letra=\"\">\n" +
                        "                <nombre>Titulado Superior</nombre>\n" +
                        "            </categoria_profesional>\n" +
                        "        </nivel_profesional>\n" +
                        "        <salarios anyo=\"2022\">\n" +
                        "          <salario nivel=\"1\" letra=\"\"> 25851.50</salario>\n" +
                        "          <salario nivel=\"2\" letra=\"\"> 20681.35 </salario>\n" +
                        "          <salario nivel=\"3\" letra=\"\"> 18096.18 </salario>\n" +
                        "        </salarios>\n" +
                        "    </grupo_profesional>"
                + "</grupos_profesionales>"
            + "</convenio>";
        double salario = nomina.conseguirSalario(1, 1,"", devuelveArchivo);
        assertEquals(25851.50,salario);
    }
}
