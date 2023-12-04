package com.sebastian.hibernateapp.consultashibernatepersonalizados;


import com.sebastian.hibernateapp.utility.JpaUtil;

import jakarta.persistence.EntityManager;

public class HibernateSubquery {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();

        /* En las consultas SQL podemos generar Consultas anidadas dentro de algún WHERE, como en los siguientes ejemplos */

        System.out.println("-------------- Obtener el nombre más largo de todos --------------------");
        String nombreMasLargo = em.createQuery
        ("select c.nombre from Cliente c where length(c.nombre) = (select max(length(c.nombre)) from Cliente c)", 
        String.class).setMaxResults(1).getSingleResult();
        System.out.println("Nombre más largo: " + nombreMasLargo);


        System.out.println("-------------- Obtener el apellido más corto de todos --------------------");
        String apellidoMasCorto = em.createQuery
        ("select c from Cliente c where length(c.apellido) = (select min(length(c.apellido)) from Cliente c)", String.class)
        .setMaxResults(1).getSingleResult();
        System.out.println("Apellido más corto: " + apellidoMasCorto);

    }
}