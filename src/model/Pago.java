package model;

import interfaces.FormaPago;

public class Pago {
    private FormaPago formaPago;

    public Pago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public void PagarReserva(double monto) {
        this.formaPago.pagar(monto);
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }
}
