/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;  //Utilizado para realizar un insert en la base de datos
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;          //Utilizado para realizar un select a mi base de datos
import javax.swing.table.DefaultTableModel;

public class Cliente extends Persona{
    private String nit;
    private int id;

    Conexion cn;
    public Cliente(){}
    
    public Cliente(int id, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
        this.id = id;
    }
        
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public DefaultTableModel leer(){
        
        DefaultTableModel tabla = new DefaultTableModel();        
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT id_cliente as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento FROM clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[] = {"ID","NIT","Nombres","Apellidos","Direccion","Telefono","Fecha Nac"};
            
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[7];
            
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nit");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            cn.cerrar_conexion();
            System.out.println("Error" + ex.getMessage());
        }
        return tabla;
    }
        
    @Override
    public void agregar(){
        try{
            PreparedStatement parametro;
            String query = "INSERT INTO clientes (nit, nombres, apellidos, direccion, telefono, fecha_nacimiento) VALUES (?,?,?,?,?,?);";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro ingresado", "Agregar", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());

        }
    }
    
    @Override
    public void actualizar(){
        
        try{
            PreparedStatement parametro;
            String query = "UPDATE clientes SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? " + 
            "WHERE id_cliente = ?";
            
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            parametro.setInt(7, getId());

            
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro actualizado", "Actualizar", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());

        }
    }
    
    @Override
    public void eliminar(){
        
        try{
            PreparedStatement parametro;
            String query = "DELETE FROM clientes WHERE id_cliente = ?";
            
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());

            
            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro Eliminado", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error" + ex.getMessage());

        }
    }

}
