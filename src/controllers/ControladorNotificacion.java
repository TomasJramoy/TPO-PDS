package controllers;

import model.*;

public class ControladorNotificacion {
    private static ControladorNotificacion instancia;

    public static ControladorNotificacion getInstancia() {
        if (instancia==null) {
            instancia = new ControladorNotificacion();
        }
        return  instancia;
    }
    public void enviarModificacion(Cliente cliente, Reserva reserva) {
        if (cliente.getPreferenciaContacto().equals("SMS")) {
            INotificacion notificacion = new SMS();
            notificacion.enviarModificacion(cliente, reserva);
        } else if (cliente.getPreferenciaContacto().equals("WHATSAPP")) {
            INotificacion notificacion = new Whatsapp();
            notificacion.enviarModificacion(cliente, reserva);
        } else if (cliente.getPreferenciaContacto().equals("EMAIL")) {
            INotificacion notificacion = new Email();
            notificacion.enviarModificacion(cliente, reserva);
        }
    }
}
