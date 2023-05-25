package controllers;

import ennumerations.PreferenciaContacto;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ControladorCliente {
    private List<Cliente> listaClientes = new ArrayList<Cliente>();

    public void altaCliente(String nombre, String apellido, Integer dni, Integer telefono, String email, PreferenciaContacto preferenciaContacto) {
        Cliente cliente = new Cliente(nombre,apellido, dni, telefono, email, preferenciaContacto);
        listaClientes.add(cliente);
    }
}
