package model;

import static java.time.temporal.ChronoUnit.DAYS;

import interfaces.FormaPago;
import interfaces.IEstadoReserva;
import interfaces.INotificacion;

public class Registrada implements IEstadoReserva {
    @Override
    public void pagar(Reserva reserva, FormaPago formaPago) {
        reserva.setEstadoReserva(new Pagada());
        reserva.getEstrategiaDescuento().calcularDescuento(reserva);
        Pago pago = new Pago(formaPago);
        reserva.setPago(pago);
        reserva.getPago().PagarReserva(reserva.getMontoFinal());
        INotificacion notificacion = FactoryNotificacion.crearEstrategiaNotificacion(reserva.getCliente().getPreferenciaContacto());
        notificacion.enviarModificacion(reserva.getCliente(), reserva);
        
    }

    @Override
    public void cancelar(Habitacion habitacion, Reserva reserva) {
            reserva.setEstadoReserva(new Cancelada());
            int dias = (int) DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
            for (int i=0; i<=dias;i++) {
                habitacion.EliminarOcupacion(reserva.getCheckIn().plusDays(i));
            }
            INotificacion notificacion = FactoryNotificacion.crearEstrategiaNotificacion(reserva.getCliente().getPreferenciaContacto());
            notificacion.enviarModificacion(reserva.getCliente(), reserva);
        }

    @Override
    public String toString() {
        return "Registrada";
    }
}
