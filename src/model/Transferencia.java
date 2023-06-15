package model;

public class Transferencia extends FormaPago {
    private double cvu;
    private AdaptadorMercadoPago adaptador;
   
    public Transferencia() {
        this.adaptador= new AdaptadorMercadoPago();
    }


    @Override
    public String toString() {
        return "Transferencia";
    }

    @Override
    public void pagar(double monto) {
        this.adaptador.pagar(monto, this.toString());
    }
}
