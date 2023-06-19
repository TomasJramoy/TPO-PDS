package interfaces;

import model.Descuento;
import model.Habitacion;
import model.Reserva;

public interface IEstadoReserva {
    public void pagar(Reserva reserva, FormaPago formaPago);
    public void cancelar(Habitacion habitacion, Reserva reserva);

}
