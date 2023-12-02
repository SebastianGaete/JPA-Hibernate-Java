package com.sebastian.hibernateapp.consultashibernatepersonalizados;

import com.sebastian.hibernateapp.utility.JpaUtil;

import jakarta.persistence.EntityManager;

public class HibernateConsultaAgregacion {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();

        /* Las consultas de agregación son las determinadas para calcular el min, max, sum, count, avg(promedio) y lenght 
         * solamente de campos NUMÉRICOS DE CUALQUIER TIPO.
        */

        System.out.println("--------------- CANTIDAD TOTAL DE LOS REGISTROS ------------------");
        Long cantidadRegistros = em.createQuery("select count(c) from Cliente c", Long.class)
        .getSingleResult();
        System.out.println("Cantidad total de registros: " + cantidadRegistros);


        System.out.println("--------------- SUMA TOTAL DE LOS ID ------------------");
        Long sumadeId = em.createQuery("select sum(c.id) from Cliente c", Long.class)
        .getSingleResult();
        System.out.println("Suma de los id: " + sumadeId);


        System.out.println("--------------- ID MÍNIMO DE LOS REGISTROS ------------------");
        Integer idMinimo = em.createQuery("select min(c.id) from Cliente c", Integer.class)
        .getSingleResult();
        System.out.println("Primer o mínimo id: " + idMinimo);


        System.out.println("--------------- ID MÁXIMO DE LOS REGISTROS ------------------");
        Integer idMaximo = em.createQuery("select max(c.id) from Cliente c", Integer.class)
        .getSingleResult();
        System.out.println("Ultimo o máximo id: " + idMaximo);


        System.out.println("--------------- NOMBRE CON MÁS CARÁCTERES ------------------");
        Integer nombreMasLargo = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class)
        .getSingleResult();
        System.out.println("El nombre más largo tiene: " + nombreMasLargo + " carácteres");


        System.out.println("--------------- PROMEDIO DE LAS LONGUITUDES DE LOS NOMBRES ------------------");
        Double promedio = em.createQuery("select avg(length(c.nombre)) from Cliente c", Double.class)
        .getSingleResult();
        System.out.println("El promedio de las longuitudes de los nombres es: " +promedio);

    }
}