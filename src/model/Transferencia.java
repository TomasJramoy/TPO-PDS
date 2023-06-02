package model;

public class Transferencia extends FormaPago {
    private double cvu;
    private AdaptadorMercadoPago adaptador;

    @Override
    public String toString() {
        return "Transferencia";
    }

    @Override
    public void pagar(double monto) {
        System.out.println("Pagado con transferencia: " + monto);
    }
}
