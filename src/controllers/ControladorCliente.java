package controllers;

import ennumerations.PreferenciaContacto;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ControladorCliente {
    private static ControladorCliente instancia;

    public static ControladorCliente getInstancia() {
        if (instancia==null) {
            instancia = new ControladorCliente();
        }
        return  instancia;
    }
    private List<Cliente> listaClientes = new ArrayList<Cliente>();

    public void altaCliente(String nombre, String apellido, Integer dni, Integer telefono, String email, PreferenciaContacto preferenciaContacto) {
        Cliente cliente = new Cliente(nombre,apellido, dni, telefono, email, preferenciaContacto);
        listaClientes.add(cliente);
    }

    public List<Cliente> getListaClientes() {
        return this.listaClientes;
    }
}
