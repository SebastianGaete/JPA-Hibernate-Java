package com.sebastian.hibernateapp.consultashibernatepersonalizados;

import java.util.List;

import com.sebastian.hibernateapp.entity.Cliente;
import com.sebastian.hibernateapp.utility.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateFuncionesVarias {
    public static void main(String[] args) {
        
        /* Tambien como existen en Java las funciones upperCase, lowerCase, concat, etc. Tambien se encuentran en SQL 
         * y porsupuesto que JPA tambien los soporta, utilizados como upper(), lower(), concat() 
        */

        EntityManager em = JpaUtil.getEntityManager();

        /*Utilizando el concat ya sea de una de las dos formas, nos ahorramos en Utilizar un tipo de dato Object[]
         * para almacenar los campos, o tambien el uso de una clase DTO. Solo usamos una Lista de String, ya que el concat()
         * devuelve un String.
         */
        System.out.println("-------------- FORMA 1 CONCATENAR CAMPOS DE LOS REGISTROS EN LA MISMA CONSULTA ---------------");
        List<String> listaNombreApellido = em.createQuery("select concat(c.nombre, ' ', c.apellido) from Cliente c",
        String.class)
        .getResultList();

        listaNombreApellido.forEach(System.out::println);


        System.out.println("-------------- FORMA 2 CONCATENAR CAMPOS DE LOS REGISTROS EN LA MISMA CONSULTA ---------------");
        listaNombreApellido = em.createQuery("select c.nombre || ' - ' || c.apellido from Cliente c",
        String.class)
        .getResultList();

        listaNombreApellido.forEach(System.out::println);


        System.out.println("-------------- UTILIZACIÓN DE UPPER Y LOWER ---------------");
        listaNombreApellido = em.createQuery("select concat( upper(c.nombre), ' ', lower(c.apellido) ) from Cliente c",
        String.class)
        .getResultList();

        listaNombreApellido.forEach(System.out::println);


        /* Tambien existe una función llamada LIKE la cual busca campos que conincidan con lo que nosotros indiquemos, encuentra valores
         * que CONTENGAN lo que se pase por la función like. 
         */
        System.out.println("-------------- UTILIZACIÓN DE COINCIDENCIA LIKE ---------------");
        String param = "amo";
        List<Cliente> listaPorCoinsidencia = em.createQuery("select c from Cliente c where c.apellido like :param", Cliente.class)
        // La utilización de los "%" es para que se inicie la busqueda por derecha e izquierda de lo entregado.
        .setParameter("param", "%" + param + "%")
        .getResultList();

        listaPorCoinsidencia.forEach(System.out::println);


        /* Utilización de función BETWEEN() sirve para obtener registros entre rangos solo entre tipos de datos numéricos, char, y fechas */
        System.out.println("-------------- OBTENCION DE REGISTROS ENTRE RANGO BETWEEN() ---------------");
        List<Cliente> clientes = em.createQuery("select c from Cliente c where c.id between 3 and 10", Cliente.class)
        .getResultList();
        clientes.forEach(System.out::println);


        /* Utilización de función ORDER BY sirve para ordenar la salida de los registros conforme a un campo o varios en orden ascendente
         * o descendente.
         */
        System.out.println("-------------- ORDENAMIENTO DE REGISTROS CON ORDER BY ASC - DESC ---------------");
        clientes = em.createQuery("select c from Cliente c order by c.nombre asc", Cliente.class)
        .getResultList();
        clientes.forEach(System.out::println);

    }
}