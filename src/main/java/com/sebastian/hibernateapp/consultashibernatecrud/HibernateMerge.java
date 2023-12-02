package com.sebastian.hibernateapp.consultashibernatecrud;

import com.sebastian.hibernateapp.entity.Cliente;
import com.sebastian.hibernateapp.utility.JpaUtil;

import jakarta.persistence.EntityManager;

public class HibernateMerge {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente c = em.find(Cliente.class, 5);
            System.out.println("Cliente a modificar:\n" + c);

            c = em.merge(c);
            c.setNombre("Pedro");
            c.setApellido("Diaz");

            System.out.println("Cliente Modificado:\n" + c);
            
            em.getTransaction().commit();

        }catch (Exception e){

        }finally{
            em.close();
        }


    }
}