package model;

public class Efectivo extends FormaPago {
    @Override
    public void pagar(double monto) {
        System.out.println("Pagado con efectivo: " + monto);
    }
}
