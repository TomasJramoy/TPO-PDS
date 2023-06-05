package controllers;

import ennumerations.Extra;
import ennumerations.TipoHabitacion;
import model.Habitacion;

import java.util.ArrayList;
import java.util.List;

public class ControladorHabitacion {
    private static ControladorHabitacion instancia;

    public static ControladorHabitacion getInstancia() {
        if (instancia==null) {
            instancia = new ControladorHabitacion();
        }
        return  instancia;
    }
    private List<Habitacion> listaHabitaciones = new ArrayList<Habitacion>();

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }
    public List<Habitacion> filtrarTipo(TipoHabitacion tipoHabitacion) {
        List<Habitacion> listaFiltrada = new ArrayList<Habitacion>();
        for (Habitacion habitacion: listaHabitaciones) {
            if (habitacion.getTipoHabitacion()==tipoHabitacion)
                listaFiltrada.add(habitacion);
        }
        return listaFiltrada;
    }

    public List<Habitacion> filtrarPersonas(Integer cantidad) {
        List<Habitacion> listaFiltrada = new ArrayList<Habitacion>();
        for (Habitacion habitacion: listaHabitaciones) {
            if (habitacion.getCantidadPersonas()>=cantidad)
                listaFiltrada.add(habitacion);
        }
        return listaFiltrada;
    }

    public List<Habitacion> filtrarExtras(List<Extra> extras) {
        List<Habitacion> listaFiltrada = new ArrayList<Habitacion>();
        for (Habitacion habitacion: listaHabitaciones) {
            boolean tiene = true;
            for (Extra extra:extras) {
                if (!habitacion.getExtras().contains(extra))
                    tiene = false;
            }
            if (tiene)
                listaFiltrada.add(habitacion);
        }
        return listaFiltrada;
    }

    public void publicarHabitacion(Integer cantidadPersonas, TipoHabitacion tipoHabitacion, List<Extra> extras, double precioNoche, Integer habitacionID) {
        Habitacion habitacion = new Habitacion(cantidadPersonas, tipoHabitacion, extras, precioNoche, habitacionID);
        listaHabitaciones.add(habitacion);
    }
}
