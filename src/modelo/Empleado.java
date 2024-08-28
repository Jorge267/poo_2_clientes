/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


public class Empleado extends Persona{
    
    private String codigo_empleado, puesto;

    public Empleado(){}
    
    public Empleado(String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String codigo_empleado, String puesto) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo_empleado = codigo_empleado;
        this.puesto = puesto;
    }
    
    public String getCodigo_empleado() {
        return codigo_empleado;
    }

    public void setCodigo_empleado(String codigo_empleado) {
        this.codigo_empleado = codigo_empleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    @Override
    public void agregar(){
        System.out.println("Nombres " + getNombres());
        System.out.println("Apellidos " + getApellidos());
        System.out.println("Direccion " + getDireccion());
        System.out.println("Telefono " + getTelefono());
        System.out.println("Fecha Nacimiento " + getFecha_nacimiento());
        System.out.println("Codigo Empleado" + getCodigo_empleado());
        System.out.println("Puesto" + getPuesto());
    }

}
