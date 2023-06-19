package model;

import interfaces.FormaPago;
import interfaces.IEstadoReserva;

public class Pagada implements IEstadoReserva {
    @Override
    public void pagar(Reserva reserva, FormaPago formaPago) {
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
