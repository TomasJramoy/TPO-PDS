package model;

import ennumerations.TipoHabitacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Habitacion {
    private Integer cantidadPersonas;
    private TipoHabitacion tipoHabitacion;
    private List<String> extras;
    private double precioNoche;
    private List<LocalDate> reservas;
    private Integer habitacionID;

    public Habitacion(Integer cantidadPersonas, TipoHabitacion tipoHabitacion, List<String> extras, double precioNoche, Integer habitacionID) {
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
