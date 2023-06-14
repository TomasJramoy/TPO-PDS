package model;

import ennumerations.PreferenciaContacto;

public class FactoryNotificacion {
    public static INotificacion crearEstrategiaNotificacion(PreferenciaContacto preferenciaContacto) {
        switch(preferenciaContacto) {
        case SMS:
            return new SMS();
        case EMAIL:
            return new Email();
        case WHATSAPP:
            return new Whatsapp();
        default:
            return new Whatsapp();
    }
    }
}
