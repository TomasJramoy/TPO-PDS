package interfaces;

import model.Descuento;
import model.Habitacion;
import model.Reserva;

public interface IEstadoReserva {
    public void pagar(Reserva reserva, FormaPago formaPago, Descuento descuento);
    public void cancelar(Habitacion habitacion, Reserva reserva);

}
