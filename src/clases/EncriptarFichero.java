/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author marketing
 */
public class EncriptarFichero {
    //Variables de la clase
    String ejecucion;
    //opciones de ejecucion -c encripta el fichero -d desencripta un fichero
    String opcionA= "-c";
    String opcionB= "-d";
    //fichero para la entrada y para la salida C:/pruebas/test.db
    File entrada = new File("C:/pruebas/test.db");      //fichero normal a encriptar
    File salida = new File("C:/pruebas/cryptTest.db");  //fichero de salida encriptado
    
    //set de la ejecucion que queremos realizar -c / -d
    public void setEjecucion(String ejecucion) {
        this.ejecucion = ejecucion;
    }     
    
    //metodo para encriptar
    public void encriptacion() {
    
            //compara entre la opcionA o la opcionB
        if ((opcionA.equals(ejecucion))||(opcionB.equals(ejecucion))){    
            //leer clave por teclado
            try{

                InputStreamReader leer_clave = new InputStreamReader(System.in);
                BufferedReader buff_clave = new BufferedReader(leer_clave);
                System.out.print("Escriba una clave: ");
                String clave = buff_clave.readLine();
                //pasar clave a la clase SecretKey
                try{
                 SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
                 DESKeySpec kspec = new DESKeySpec(clave.getBytes());
                 SecretKey ks = skf.generateSecret(kspec);
              //Inicializar el cifrado
                try{
                   Cipher cifrado = Cipher.getInstance("DES");

                    //Escojo modo cifrado o descifrado segun sea el caso
                    if (opcionA.equals(ejecucion)){
                       cifrado.init(Cipher.ENCRYPT_MODE, ks);}//MODO CIFRAR
                    if (opcionB.equals(ejecucion)){
                       cifrado.init(Cipher.DECRYPT_MODE, ks);}//MODO DESCIFRAR

                    //Leer fichero
                    InputStream archivo = new FileInputStream( entrada );
                    OutputStream fich_out = new FileOutputStream ( salida );

                    byte[] buffer = new byte[1024];
                    byte[] bloque_cifrado;
                    String textoCifrado = new String();
                    int fin_archivo = -1;
                    int leidos;//numero de bytes leidos

                    leidos = archivo.read(buffer);

                    while( leidos != fin_archivo ) {
                       bloque_cifrado = cifrado.update(buffer,0,leidos);
                       textoCifrado = textoCifrado + new String(bloque_cifrado,"ISO-8859-1"); 
                       leidos = archivo.read(buffer);          
                    }

                    archivo.close();

                    bloque_cifrado = cifrado.doFinal();
                    textoCifrado = textoCifrado + new String(bloque_cifrado,"ISO-8859-1");
                    //ISO-8859-1 es ISO-Latin-1

                    fich_out.write(textoCifrado.getBytes("ISO-8859-1"));//escribir fichero

                    }

                    //Inicializacion de cifrado
                    catch(javax.crypto.NoSuchPaddingException nspe) {
                        System.out.print(ks);
                    } //Instanciacion DES
                    catch(javax.crypto.IllegalBlockSizeException ibse) {
                        System.out.print(ks);
                    }//metodo doFinal
                    catch(javax.crypto.BadPaddingException bpe) {
                        System.out.print(ks);
                    }//metodo doFinal
                    }
                    //pasar clave a la clase SecretKey
                    catch(java.security.InvalidKeyException ike) {
                       System.out.print(ike);
                    }
                    catch(java.security.spec.InvalidKeySpecException ikse) {
                       System.out.print(ikse);
                    }
                    catch(java.security.NoSuchAlgorithmException nsae) {
                       System.out.print(nsae);
                    }

            }
                //leer del teclado la clave como String
                catch(java.io.IOException ioex) {
                    System.out.print(ioex);
                }
        }
         
    }
        
}
