package model;

public class TarjetaCredito extends Tarjeta {
    @Override
    public void pagar(double monto) {
        System.out.println("Pagado con tarjeta de credito: " + monto);
    }
}
