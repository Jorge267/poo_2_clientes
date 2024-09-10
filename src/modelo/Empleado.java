/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;   //MOstrar el error con el try catch
import java.sql.ResultSet;      //Utilizado para realizar la consulta a la base de datos
import javax.swing.table.DefaultTableModel;



public class Empleado extends Persona{
    
    private String codigo;
    private int id_puesto, id_empleado;
    Conexion cn;

    public Empleado(){}
    public Empleado(String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String codigo_empleado, int id_puesto) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo_empleado;
        this.id_puesto = id_puesto;
        
    }
    
    public Empleado(int id_empleado, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String codigo_empleado, int id_puesto) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo_empleado;
        this.id_puesto = id_puesto;
        this.id_empleado = id_empleado;
        
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo_empleado) {
        this.codigo = codigo_empleado;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) { 
        this.id_puesto = id_puesto;
    }
      
    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    
    
    @Override
    public void agregar(){

        try{
          PreparedStatement parametro;
          String query = "INSERT INTO empleados (codigo, nombres, apellidos, direccion, telefono, fecha_nacimiento, id_puesto) VALUES (?,?,?,?,?,?,?);      ";
          cn = new Conexion();
          cn.abrir_conexion();
          parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
          parametro.setString(1, this.getCodigo());
          parametro.setString(2, this.getNombres());
          parametro.setString(3, this.getApellidos());
          parametro.setString(4, this.getDireccion());
          parametro.setString(5, this.getTelefono());
          parametro.setString(6, this.getFecha_nacimiento());
          parametro.setInt(7, this.getId_puesto());
          System.out.println(getId_puesto());
          
          int ejecutar = parametro.executeUpdate();
          cn.cerrar_conexion();
          JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro ingresado", "Agregar", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..." + ex.getMessage());
        }
    }
    
    @Override
    public void actualizar(){
          try{
          PreparedStatement parametro;

          String query = "UPDATE empleados SET codigo = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, id_puesto = ? WHERE id_empleado = ?";

          cn = new Conexion();
          cn.abrir_conexion();
          parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
          parametro.setString(1, this.getCodigo());
          parametro.setString(2, this.getNombres());
          parametro.setString(3, this.getApellidos());
          parametro.setString(4, this.getDireccion());
          parametro.setString(5, this.getTelefono());
          parametro.setString(6, this.getFecha_nacimiento());
          parametro.setInt(7, this.getId_puesto());
          parametro.setInt(8, this.getId_empleado());
          System.out.println("El id del empleado es" + getId_empleado());
          System.out.println("El id del puesto es" + getId_puesto());
          
          int ejecutar = parametro.executeUpdate();
          cn.cerrar_conexion();
          JOptionPane.showMessageDialog(null, Integer.toString(ejecutar) + " Registro actualizad", "Actualizar", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error..." + ex.getMessage());
        }
    }

        
    public DefaultTableModel leer(){
        
        DefaultTableModel tabla = new DefaultTableModel();
        
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT e.id_empleado, e.codigo, e.nombres, e.apellidos, e.direccion, e.telefono, e.fecha_nacimiento, p.puesto FROM empleados e INNER JOIN puestos p ON e.id_puesto = p.id_puesto;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado [ ] = {"ID empleado","Codigo","Nombres","Apellidos","Direccion","Telefono","Fecha Nacimiento","Puesto"};
            tabla.setColumnIdentifiers(encabezado);
            String datos [ ] = new String [8];
            
            while (consulta.next()){
                datos[0] = consulta.getString("id_empleado");
                datos[1] = consulta.getString("codigo");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("puesto");
                
                tabla.addRow(datos);  
            }  
            cn.cerrar_conexion();
        }catch(HeadlessException | SQLException ex){
            cn.cerrar_conexion();
            System.out.println("Error..." + ex.getMessage());
        }
        return tabla;
    }



}
