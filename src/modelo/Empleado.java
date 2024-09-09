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
    private int id_puesto;
    Conexion cn;

    public Empleado(){}
    public Empleado(String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String codigo_empleado, int id_puesto) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo_empleado;
        this.id_puesto = id_puesto;
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



}
