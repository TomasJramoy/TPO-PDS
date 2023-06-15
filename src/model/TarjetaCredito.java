package model;

public class TarjetaCredito extends Tarjeta {
    private AdaptadorMercadoPago adaptador;
    public TarjetaCredito() {
        this.adaptador= new AdaptadorMercadoPago();
    }
    
    @Override
    public String toString() {
        return "Tarjeta de Credito";
    }

    @Override
    public void pagar(double monto) {
        this.adaptador.pagar(monto, this.toString());
    }
}
