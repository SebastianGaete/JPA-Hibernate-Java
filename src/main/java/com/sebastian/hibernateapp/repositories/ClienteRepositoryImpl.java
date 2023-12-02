package com.sebastian.hibernateapp.repositories;

import java.util.List;
import com.sebastian.hibernateapp.entity.Cliente;
import com.sebastian.hibernateapp.utility.JpaUtil;
import jakarta.persistence.EntityManager;


public class ClienteRepositoryImpl implements CrudRepositoryCliente<Cliente> {

    private EntityManager em;
    private Cliente cliente;
    
    public ClienteRepositoryImpl() {
        this.em = JpaUtil.getEntityManager();
    }

    @Override
    public void create(Cliente t) {
        try{
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.getMessage();
        }
    }
    
    @Override
    public List<Cliente> dataList() {
        List<Cliente> list = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        return list;
    }
    
    @Override
    public void delete(Integer id) {
        try{
            em.getTransaction().begin();
            cliente = getForId(id);
            em.remove(cliente);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.getMessage();
        }
    }
    
    @Override
    public Cliente getForId(Integer id) {
        cliente = em.find(Cliente.class, id);
        return cliente;
    }
    
    @Override
    public void update(Integer id, String nombre, String apellido, String formaPago) {
        try{
            em.getTransaction().begin();
            cliente = getForId(id);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setFormaPago(formaPago);
            em.merge(cliente);
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
            e.getMessage();
        }
    }
 
}