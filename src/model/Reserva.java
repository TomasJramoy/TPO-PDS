package model;

import java.time.LocalDate;
import java.util.List;

public class Reserva {
    private Integer nroReserva;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalDate fechaReserva;
    private Cliente cliente;
    private List<Huesped> huespedes;
    private IEstadoReserva estadoReserva;
    private double monto;
    private Descuento estrategiaDescuento;
    private Integer habitacionID;
    private double montoFinal;

    public double CalcularAnticipacionReserva() {
        return -1;
    }

    public void setEstrategiaDescuento(Descuento estrategia) {

    }

    public double CalcularMonto() {
        return -1;
    }

    public double CalcularDescuento() {
        return -1;
    }
}
