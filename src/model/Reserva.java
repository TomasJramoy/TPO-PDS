package model;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Reserva {
    private Integer nroReserva;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalDate fechaReserva;
    private Cliente cliente;
    private List<Huesped> huespedes;
    private IEstadoReserva estadoReserva;
    private double monto;

    public IEstadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    private Descuento estrategiaDescuento;
    private Integer habitacionID;
    private double montoFinal;

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Pago getPago() {
        return pago;
    }

    private Pago pago;

    public double getMontoFinal() {
        return montoFinal;
    }

    public double CalcularAnticipacionReserva() {
        return -1;
    }

    public void setEstrategiaDescuento(Descuento estrategia) {

    }

    public Integer getNroReserva() {
        return nroReserva;
    }

    public Descuento getEstrategiaDescuento() {
        return estrategiaDescuento;
    }

    public Reserva(Integer nroReserva, LocalDate checkIn, LocalDate checkOut, LocalDate fechaReserva, Cliente cliente, List<Huesped> huespedes, Integer habitacionID) {
        this.nroReserva = nroReserva;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.fechaReserva = fechaReserva;
        this.cliente = cliente;
        this.huespedes = huespedes;
        this.estadoReserva = new Registrada();
        this.estrategiaDescuento = estrategiaDescuento;
        this.habitacionID = habitacionID;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

}
