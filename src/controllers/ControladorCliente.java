package controllers;

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

    public void altaCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public List<Cliente> getListaClientes() {
        return this.listaClientes;
    }
}
