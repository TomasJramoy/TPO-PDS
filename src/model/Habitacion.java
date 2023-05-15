package model;

import ennumerations.TipoHabitacion;

import java.util.List;

public class Habitacion {
    private Integer cantidadPersonas;
    private TipoHabitacion tipoHabitacion;
    private List<String> extras;
    private double precioNoche;
    private List<Reserva> reservas;
    private Integer habitacionID;

}
