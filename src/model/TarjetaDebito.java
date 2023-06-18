package model;


public class TarjetaDebito extends Tarjeta {
    private AdaptadorMercadoPago adaptador;
    public TarjetaDebito() {
        this.adaptador= new AdaptadorMercadoPago();
    }
    
    @Override
    public String toString() {
        return "Tarjeta de Debito";
    }

    @Override
    public void pagar(double monto) {
        this.adaptador.pagar(monto, this.toString());
    }
}
