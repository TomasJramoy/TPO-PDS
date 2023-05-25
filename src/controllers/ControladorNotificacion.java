package controllers;

import model.*;

public class ControladorNotificacion {
    public void enviarModificacion(Cliente cliente, Reserva reserva) {
        if (cliente.getPreferenciaContacto().equals("SMS")) {
            INotificacion notificacion = new SMS();
            notificacion.enviarModificacion(cliente, reserva);
        } else if (cliente.getPreferenciaContacto().equals("WhatsApp")) {
            INotificacion notificacion = new Whatsapp();
            notificacion.enviarModificacion(cliente, reserva);
        } else if (cliente.getPreferenciaContacto().equals("email")) {
            INotificacion notificacion = new Email();
            notificacion.enviarModificacion(cliente, reserva);
        }
    }
}
