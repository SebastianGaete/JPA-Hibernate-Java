package com.sebastian.hibernateapp.consultashibernatepersonalizados;

import java.util.List;
import com.sebastian.hibernateapp.entity.Cliente;
import com.sebastian.hibernateapp.utility.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateConsultaDinamica {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();

        System.out.println("------------- Lista de Clientes -------------------");
        List<Cliente> lista = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        lista.forEach(System.out::println);
        
        
        System.out.println("--------------- Busqueda de Cliente por id (parámetro) ----------------");
        Cliente objetoCliente = em.createQuery("select c from Cliente c where id = :id ", Cliente.class)
        /* select c from Cliente c where id = :id   Tambien puede ser de esa manera, pero en el setParameter hay que colocar la posicion */
        .setParameter("id", 2)
        .getSingleResult();
        System.out.println("Cliente encontrado: " + objetoCliente);


        System.out.println("--------------- Obtener solo un campo de un registro ----------------");
        // En este caso como solo estamos pidiendo un campo del registro, lo debemos guardar en el tipo de dato que es ese campo.
        String nombreCliente = em.createQuery("select c.nombre from Cliente c where id=:id", String.class)
        .setParameter("id", 8)
        .getSingleResult();
        System.out.println("Nombre del cliente: " + nombreCliente);


        System.out.println("--------------- Obtener dos o más campos de un registro ----------------");
        /* En este caso cuando obtenemos más de un campo debemos guardarlo en un Array del tipo Object, porque en un indice 
         * puede venir un campo Integer, Date, String, es po eso que los capturamos con el tipo de dato más genérico
        */
        Object[] camposClientes = em.createQuery("select c.nombre, c.formaPago from Cliente c where id=:id", Object[].class)
        .setParameter("id", 2)
        .getSingleResult();
        String nombre = (String) camposClientes[0];
        String formaPago = (String) camposClientes[1];
        System.out.println("Campos del cliente: " + nombre + " - " + formaPago);

        
        System.out.println("--------------- Obtener dos o más campos de varios registros ----------------");
        List<Object[]> camposClientes2 = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c ", Object[].class)
        .getResultList();

        camposClientes2.forEach(c ->{
            Integer id = (Integer) c[0];
            String nombre2 = (String) c[1];
            String apellido = (String) c[2];
            System.out.println("Id: " + id + " Nombre: " + nombre2 + " Apellido: " + apellido);
            
        });

        System.out.println("--------------- Obtener un campo y el registro completo a la vez ----------------");
        List<Object[]> pagoYClientes = em.createQuery("select c.formaPago, c from Cliente c", Object[].class).getResultList();
        pagoYClientes.forEach(reg ->{
            String pago = (String) reg[0];
            Object c = (Cliente) reg[1];
            System.out.println(pago + " || " + c);
        });


        
    }
}