package com.sebastian.hibernateapp.consultashibernatepersonalizados;

import java.util.List;
import com.sebastian.hibernateapp.models.ClienteDto;
import com.sebastian.hibernateapp.utility.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateConsultaDTO {
    
    public static void main(String[] args) {
        

        /* Con JPA podemos realizar lo que se llama DTO (Data Transfer Object) esto permite que podamos realizar consultas 
         * SQL con clases que NO SON ENTITY, lo que pasa realmente es que Obtenemos los datos de una clase Entity, pero esos datos, campos
         * o registros, los transferimos a otra clase COMUN Y CORRIENTE, solo para mostrar los datos desde esa clase.
         * 
         * La utilización de una clase DTO para nuestras consultas es conveniente para no manejar tipos de datos Object.
         */

         EntityManager em = JpaUtil.getEntityManager();

        
         /* Para lograr transferir los datos a nuestra clase DTO en la consulta debemos crear una nueva instancia pero colocando el package
          * en donde está ubicado nuestra clase DTO y pasando por constructor los atributos que queremos poblar, y al finalizar indicamos
          * que los datos vendrán desde la clase Cliente con el alias c.
          */
        List<ClienteDto> listaClientes = em.createQuery(
            "select new com.sebastian.hibernateapp.models.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class)
        .getResultList();
        listaClientes.forEach(System.out::println);

        
        em.close();
    }
}