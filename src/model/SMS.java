package model;

public class SMS implements INotificacion {

    @Override
    public void enviarFactura(Cliente cliente, Factura factura) {

    }

    @Override
    public void enviarModificacion(Cliente cliente, Reserva reserva) {
        String texto = "Su reserva ha pasado al estado: " + reserva.getEstadoReserva().toString();
    }
}
