package model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DescuentoPorFecha implements Descuento{
    private int dias1 = 15;
    private int dias2 = 60;
    private double indice1 = -0.15;
    private double indice2 = 0.20;


    public void modificarParametros(int d1, double i1, int d2, Double i2) {
        this.dias1 = d1;
        this.indice1 = i1;
        this.dias2 = d2;
        this.indice2 = i2;
    }

    public int diferenciaDias(LocalDate fechaReserva, LocalDate checkIn) {
        return (int) ChronoUnit.DAYS.between(fechaReserva, checkIn);
    }
    

    public double calcularDescuento(Reserva reserva) {
        double indice = 0;
        int dias = diferenciaDias(reserva.getFechaReserva(), reserva.getCheckIn());
        if (dias >=dias1 && dias<dias2) 
            indice = indice1;
        else if (dias>dias2)
            indice = indice2;
        return reserva.getMonto() + reserva.getMonto()*indice;
    }


   

}
