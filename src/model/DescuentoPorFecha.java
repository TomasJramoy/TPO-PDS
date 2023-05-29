package model;
import java.time.LocalDate;

public class DescuentoPorFecha extends Descuento{
    // @Override

    private double descuento = 1;
    
    private int _PLAZO_DESCUENTO_MINIMO_ = 15;
    private double _PORCENTAJE_DESCUENTO_MINIMO_ = 0.85;
    private int _PLAZO_DESCUENTO_MAXIMO_ = 61;
    private double _PORCENTAJE_DESCUENTO_MAXIMO_ = 0.80;

    public DescuentoPorFecha calcularDescuento(LocalDate fechaReserva) {
        LocalDate now = LocalDate.now();

        if (fechaReserva.isBefore(now.plusDays(_PLAZO_DESCUENTO_MAXIMO_))) {
            // this.descuento = monto * _PORCENTAJE_DESCUENTO_MAXIMO_;
            this.descuento = _PORCENTAJE_DESCUENTO_MAXIMO_;
        } else if (fechaReserva.isBefore(now.plusDays(_PLAZO_DESCUENTO_MINIMO_))) {
            // this.descuento = monto * _PORCENTAJE_DESCUENTO_MINIMO_;
            this.descuento = _PORCENTAJE_DESCUENTO_MINIMO_;
        }

        return this;
    }

    public double getDescuento() {
        return this.descuento;
    }

}
