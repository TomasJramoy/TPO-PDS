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
        if (reserva.getEstadoReserva().toString()=="Pagada") {
            Factura factura = new Factura(reserva);
        }
    }

    public void enviarFactura(Cliente cliente, Factura factura) {
        if (cliente.getPreferenciaContacto().equals("SMS")) {
            INotificacion notificacion = new SMS();
            notificacion.enviarFactura(cliente, factura);
        } else if (cliente.getPreferenciaContacto().equals("WHATSAPP")) {
            INotificacion notificacion = new Whatsapp();
            notificacion.enviarFactura(cliente, factura);
        } else if (cliente.getPreferenciaContacto().equals("EMAIL")) {
            INotificacion notificacion = new Email();
            notificacion.enviarFactura(cliente, factura);
        }

    }
}
