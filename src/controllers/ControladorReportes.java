package controllers;

import model.Habitacion;
import model.Reporte;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorReportes {
    private static ControladorReportes instancia;

    public static ControladorReportes getInstancia() {
        if (instancia==null) {
            instancia = new ControladorReportes();
        }
        return  instancia;
    }
    private List<Reporte> listaReportes = new ArrayList<Reporte>();

    public void generarReporte(List<Habitacion> listaHabitaciones) {
        Reporte reporte = new Reporte();
        String contenido = "";
        for (Habitacion habitacion:listaHabitaciones) {
            contenido = contenido + "\nLa habitacion " + habitacion.getHabitacionID() + " se encuentra ocupada las siguientes fechas: " ;
            for (LocalDate fecha: habitacion.getReservas()) {
                contenido = contenido + fecha;
            }
        }
        reporte.setContenido(contenido);
        listaReportes.add(reporte);
    }


}
