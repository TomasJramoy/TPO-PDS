package model;

public interface INotificacion {
    public void enviarFactura(Cliente cliente, Factura factura);
    public void enviarModificacion(Cliente cliente, Reserva reserva);
}
