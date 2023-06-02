package model;

public class TarjetaDebito extends Tarjeta {
    @Override
    public String toString() {
        return "Tarjeta de Debito";
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pagado con tarjeta de debito: " + monto);
    }
}
