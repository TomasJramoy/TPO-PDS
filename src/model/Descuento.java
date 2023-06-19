package model;

public interface Descuento {

    public void modificarParametros(int dias1, double indice1, int dias2, Double indice2);

    public double calcularDescuento(Reserva reserva);
}
