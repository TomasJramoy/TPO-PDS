package model;

public class TarjetaCredito extends Tarjeta {
    @Override
    public String toString() {
        return "Tarjeta de Credito";
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pagado con tarjeta de credito: " + monto);
    }
}
