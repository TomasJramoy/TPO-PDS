package model;

import ennumerations.PreferenciaContacto;

public class Cliente {
    private String nombre;
    private String apellido;
    private Integer dni;
    private Integer telefono;
    private String email;

    public PreferenciaContacto getPreferenciaContacto() {
        return preferenciaContacto;
    }

    private PreferenciaContacto preferenciaContacto;

    public Cliente(String nombre, String apellido, Integer dni, Integer telefono, String email, PreferenciaContacto preferenciaContacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.preferenciaContacto = preferenciaContacto;
    }
}
