package com.sebastian.hibernateapp;

import java.util.List;

import javax.swing.JOptionPane;
import com.sebastian.hibernateapp.entity.Cliente;
import com.sebastian.hibernateapp.repositories.ClienteRepositoryImpl;
import com.sebastian.hibernateapp.repositories.CrudRepositoryCliente;
import com.sebastian.hibernateapp.utility.JpaUtil;

import jakarta.persistence.EntityManager;

public class ClienteMain {
    private static final EntityManager em = JpaUtil.getEntityManager();
    private static final CrudRepositoryCliente<Cliente> repository = new ClienteRepositoryImpl();

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Bienvenido al gestos de clientes");
    
        try{
            Integer operacion = Integer.valueOf(JOptionPane.showInputDialog("Ingresa una de las siguientes opciones\n[1] Listar\n[2] Buscar\n[3] Actualizar\n[4] Crear\n[5] Eliminar\n[6] Salir"));
            while (operacion != 6){
                switch (operacion) {
                
                case 1:
                    listaClientes();
                    break;

                case 2:
                    Integer id = Integer.valueOf(JOptionPane.showInputDialog("Ingrese el id del cliente que desea buscar: "));
                    Cliente cliente = repository.getForId(id);
                    JOptionPane.showMessageDialog(null, cliente);
                    em.close();
                    break;

                case 3:
                    id = Integer.valueOf(JOptionPane.showInputDialog("Ingrese el id del cliente que desea buscar: "));
                    cliente = repository.getForId(id);
                    String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:", cliente.getNombre());
                    String apellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido:", cliente.getApellido());
                    String formaPago = JOptionPane.showInputDialog("Ingrese la nueva forma de pago:", cliente.getFormaPago());
                    cliente.setNombre(nombre);
                    cliente.setApellido(apellido);
                    cliente.setFormaPago(formaPago);
                    repository.create(cliente);
                    em.close();
                    break;

                case 4:
                    nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                    apellido = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                    formaPago = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                    cliente = new Cliente(nombre, apellido, formaPago);
                    repository.create(cliente);
                    em.close();
                    break;

                case 5:
                    id = Integer.valueOf(JOptionPane.showInputDialog("Ingrese el id del cliente que desea eliminar: "));
                    repository.delete(id);
                    em.close();
                    break;

                default:
                    JOptionPane.showMessageDialog(null,"Asegurate de ingresar una opción valida");
                }
                operacion = Integer.valueOf(JOptionPane.showInputDialog("Ingresa una de las siguientes opciones\n[1] Listar\n[2] Buscar\n[3] Actualizar\n[4] Crear\n[5] Eliminar\n[6] Salir"));
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Debes ingresar opciones numéricas");
        }

        JOptionPane.showMessageDialog(null,"Hasta Pronto!");
    }


    public static void listaClientes(){
        List<Cliente> lista = repository.dataList();
        JOptionPane.showMessageDialog(null,"Lista de Clientes\n" + lista);
        em.close();
    }


}



