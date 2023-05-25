package model;

public class Pago {
    private FormaPago formaPago;

    public Pago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public void PagarReserva(double monto) {
        this.formaPago.pagar(monto);
    }
}
