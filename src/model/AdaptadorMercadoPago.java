package model;

import interfaces.MercadoPagoAPI;

public class AdaptadorMercadoPago implements MercadoPagoAPI{
    public void pagar(Double monto, String pago) {
            switch (pago) {
                case "Tarjeta de Credito":
                    System.out.println("Pagado con tarjeta de credito: " + monto);
                    break;
                case "Tarjeta de Debito":
                    System.out.println("Pagado con tarjeta de debito: " + monto);
                    break;
                case "Transferencia":
                    System.out.println("Pagado con transferencia: " + monto);
                    break;
            }

    }
}
