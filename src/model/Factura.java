package model;

public class Factura {
    Reserva reserva;
    private String contenido;

    public Factura(Reserva reserva) {
        this.reserva = reserva;
        contenido = "Reserva nro: " + reserva.getNroReserva() + "\nMonto final: " + reserva.getMontoFinal() + "\nMedio de Pago: " + reserva.getPago().getFormaPago().toString() +
                "\nEstadia desde la fecha: " + reserva.getCheckIn() + " hasta: " + reserva.getCheckOut();
    }

    public String getContenido() {
        return contenido;
    }
}
