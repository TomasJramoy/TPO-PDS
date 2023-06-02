package model;

public class Pagada implements IEstadoReserva {
    @Override
    public void pagar(Reserva reserva) {
        reserva.setEstadoReserva(new Facturada());
    }

    @Override
    public String toString() {
        return "Pagada";
    }
}
