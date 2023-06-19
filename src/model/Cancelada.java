package model;

import interfaces.FormaPago;
import interfaces.IEstadoReserva;

public class Cancelada implements IEstadoReserva {
    @Override
    public void pagar(Reserva reserva, FormaPago formaPago) {
        System.out.println("La reserva se encuentra cancelada");
    }

    @Override
    public void cancelar(Habitacion habitacion, Reserva reserva) {
        System.out.println("La reserva se encuentra cancelada");
    }

    @Override
    public String toString() {
        return "Cancelada";
    }
}
