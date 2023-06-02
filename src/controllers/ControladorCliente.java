package controllers;

import ennumerations.PreferenciaContacto;
import model.Cliente;
import model.FormaPago;
import model.Transferencia;

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

    public void AltaCliente(String nombre, String apellido, Integer dni, Integer telefono, String email, PreferenciaContacto preferenciaContacto) {
        Cliente cliente = new Cliente(nombre,apellido, dni, telefono, email, preferenciaContacto);
        listaClientes.add(cliente);
    }

}
