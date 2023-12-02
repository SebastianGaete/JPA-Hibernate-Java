package com.sebastian.hibernateapp.consultashibernatecrud;

import javax.swing.JOptionPane;

import com.sebastian.hibernateapp.entity.Cliente;
import com.sebastian.hibernateapp.utility.JpaUtil;

import jakarta.persistence.EntityManager;

public class HibernateRemove {
    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManager();

        try{

            em.getTransaction().begin();

            Integer id = Integer.valueOf(JOptionPane.showInputDialog("Indica el id del cliente a eliminar"));
            Cliente c = em.find(Cliente.class, id);

            String opcion = JOptionPane.showInputDialog("¿seguro que deseas eliminar el cliente? \n" + c + "\nY / N");
            switch (opcion) {
                
                case "Y" : 
                em.remove(c);
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "El cliente se ha eliminado con exito!");
                break;  

                case "N": 
                JOptionPane.showMessageDialog(null, "Se canceló la operación!");
                break;

                default:
                    JOptionPane.showMessageDialog(null, "Debes seleccionar una opción valida!");
            }

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally{
            em.close();
        }
    }
}