package model;

import interfaces.FormaPago;

public class Efectivo implements FormaPago {
    @Override
    public void pagar(double monto) {
        System.out.println("Pagado con efectivo: " + monto);
    }

    @Override
    public String toString() {
        return "Efectivo";
    }
}
