package controllers;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ennumerations.DescuentoAplicable;
import interfaces.FormaPago;

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

     public List<Reserva> getListaReservas() {
        return listaReservas;
    }


    public void controlarReservas() {
        for (Reserva reserva:listaReservas) {
            if (reserva.getCheckOut().isBefore(LocalDate.now())) {
                listaReservas.remove(reserva);
            }
        }
    }

    public void cancelarReserva(Habitacion habitacion, Reserva reserva) {
        reserva.getEstadoReserva().cancelar(habitacion,reserva);
    }

    public void pagarReserva(Reserva reserva, FormaPago formaPago, DescuentoAplicable descuentoAplicable) {
        Descuento descuento = FactoryDescuento.crearEstrategiaDescuento(descuentoAplicable);
        reserva.getEstadoReserva().pagar(reserva, formaPago, descuento);
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

    public void reservarHabitacion(Reserva reserva, Habitacion habitacion) {
        if (!habitacion.getReservas().contains(reserva.getCheckIn()) || !habitacion.getReservas().contains(reserva.getCheckOut())) {
            reserva.setMonto(calcularMontoReserva(reserva, habitacion));
            agregarReserva(reserva, habitacion);
            listaReservas.add(reserva);
        } else {
            System.out.println("La habitacion no se encuentra disponible en las fechas seleccionadas.");
        }
    }

}
