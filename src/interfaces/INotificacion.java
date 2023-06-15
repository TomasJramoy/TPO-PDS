package interfaces;

import model.Cliente;
import model.Factura;
import model.Reserva;

public interface INotificacion {
    public void enviarFactura(Cliente cliente, Factura factura);
    public void enviarModificacion(Cliente cliente, Reserva reserva);
}
