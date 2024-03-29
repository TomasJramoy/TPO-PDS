package model;

import interfaces.FormaPago;

public class Transferencia implements FormaPago {
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
