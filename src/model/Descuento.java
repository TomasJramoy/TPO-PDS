package model;
import java.time.LocalDate;

public abstract class Descuento {
    public abstract DescuentoPorFecha calcularDescuento(LocalDate fechaReserva);
}
