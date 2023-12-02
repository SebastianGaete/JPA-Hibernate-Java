package com.sebastian.hibernateapp.consultashibernatecrud;

import java.util.Scanner;

import com.sebastian.hibernateapp.entity.Cliente;
import com.sebastian.hibernateapp.utility.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateFindId {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        EntityManager em = JpaUtil.getEntityManager();

        System.out.print("Ingrese el id del cliente que desea buscar: ");
        Integer id = sc.nextInt();

        /* En vez de utilizar el createQuery() podemos utilizar el método find() para encontrar un registro con un id en específico
         * y además no se tiene que escribir la consulta SQL, ya que el método find() lo hace por debajo.
         */
        Cliente cliente = em.find(Cliente.class, id);

        System.out.println(cliente);

        sc.close();
        em.close();

    }
}