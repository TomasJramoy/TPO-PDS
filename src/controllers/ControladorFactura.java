package controllers;

import model.*;

public class ControladorFactura {
    public void generarFactura() {

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
