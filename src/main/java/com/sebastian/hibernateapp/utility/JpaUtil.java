package com.sebastian.hibernateapp.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/* 
 * Esta clase JpaUtil en simples palabras, permite obtener una conexión EntityManager la cual nos permitirá
 * utilizar todos los métodos para nuestros CRUD, y es un SINGLETON.
 */
public class JpaUtil {
    
    // Atributo donde vamos a guardar el EntityManager creado en el método buildEntityManagerFactory. 
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    // Con este método privado, vamos a poder crear un EntityManager desde la fábrica.
    private static EntityManagerFactory buildEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("primerJPA");
    }

    // Este el el método con el que vamos a obtener la conexion o el Entity Manager
    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }


}