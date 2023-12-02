package com.sebastian.hibernateapp.consultashibernatepersonalizados;

import java.util.List;
import com.sebastian.hibernateapp.utility.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateDistinct {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();

        /* En el lenguaje SQL y tamnbien en JPA existe una función llamada DISTINTC, la cual permite que solo se muestren
         * registros en nuestra base de datos que no estén repetidos, si es que hay registros con campos iguales solo mostrará uno.
         */

         List<String> listaNombresClientes = em.createQuery("select distinct(c.nombre) from Cliente c ", String.class)
         .getResultList();
        listaNombresClientes.forEach(System.out::println);


        List<String> listaUnicaPagos = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class)
        .getResultList();
        listaUnicaPagos.forEach(System.out::println);


    }    
}