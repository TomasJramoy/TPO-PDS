package controllers;

import model.*;

import java.util.ArrayList;
import java.util.List;

import interfaces.INotificacion;

public class ControladorFactura {
    private static ControladorFactura instancia;

    public static ControladorFactura getInstancia() {
        if (instancia==null) {
            instancia = new ControladorFactura();
        }
        return  instancia;
    }
    private List<Factura> listaFacturas = new ArrayList<Factura>();


    public void generarFactura(Reserva reserva) {
        if (reserva.getEstadoReserva().toString()=="Pagada") {
            Factura factura = new Factura(reserva);
            listaFacturas.add(factura);
        }
    }

    public void enviarFactura(Cliente cliente, Factura factura) {
        INotificacion notificacion = FactoryNotificacion.crearEstrategiaNotificacion(cliente.getPreferenciaContacto());
        notificacion.enviarFactura(cliente, factura);
        

    }
}
