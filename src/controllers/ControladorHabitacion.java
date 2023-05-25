package controllers;

import ennumerations.TipoHabitacion;
import model.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class ControladorHabitacion {
    private List<Habitacion> listaHabitaciones = new ArrayList<Habitacion>();

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }
/*public List<Habitacion> filtrar(String variable, String ordenamiento) {

    } */

    public void PublicarHabitacion(Integer cantidadPersonas, TipoHabitacion tipoHabitacion, List<String> extras, double precioNoche, Integer habitacionID) {
        Habitacion habitacion = new Habitacion(cantidadPersonas, tipoHabitacion, extras, precioNoche, habitacionID);
        listaHabitaciones.add(habitacion);
    }
}
