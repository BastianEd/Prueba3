/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataBase.Conexion;
import Model.Cliente;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Cetecom
 */
public class ClienteControlador {
    Conexion cx;
    HelperControlador helper = new HelperControlador();
  
    
    
    public ClienteControlador() {
        cx = new Conexion();
        cx.conectar();
        
    }
    
        //Agregar
    public void agregarCliente(int rut,String nombre, int numero_contacto,String direccion,String tipo_cliente){
        //Query de SQL
        String query = "INSERT INTO cliente (rut,nombre,numero_contacto,direccion,tipo_cliente) VALUES (?,?,?,?,?)";
        
        //Captura de excepciones
        try {
            PreparedStatement st = cx.getConnection().prepareStatement(query);
            //Agregar Los atributos del query
            st.setInt(1,rut);
            st.setString(2, nombre);
            st.setInt(3,numero_contacto);
            st.setString(4, direccion);
            st.setString(5, tipo_cliente);
            //Mensaje
            helper.showInformation("Cliente agregado.");
                    
        } catch (Exception e) {
            helper.showError(e.getMessage());
        }
    }
        
        //Obtener
    public List<Cliente> obtenerCliente (int rut){
            //Lista
        List<Cliente> listaCliente = new ArrayList<>();
        
            //Query
        String query = ("SELECT * FROM cliente");
        
        //Captura de excepciones
        try {
            ResultSet rs = cx.EjecutarQuery(query);
            while(rs.next()){
                listaCliente.add(new Cliente(
                        rs.getInt("rut"),
                        rs.getString("nombre"),
                        rs.getInt("numero_contacto"),
                        rs.getString("direccion"),
                        rs.getString("tipo_cliente") 
                ));
            }
        } catch (Exception e) {
            helper.showError(e.getMessage());
        }
        return listaCliente;
    }
    
    //Buscar
    public Cliente buscarCliente(int rut){
        Cliente clienteBuscado = null;
        
        String query = "SELECT * FROM cliente WHERE rut = "+rut;
        try {
            ResultSet rs = cx.EjecutarQuery(query);
            
            if(rs.next()){
                clienteBuscado = new Cliente();
                clienteBuscado.setRut(rs.getInt("rut"));
                clienteBuscado.setNombre(rs.getString("nombre"));
                clienteBuscado.setNumero_contacto(rs.getInt("numero_contacto"));
                clienteBuscado.setDireccion(rs.getString("direccion"));
                clienteBuscado.setTipo_cliente(rs.getString("tipo_cliente"));
                
            }
            
        } catch (Exception e) {
            helper.showError(e.getMessage());
        }
        return clienteBuscado;
    }
        
    //Editar
    public boolean editarCliente(int rut,String nombre,int numero_contacto,String direccion,String tipo_cliente){
        //query editar
        String query = "UPDATE cliente SET nobre='"+nombre+
                "', numero_contacto = "+numero_contacto+", direccion = '"+direccion+ 
                "', tipo_cliente = '"+tipo_cliente +
                "', WHERE rut = "+rut + ";";
        
        try {
            Cliente clienteEncontrado = buscarCliente(rut);
            if(clienteEncontrado != null){
                Statement st = cx.getConnection().createStatement();
                
                int filasAfectadas = st.executeUpdate(query);
                helper.showInformation("Cliente actualizado.");
                return filasAfectadas > 0;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            helper.showError(e.getMessage());
        }
        return false;
    }
    
    //ELIMINAR
    public void eliminarCliente(int rut){
        //query eliminar
        String query = "DELETE FROM cliente WHERE rut ="+rut+";";
        
        try {
            Cliente clienteEncontrado = buscarCliente(rut);
            if(clienteEncontrado != null){
                Statement st = cx.getConnection().createStatement();
                st.execute(query);
                    //Mensaje
                helper.showInformation("Cliente eliminado.");
            }else{
                helper.showError("Cliente no Encontrado.");
            }
        } catch (Exception e) {
            helper.showError(e.getMessage());
        }
        
        
    }

    public Iterable<Cliente> obtenerCliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
