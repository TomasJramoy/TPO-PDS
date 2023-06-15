package model;

import ennumerations.DescuentoAplicable;

public class FactoryDescuento {
    public static Descuento crearEstrategiaDescuento(DescuentoAplicable descuentoAplicable) {
        switch(descuentoAplicable) {
            case DESCUENTO_FECHA:
                return new DescuentoPorFecha();
            default:
                return new DescuentoPorFecha();
        }
    }
}
