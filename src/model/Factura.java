package model;

public class Factura {
    Reserva reserva;
    private String contenido;

    public Factura(Reserva reserva) {
        this.reserva = reserva;
        contenido = "Reserva nro: " + reserva.getNroReserva() + "\nMonto final: " + reserva.getMontoFinal() + "\nEstadia desde la fecha: " + reserva.getCheckIn() + " hasta: " + reserva.getCheckOut();
    }


}
