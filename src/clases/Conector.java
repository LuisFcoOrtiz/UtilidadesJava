/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author marketing
 */
public class Conector {
    
    //Clase para conectar con Sqlite
    //url de el archivo de Sqlite
    String url = "C:\\pruebas\\test.db";
    Connection connect;
        
    public void connection() throws SQLException {  //Abrir conexion con la base de datos
    
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:"+url);            
            if (connect!=null){
                System.out.println("La conexión fue exitosa");
            }
            
        } catch(SQLException ex) {
            System.out.print(ex);
        } 
        
    } //Fin de metodo connection
    
      
    public void select() {  //Select hacia la base de datos
        
       ResultSet result = null;
       try {
           PreparedStatement query;
           query = connect.prepareStatement("select * from trabajadores");
           result = query.executeQuery();
           while (result.next()) {
               System.out.println("DATOS");
               System.out.println(result.getInt("id"));
               System.out.println(result.getString("nombre"));
               System.out.println(result.getString("apellidos"));
               System.out.println("*********");
           }
       } catch(SQLException ex) {
           System.out.println(ex);       
       }
       
    } //fin del metodo select
    
    public void close() {   //Cerrar la conexion con la base de datos
    
        try {
            connect.close();
        }catch (SQLException ex) {
            System.out.print(ex);
        }
        
    } //fin del metodo close
    
} //fin de la clase conector
