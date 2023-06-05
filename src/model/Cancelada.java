package model;

public class Cancelada implements IEstadoReserva {
    @Override
    public void pagar(Reserva reserva, FormaPago formaPago, Descuento descuento) {
        System.out.println("La reserva se encuentra cancelada");
    }

    @Override
    public void cancelar(Habitacion habitacion, Reserva reserva) {
        System.out.println("La reserva se encuentra cancelada");
    }
}
