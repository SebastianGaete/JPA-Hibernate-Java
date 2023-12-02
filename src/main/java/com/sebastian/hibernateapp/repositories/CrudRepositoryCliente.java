package com.sebastian.hibernateapp.repositories;

import java.util.List;


public interface CrudRepositoryCliente<T>{
    List<T> dataList();
    T getForId(Integer id);
    void create(T t);
    void update(Integer id, String nombre, String apellido, String formaPago);
    void delete(Integer id);
}