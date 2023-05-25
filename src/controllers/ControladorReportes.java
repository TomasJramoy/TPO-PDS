package controllers;

import model.Habitacion;
import model.Reporte;
import model.Reserva;

import java.time.LocalDate;
import java.util.List;

public class ControladorReportes {
    public void generarReporte(List<Habitacion> listaHabitaciones) {
        for (Habitacion habitacion:listaHabitaciones) {
            System.out.println("La habitacion " + habitacion + " se encuentra ocupada las siguientes fechas:");
            for (LocalDate fecha: habitacion.getReservas()) {
                System.out.println(fecha);
            }
        }
    }
}
