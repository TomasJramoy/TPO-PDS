package model;

import java.time.LocalDate;

public abstract class Tarjeta extends FormaPago {
    private double numero;
    private LocalDate vencimiento;
    private Integer cvv;
    private String emisor;
    private AdaptadorMercadoPago adaptador;

    public abstract void pagar(double monto);
}
