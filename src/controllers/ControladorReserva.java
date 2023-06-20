package controllers;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ennumerations.DescuentoAplicable;
import interfaces.FormaPago;
import interfaces.INotificacion;

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


    public void aplicarDescuento(Reserva reserva, DescuentoAplicable descuentoAplicable) {
        Descuento descuento = FactoryDescuento.crearEstrategiaDescuento(descuentoAplicable);
        reserva.setEstrategiaDescuento(descuento);
        reserva.CalcularDescuento();
    }

    public void modificarDescuento(Reserva reserva, int dias1, double indice1, int dias2, double indice2) {
        reserva.getEstrategiaDescuento().modificarParametros(dias1, indice1, dias2, indice2);
    }


    public void pagarReserva(Reserva reserva, FormaPago formaPago) {
        reserva.CalcularDescuento();
        reserva.getEstadoReserva().pagar(reserva, formaPago);
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
        if (!(habitacion.getReservas().contains(reserva.getCheckIn()) || habitacion.getReservas().contains(reserva.getCheckOut()))) {
            INotificacion notificacion = FactoryNotificacion.crearEstrategiaNotificacion(reserva.getCliente().getPreferenciaContacto());
            notificacion.enviarModificacion(reserva.getCliente(), reserva);
            reserva.setMonto(calcularMontoReserva(reserva, habitacion));
            agregarReserva(reserva, habitacion);
            listaReservas.add(reserva);
        } else {
            System.out.println("La habitacion no se encuentra disponible en las fechas seleccionadas.");
        }
    }

}
