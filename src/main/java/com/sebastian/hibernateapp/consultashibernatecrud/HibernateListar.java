package com.sebastian.hibernateapp.consultashibernatecrud;

import java.util.List;

import com.sebastian.hibernateapp.entity.Cliente;
import com.sebastian.hibernateapp.utility.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateListar {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();

        List<Cliente> clientes =  em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        em.close();

    }
}