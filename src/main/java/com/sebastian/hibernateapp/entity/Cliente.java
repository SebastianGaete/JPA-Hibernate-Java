package com.sebastian.hibernateapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* Para poder mapear una clase de Java a una tabla de la base de datos, debemos crear una clase
 * con atributos identicos a los de las columnas de la tabla, no obstante debemos utilizar la anotación @ Entity en la clase
 * creada para indicar que esa clase será mapeada.
 * Como todo debe ser igual a los campos de las tablas, debemos configurar el name de cada atributo o nombre de clase para que
 * sea identico para el nombre de tabla usamos @Table(name=" ") y para las columnas usamos @Column(name=" ")
 * Para las Primary Key utilizamos @Id acompañado de @GeneratedValue ya que será autoincremental.
 */
@Entity
@Table (name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;

    @Column(name="forma_pago")
    private String formaPago;


    public Cliente() {
    }
    
    public Cliente(Integer id, String nombre, String apellido, String formaPago) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }
    


    public Cliente(String nombre, String apellido, String formaPago) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: " + this.id).append(" | ")
        .append("Nombre: " + this.nombre).append(" | ")
        .append("Apellido: " + this.apellido).append(" | ")
        .append("Forma de Pago: " + this.formaPago).append("\n");

        return sb.toString();
    }
    
}