package model;

import interfaces.INotificacion;

public class Email implements INotificacion {
    @Override
    public void enviarFactura(Cliente cliente, Factura factura) {
        System.out.println("Envio de factura mediante Email al cliente" + cliente.getNombre());
        System.out.println(factura.getContenido());
    }

    @Override
    public void enviarModificacion(Cliente cliente, Reserva reserva) {
        System.out.println("Envio de notificacion mediante Email al cliente" + cliente.getNombre() + "Estado actual de la reserva: " + reserva.getEstadoReserva().toString());
    }
}
