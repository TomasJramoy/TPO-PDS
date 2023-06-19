package model;

public class Reporte {
    private String contenido;

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void imprimir() {
        System.out.println(contenido);
    }
}
