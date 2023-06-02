package model;

public class TarjetaDebito extends Tarjeta {
    @Override
    public void pagar(double monto) {
        System.out.println("Pagado con tarjeta de debito: " + monto);
    }
}
