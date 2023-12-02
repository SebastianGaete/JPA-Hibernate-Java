package com.sebastian.hibernateapp.consultashibernatecrud;

import java.util.List;
import com.sebastian.hibernateapp.entity.Cliente;
import com.sebastian.hibernateapp.utility.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class HibernateWhere {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();
        

        // Where con getSingleResult()
        String query = "select c from Cliente c where apellido = 'Fuentes' ";
        Cliente clienteCredito = em.createQuery(query, Cliente.class).getSingleResult();
        System.out.println(clienteCredito);
        

        // Where con getSingleResult() con parámetros (?1)
        
        String query2 = "select c from Cliente c where nombre = ?1 ";
        Query queryParam = em.createQuery(query2, Cliente.class);
        queryParam.setParameter(1, "Sebastian");
        Cliente cliente = (Cliente) queryParam.getSingleResult();

        System.out.println(cliente);


        // Where con getResultList() con parámetros (:atributo)

        String query3 = "select c from Cliente c where formaPago = :formaPago";
        List<Cliente> clientesDebito = em.createQuery(query3, Cliente.class)
        .setParameter("formaPago", "Debito")
        .getResultList();
        clientesDebito.forEach(System.out::println);


        em.close();
    }
}

