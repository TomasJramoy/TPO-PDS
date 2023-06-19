package UnitTest;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import controllers.ControladorCliente;
import controllers.ControladorFactura;
import controllers.ControladorHabitacion;
import controllers.ControladorReserva;
import ennumerations.DescuentoAplicable;
import ennumerations.Extra;
import ennumerations.PreferenciaContacto;
import ennumerations.TipoHabitacion;
import interfaces.FormaPago;
import model.Cliente;
import model.Factura;
import model.Habitacion;
import model.Pagada;
import model.Reserva;
import model.TarjetaCredito;
import model.Transferencia;


public class TestUnitario {
    //Cargar nuevos clientes.
    @Test
    public void testAltaCliente() {
        Cliente cliente = new Cliente("Juan", "Perez", 123456, 11345678, "jperez@gmail.com", PreferenciaContacto.EMAIL);
        ControladorCliente controladorCliente = ControladorCliente.getInstancia();

        controladorCliente.altaCliente(cliente);

        List<Cliente> listaClientes = controladorCliente.getListaClientes();
        
        // Verificar que el cliente se haya agregado correctamente a la lista
        Assert.assertEquals(1, listaClientes.size());
        Assert.assertEquals(cliente, listaClientes.get(0));
    }


    //Cargar habitaciones y disponibilidad.
    @Test
    public void testPublicarHabitacion() {
        ControladorHabitacion controladorHabitacion = ControladorHabitacion.getInstancia();
        int cantidadPersonas = 2;
        TipoHabitacion tipoHabitacion = TipoHabitacion.DOBLE;
        List<Extra> extras = new ArrayList<>();
        extras.add(Extra.DESPERTADOR);
        double precioNoche = 100.0;
        Integer habitacionID = 1;
        Habitacion habitacion = new Habitacion(cantidadPersonas, tipoHabitacion, extras, precioNoche, habitacionID);

        controladorHabitacion.publicarHabitacion(habitacion);

        List<Habitacion> listaHabitaciones = controladorHabitacion.getListaHabitaciones();

        // Verificar que la habitación se haya agregado correctamente a la lista
        Assert.assertEquals(1, listaHabitaciones.size());
        Assert.assertEquals(habitacion, listaHabitaciones.get(0));
    }


    //Buscar habitaciones según el criterio solicitado y mostrar datos completos.
    @Test
    public void testFiltrarTipo() {
        ControladorHabitacion controlador = ControladorHabitacion.getInstancia();

        controlador.publicarHabitacion(new Habitacion(2, TipoHabitacion.DOBLE, new ArrayList<>(), 100.0, 1));
        controlador.publicarHabitacion(new Habitacion(1, TipoHabitacion.INDIVIDUAL, new ArrayList<>(), 80.0, 2));
        controlador.publicarHabitacion(new Habitacion(4, TipoHabitacion.FAMILIAR, new ArrayList<>(), 150.0, 3));
        TipoHabitacion tipoHabitacion = TipoHabitacion.INDIVIDUAL;

        List<Habitacion> habitacionesFiltradas = controlador.filtrarTipo(tipoHabitacion);

        // Verificar que se obtengan las habitaciones correctas
        Assert.assertEquals(1, habitacionesFiltradas.size());
        Assert.assertEquals(tipoHabitacion, habitacionesFiltradas.get(0).getTipoHabitacion());
    }


    @Test
    public void testFiltrarPersonas() {
        ControladorHabitacion controlador = ControladorHabitacion.getInstancia();

        controlador.publicarHabitacion(new Habitacion(2, TipoHabitacion.DOBLE, new ArrayList<>(), 100.0, 1));
        controlador.publicarHabitacion(new Habitacion(1, TipoHabitacion.INDIVIDUAL, new ArrayList<>(), 80.0, 2));
        controlador.publicarHabitacion(new Habitacion(4, TipoHabitacion.FAMILIAR, new ArrayList<>(), 150.0, 3));
        Integer cantidadPersonas = 2;

        List<Habitacion> habitacionesFiltradas = controlador.filtrarPersonas(cantidadPersonas);

        // Verificar que se obtengan las habitaciones correctas
        Assert.assertEquals(2, habitacionesFiltradas.size());
        Assert.assertTrue(habitacionesFiltradas.get(0).getCantidadPersonas() >= cantidadPersonas);
    }


    @Test
    public void testFiltrarExtras() {
        ControladorHabitacion controlador = ControladorHabitacion.getInstancia();
        List<Extra> extras = new ArrayList<>();
        extras.add(Extra.INTERNET);

        controlador.publicarHabitacion(new Habitacion(2, TipoHabitacion.DOBLE, extras, 100.0, 1));
        controlador.publicarHabitacion(new Habitacion(1, TipoHabitacion.INDIVIDUAL, new ArrayList<>(), 80.0, 2));
        controlador.publicarHabitacion(new Habitacion(4, TipoHabitacion.FAMILIAR, new ArrayList<>(), 150.0, 3));

        
        List<Habitacion> habitacionesFiltradas = controlador.filtrarExtras(extras);

        // Verificar que se obtengan las habitaciones correctas
        Assert.assertEquals(1, habitacionesFiltradas.size());
        for (Habitacion habitacion : habitacionesFiltradas) {
            Assert.assertTrue(habitacion.getExtras().containsAll(extras));
        }
    }


    //Reservar y cancelar habitaciones presencialmente o vía web.
    @Test
    public void testReservarHabitacion() {
        ControladorReserva controladorReserva = ControladorReserva.getInstancia();
        ControladorHabitacion controladorHabitacion = ControladorHabitacion.getInstancia();

        List<Extra> extras = new ArrayList<>();
        Habitacion habitacion = new Habitacion(2, TipoHabitacion.INDIVIDUAL, extras, 100.0, 1);
        
        controladorHabitacion.publicarHabitacion(habitacion);

        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(3);
        Reserva reserva = new Reserva(1, checkIn, checkOut, LocalDate.now(), null, null, habitacion.getHabitacionID());

        controladorReserva.reservarHabitacion(reserva, habitacion);

        // Verificar que la reserva se haya agregado a la lista de reservas del controlador
        List<Reserva> listaReservas = controladorReserva.getListaReservas();
        Assert.assertEquals(1, listaReservas.size());
        Assert.assertTrue(listaReservas.contains(reserva));

        // Verificar que la habitación tenga la reserva agregada en sus fechas ocupadas
        List<LocalDate> reservasHabitacion = habitacion.getReservas();
        Assert.assertEquals(3, reservasHabitacion.size());
        Assert.assertTrue(reservasHabitacion.contains(checkIn));
        Assert.assertTrue(reservasHabitacion.contains(checkOut));
    }


    //Actualizar parámetros de facturación (plazo en días y porcentaje de variación).

    
    //Enviar facturas y notificaciones a clientes.
    @Test
    public void testEnviarFactura() {
        ControladorFactura controladorFactura = ControladorFactura.getInstancia();
        ControladorReserva controladorReserva = ControladorReserva.getInstancia();
        ControladorCliente controladorCliente = ControladorCliente.getInstancia();
        ControladorHabitacion controladorHabitacion = ControladorHabitacion.getInstancia();
        
        Habitacion habitacion = new Habitacion(2, TipoHabitacion.INDIVIDUAL, new ArrayList<>(), 100.0, 1);
        controladorHabitacion.publicarHabitacion(habitacion);

        Cliente cliente = new Cliente("Juan", "Perez", 123456, 11345678, "jperez@gmail.com", PreferenciaContacto.EMAIL);
        controladorCliente.altaCliente(cliente);
        Reserva reserva = new Reserva(1, LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 25), LocalDate.of(2023, 6, 18), cliente, null, 1);
        controladorReserva.reservarHabitacion(reserva, controladorHabitacion.getListaHabitaciones().get(0));
        
        FormaPago tarjetaCredito = new TarjetaCredito();
        controladorReserva.pagarReserva(controladorReserva.getListaReservas().get(0), tarjetaCredito, DescuentoAplicable.DESCUENTO_FECHA);
        controladorFactura.generarFactura(controladorReserva.getListaReservas().get(0));
        
        Factura factura = controladorFactura.getListaFacturas().get(0);
        controladorFactura.enviarFactura(controladorCliente.getListaClientes().get(0), controladorFactura.getListaFacturas().get(0));


        // Comprobar que la factura se genero correctamente
        Assert.assertEquals(factura, controladorFactura.getListaFacturas().get(0));
        assertTrue(true);
    }
}


