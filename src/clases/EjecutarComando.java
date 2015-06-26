/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static jdk.nashorn.internal.objects.NativeString.substring;

/**
 *
 * @author marketing
 * Ejecuta un comando en sistema linux o windows
 */
public class EjecutarComando {
    
    static String comando, argumento, comandoFinal;       //Comando y argumento a ejecutar
    public static String numSerieVolumen;   //Almacena el Nº de serie del volumen HDD

    public void setComando(String comando) {
        this.comando = comando;
    }

    public void setArgumento(String argumento) {
        this.argumento = argumento;
    }
    
    public void procesa() throws IOException {    
        
        //se une el comando mas el argumento para ser ejecutado
        comandoFinal= comando+argumento;
        Process process = Runtime.getRuntime().exec(comandoFinal);
        process = Runtime.getRuntime().exec(comandoFinal);
        //Para mostrar el resultado se introduce en Buffer y mediante un bucle muestra linea por linea
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {  
                System.out.println(line);
                //Añade solo el numero de serie del HDD a la variable
                numSerieVolumen=substring(line, 36);
               
        } 
        
    } //cierre del metodo procesa

    public void dameNumeroSerie() {
        //devuelve el Nº de serie del volumen del disco duro
        System.out.println(numSerieVolumen);        
    }
    
} //cierre de la clase EjecutarComando
