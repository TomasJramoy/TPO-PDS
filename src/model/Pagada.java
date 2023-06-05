package model;

public class Pagada implements IEstadoReserva {
    @Override
    public void pagar(Reserva reserva, FormaPago formaPago, Descuento descuento) {
        System.out.println("La reserva ya se encuentra paga.");
    }

    @Override
    public void cancelar(Habitacion habitacion, Reserva reserva) {
        System.out.println("No puedes cancelar una reserva paga.");
    }

    @Override
    public String toString() {
        return "Pagada";
    }
}
