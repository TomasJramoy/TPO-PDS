package controllers;

import model.*;

import java.util.ArrayList;
import java.util.List;

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
        Factura factura = new Factura(reserva);
        reserva.getEstadoReserva().pagar(reserva);
        listaFacturas.add(factura);
    }

    public void enviarFactura(Cliente cliente, Factura factura) {
        if (cliente.getPreferenciaContacto().equals("SMS")) {
            INotificacion notificacion = new SMS();
            notificacion.enviarFactura(cliente, factura);
        } else if (cliente.getPreferenciaContacto().equals("WhatsApp")) {
            INotificacion notificacion = new Whatsapp();
            notificacion.enviarFactura(cliente, factura);
        } else if (cliente.getPreferenciaContacto().equals("email")) {
            INotificacion notificacion = new Email();
            notificacion.enviarFactura(cliente, factura);
        }

    }
}
