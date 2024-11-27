/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package automovil;

import DataBase.Conexion;
import View.MenuPrincipal;

/**
 *
 * @author Cetecom
 */
public class AutoMovil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Conexion Base de datos
        Conexion cx = new  Conexion();
        cx.conectar();//FUNCIONO
        
        //LLamar al MENU PRINCIPAL
        MenuPrincipal mp = new MenuPrincipal();
        mp.setVisible(true);
        
        
    }
    
}
