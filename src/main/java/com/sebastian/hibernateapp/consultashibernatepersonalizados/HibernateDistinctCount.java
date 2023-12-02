package com.sebastian.hibernateapp.consultashibernatepersonalizados;

import java.util.List;
import com.sebastian.hibernateapp.utility.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateDistinctCount {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();

        /* En el lenguaje SQL y tamnbien en JPA existe una función llamada DISTINTC, la cual permite que solo se muestren
         * registros en nuestra base de datos que no estén repetidos, si es que hay registros con campos iguales solo mostrará uno.
         */
        System.out.println("------------------ SOLO NOMBRES NO REPETIDOS -------------------");
         List<String> listaNombresClientes = em.createQuery("select distinct(c.nombre) from Cliente c ", String.class)
         .getResultList();
        listaNombresClientes.forEach(System.out::println);

        
        System.out.println("------------------ SOLO FORMAS DE PAGO NO REPETIDAS -------------------");
        List<String> listaUnicaPagos = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class)
        .getResultList();
        listaUnicaPagos.forEach(System.out::println);

    
        /* Ahora si deseamos obtener la cantidad en tipo de dato numérico de un registro se utiliza la función llamada COUNT
         * seguido de DISTINCT.
         */
         System.out.println("------------------ CANTIDAD DE FORMAS DE PAGO NO REPETIDAS -------------------");
         Long cantidadFormasPago = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class)
         .getSingleResult();

        System.out.println("Cantidad formas de pago: " + cantidadFormasPago);

    }    
}