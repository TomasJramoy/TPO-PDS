package model;

public interface IEstadoReserva {
    public void pagar(Reserva reserva, FormaPago formaPago, Descuento descuento);
    public void cancelar(Habitacion habitacion, Reserva reserva);

}
