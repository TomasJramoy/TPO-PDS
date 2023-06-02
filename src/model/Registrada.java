package model;

public class Registrada implements IEstadoReserva {
    @Override
    public void pagar(Reserva reserva) {
        reserva.setEstadoReserva(new Pagada());
    }
}
