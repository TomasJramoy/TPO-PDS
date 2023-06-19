package model;

import java.time.LocalDate;

import interfaces.FormaPago;

public abstract class Tarjeta implements FormaPago {
    private double numero;
    private LocalDate vencimiento;
    private Integer cvv;
    private String emisor;
    private AdaptadorMercadoPago adaptador;

    public abstract void pagar(double monto);
}
