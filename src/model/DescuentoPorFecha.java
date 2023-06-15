package model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DescuentoPorFecha implements Descuento{

    public int diferenciaDias(LocalDate fechaReserva, LocalDate checkIn) {
        return (int) ChronoUnit.DAYS.between(fechaReserva, checkIn);
    }
    
    public double calcularDescuento(Reserva reserva) {
        double indice = 0;
        int dias = diferenciaDias(reserva.getFechaReserva(), reserva.getCheckIn());
        if (dias >=15 && dias<60) 
            indice = -0.15;
        else if (dias>60)
            indice = 0.20;
        return reserva.getMonto() + reserva.getMonto()*indice;
    }


}
