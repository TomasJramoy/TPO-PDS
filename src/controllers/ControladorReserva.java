package controllers;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControladorReserva {
    private static ControladorReserva instancia;

    public static ControladorReserva getInstancia() {
        if (instancia==null) {
            instancia = new ControladorReserva();
        }
        return  instancia;
    }
    private List<Reserva> listaReservas = new ArrayList<Reserva>();

    public void controlarReservas() {

    }

    public void cancelarReserva(Habitacion habitacion, Reserva reserva) {
        if (reserva.getEstadoReserva().equals(Registrada.class)) {
            listaReservas.remove(reserva);
            int dias = (int) DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
            for (int i=0; i<=dias;i++) {
                habitacion.EliminarOcupacion(reserva.getCheckIn().plusDays(i));
            }
        }
    }

    public void pagarReserva(Reserva reserva, FormaPago formaPago, Descuento descuento) {
        reserva.setEstrategiaDescuento(descuento);
        reserva.CalcularDescuento();
        Pago pago = new Pago(formaPago);
        reserva.setPago(pago);
        reserva.getPago().PagarReserva(reserva.getMontoFinal());
        reserva.getEstadoReserva().pagar(reserva);
    }

    public double calcularMontoReserva(Reserva reserva, Habitacion habitacion) {
        long dias = DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
        double monto = dias * habitacion.getPrecioNoche();
        return monto;
    }

    public void agregarReserva(Reserva reserva, Habitacion habitacion) {
        int dias = (int) DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
        for (int i=0; i<=dias;i++) {
            habitacion.AgregarOcupacion(reserva.getCheckIn().plusDays(i));
        }
    }

    public void reservarHabitacion(LocalDate checkIn, LocalDate checkOut, LocalDate fechaReserva, Cliente cliente, List<Huesped> huespedes, Habitacion habitacion) throws Exception {
        if (!habitacion.getReservas().contains(checkIn) || !habitacion.getReservas().contains(checkOut)) {
            Integer nroReserva = 1;
            if (!listaReservas.isEmpty()) {
                nroReserva = listaReservas.get(-1).getNroReserva() + 1;
            }

            Reserva reserva = new Reserva(nroReserva, checkIn, checkOut, fechaReserva, cliente, huespedes, habitacion.getHabitacionID());
            reserva.setMonto(calcularMontoReserva(reserva, habitacion));
            agregarReserva(reserva, habitacion);
            listaReservas.add(reserva);
        } else {
            throw new Exception("La habitacion no se encuentra disponible en las fechas seleccionadas.");
        }
    }

}
