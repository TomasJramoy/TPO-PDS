package model;

import ennumerations.Extra;
import ennumerations.TipoHabitacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private int cantidadPersonas;
    private TipoHabitacion tipoHabitacion;
    private List<Extra> extras;
    private double precioNoche;
    private List<LocalDate> reservas;

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    private Integer habitacionID;

    public Habitacion(int cantidadPersonas, TipoHabitacion tipoHabitacion, List<Extra> extras, double precioNoche, int habitacionID) {
        this.cantidadPersonas = cantidadPersonas;
        this.tipoHabitacion = tipoHabitacion;
        this.extras = extras;
        this.precioNoche = precioNoche;
        this.reservas = new ArrayList<LocalDate>();
        this.habitacionID = habitacionID;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setReservas(List<LocalDate> reservas) {
        this.reservas = reservas;
    }

    public List<LocalDate> getReservas() {
        return reservas;
    }

    public Integer getHabitacionID() {
        return habitacionID;
    }

    public void AgregarOcupacion(LocalDate reserva) {
        reservas.add(reserva);
    }

    public void EliminarOcupacion(LocalDate reserva) {
        reservas.remove(reserva);
    }
}
