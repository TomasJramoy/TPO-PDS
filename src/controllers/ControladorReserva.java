package controllers;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControladorReserva {
    private List<Reserva> listaReservas = new ArrayList<Reserva>();

    public void controlarReservas() {

    }

    public void cancelarReserva(Integer nroReserva) {

    }

    public void pagarReserva(Reserva reserva) {

    }

    public double CalcularMontoReserva(Reserva reserva, Habitacion habitacion) {
        long dias = DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
        double monto = dias * habitacion.getPrecioNoche();
        return monto;
    }

    public double CalcularDescuentoReserva(Reserva reserva) {
        Descuento descuento = reserva.getEstrategiaDescuento();
        double montoFinal = descuento.calcularDescuento();
        return montoFinal;
    }

    public void AgregarReserva(Reserva reserva, Habitacion habitacion) {
        int dias = (int) DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
        for (int i=0; i<=dias;i++) {
            habitacion.AgregarOcupacion(reserva.getCheckIn().plusDays(i));
        }
    }

    public void ReservarHabitacion(LocalDate checkIn, LocalDate checkOut, LocalDate fechaReserva, Cliente cliente, List<Huesped> huespedes, Descuento estrategiaDescuento, Habitacion habitacion) {
        Integer nroReserva = 1;
        if (!listaReservas.isEmpty()) {
            nroReserva = listaReservas.get(-1).getNroReserva() + 1;
        }

        Reserva reserva = new Reserva(nroReserva,checkIn,checkOut,fechaReserva,cliente,huespedes,habitacion.getHabitacionID());
        reserva.setEstrategiaDescuento(estrategiaDescuento);
        reserva.setMonto(CalcularMontoReserva(reserva, habitacion));
        reserva.setMontoFinal(CalcularDescuentoReserva(reserva));
        AgregarReserva(reserva, habitacion);
        listaReservas.add(reserva);
    }

}
