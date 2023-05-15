package model;

public interface INotificacion {
    public void EnviarFactura(Cliente cliente, Factura factura);
    public void EnviarModificacion(Cliente cliente, Reserva reserva);
}
