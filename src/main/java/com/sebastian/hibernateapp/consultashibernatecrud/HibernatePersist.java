package com.sebastian.hibernateapp.consultashibernatecrud;

import javax.swing.JOptionPane;

import com.sebastian.hibernateapp.entity.Cliente;
import com.sebastian.hibernateapp.utility.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernatePersist {
    public static void main(String[] args) {
        
        /* El método persist() se utiliza para insertar registros en una tabla, igual que la sentencia insert into de SQL 
         * Para poder utilizar el persist debemos implementar siempre el try, catch y finally, dentro del try vamos a utilizar el
         * método getTransaction en conjunto con el método beging() "iniciar transaccion" y commit() "guardar transacción" y 
         * en el medio de los dos utilizamos el persist() el cuál espera una nueva instancia de la clase Entity Cliente.
        */

        EntityManager em = JpaUtil.getEntityManager();

        try{
            Cliente c = new Cliente();
            String nombre = JOptionPane.showInputDialog("Ingresa el nombre");
            String apellido = JOptionPane.showInputDialog("Ingresa el nuevo apellido");
            String pago = JOptionPane.showInputDialog("Ingresa la forma de pago");   
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(pago);

             // Iniciamos la transaccion.
            em.getTransaction().begin();
            // Creamos la nueva instancia.
            em.persist(c);
            // guardamos la transacción de la instancia.
            em.getTransaction().commit();

            // Solo mostramos el registro del nuevo cliente por consola, buscandolo por su id (se pobla de forma automática "PK")
            c = em.find(Cliente.class, c.getId());
            System.out.println("El cliente:\n" + c + "\nHa sido creado con exito!");    
            
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }

    }
}