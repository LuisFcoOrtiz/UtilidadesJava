/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static clases.EjecutarComando.comandoFinal;
import java.io.IOException;

/**
 *
 * @author marketing
 */
public class CambiarIp {
    
//Para poder cambiar la IP se tiene que ejecutar la aplicacion como administrador
    String comando, nombreRed, direccionIp, netmask, gateway;
    
    //nombre de la tarjeta de red
    public void setNombreRed(String nombreRed) {
        this.nombreRed = nombreRed;
    }
    
    //direccion ip que se quiere poner
    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }
    
    //mascara de red
    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }
    
    //puerta de enlace Router / antena
    public void setGateway(String gateway) {
        this.gateway = gateway;
    }
    
    //poner ip estatica
    public void ipStatic() throws IOException {
        comando="netsh interface ip set address name="+nombreRed+" source=static "+direccionIp+" "+netmask+" "+gateway;
        Process process = Runtime.getRuntime().exec(comando);
        process = Runtime.getRuntime().exec(comando);
    }
    
    //establecer ip dinamica
    public void ipDinamic() throws IOException {
        comando="netsh interface ip set address name="+nombreRed+" source=dhcp";
        Process process = Runtime.getRuntime().exec(comando);
        process = Runtime.getRuntime().exec(comando);
    }
    
} //Cierre de la clase CambiarIp
