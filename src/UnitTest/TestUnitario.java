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
import controllers.ControladorReportes;
import controllers.ControladorReserva;
import ennumerations.Extra;
import ennumerations.PreferenciaContacto;
import ennumerations.TipoHabitacion;
import interfaces.FormaPago;
import model.Cliente;
import model.Factura;
import model.Habitacion;
import model.Reserva;
import model.TarjetaCredito;


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
        System.out.println(controladorCliente.getListaClientes().get(0).getDni());
    }


    //Cargar habitaciones y disponibilidad.
    @Test
    public void testPublicarHabitacion() {
        ControladorHabitacion controladorHabitacion = ControladorHabitacion.getInstancia();
        List<Extra> extras = new ArrayList<>();
        extras.add(Extra.DESPERTADOR);
        
        Habitacion habitacion = new Habitacion(2, TipoHabitacion.DOBLE, extras, 100.0, 1);
        controladorHabitacion.publicarHabitacion(habitacion);

        List<Habitacion> listaHabitaciones = controladorHabitacion.getListaHabitaciones();

        // Verificar que la habitación se haya agregado correctamente a la lista
        Assert.assertEquals(1, listaHabitaciones.size());
        Assert.assertEquals(habitacion, listaHabitaciones.get(0));
        System.out.println(controladorHabitacion.getListaHabitaciones().get(0).getHabitacionID());
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
        for (Habitacion habitacion: habitacionesFiltradas) {
            System.out.println(habitacion.getHabitacionID());
        }
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
        for (Habitacion habitacion: habitacionesFiltradas) {
            System.out.println(habitacion.getHabitacionID());
        }
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
        for (Habitacion habitacion: habitacionesFiltradas) {
            System.out.println(habitacion.getHabitacionID());
        }
    }


    //Reservar y cancelar habitaciones presencialmente o vía web.
    @Test
    public void testReservarHabitacion() {
        ControladorReserva controladorReserva = ControladorReserva.getInstancia();
        ControladorHabitacion controladorHabitacion = ControladorHabitacion.getInstancia();
        ControladorCliente controladorCliente = ControladorCliente.getInstancia();

        List<Extra> extras = new ArrayList<>();
        Habitacion habitacion = new Habitacion(2, TipoHabitacion.INDIVIDUAL, extras, 100.0, 1);
        controladorHabitacion.publicarHabitacion(habitacion);

        Cliente cliente = new Cliente("Juan", "Perez", 123456, 11345678, "jperez@gmail.com", PreferenciaContacto.EMAIL);
        controladorCliente.altaCliente(cliente);

        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(3);
        Reserva reserva = new Reserva(1, checkIn, checkOut, LocalDate.now(), controladorCliente.getListaClientes().get(0), null, controladorHabitacion.getListaHabitaciones().get(0).getHabitacionID());

        

        controladorReserva.reservarHabitacion(reserva, habitacion);

        // Verificar que la reserva se haya agregado a la lista de reservas del controlador
        List<Reserva> listaReservas = controladorReserva.getListaReservas();
        Assert.assertEquals(1, listaReservas.size());
        Assert.assertTrue(listaReservas.contains(reserva));

        // Verificar que la habitación tenga la reserva agregada en sus fechas ocupadas
        List<LocalDate> reservasHabitacion = controladorHabitacion.getListaHabitaciones().get(0).getReservas();
        for (LocalDate ocupcion:reservasHabitacion) {
            System.out.println(ocupcion);
        }
        Assert.assertEquals(3, reservasHabitacion.size());
        Assert.assertTrue(reservasHabitacion.contains(checkIn));
        Assert.assertTrue(reservasHabitacion.contains(checkOut));
    }

     @Test
    public void testReservarHabitacionOcupada() {
        ControladorReserva controladorReserva = ControladorReserva.getInstancia();
        ControladorHabitacion controladorHabitacion = ControladorHabitacion.getInstancia();
        ControladorCliente controladorCliente = ControladorCliente.getInstancia();

        List<Extra> extras = new ArrayList<>();
        Habitacion habitacion = new Habitacion(2, TipoHabitacion.INDIVIDUAL, extras, 100.0, 1);
        controladorHabitacion.publicarHabitacion(habitacion);

        Cliente cliente = new Cliente("Juan", "Perez", 123456, 11345678, "jperez@gmail.com", PreferenciaContacto.EMAIL);
        controladorCliente.altaCliente(cliente);

        Reserva reserva = new Reserva(1, LocalDate.of(2023, 7, 10), LocalDate.of(2023, 7, 15), LocalDate.now(), controladorCliente.getListaClientes().get(0), null, controladorHabitacion.getListaHabitaciones().get(0).getHabitacionID());
        controladorReserva.reservarHabitacion(reserva, controladorHabitacion.getListaHabitaciones().get(0));
        Reserva reserva2 = new Reserva(2, LocalDate.of(2023, 7, 12), LocalDate.of(2023, 7, 14), LocalDate.now(), controladorCliente.getListaClientes().get(0), null, controladorHabitacion.getListaHabitaciones().get(0).getHabitacionID());
        controladorReserva.reservarHabitacion(reserva2, controladorHabitacion.getListaHabitaciones().get(0));

    }


    @Test
    public void testCancelarReserva() {
        ControladorReserva controladorReserva = ControladorReserva.getInstancia();
        ControladorHabitacion controladorHabitacion = ControladorHabitacion.getInstancia();
        ControladorCliente controladorCliente = ControladorCliente.getInstancia();

        List<Extra> extras = new ArrayList<>();
        Habitacion habitacion = new Habitacion(2, TipoHabitacion.INDIVIDUAL, extras, 100.0, 1);
        controladorHabitacion.publicarHabitacion(habitacion);

        Cliente cliente = new Cliente("Juan", "Perez", 123456, 11345678, "jperez@gmail.com", PreferenciaContacto.EMAIL);
        controladorCliente.altaCliente(cliente);

        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(3);
        Reserva reserva = new Reserva(1, checkIn, checkOut, LocalDate.now(), cliente, null, habitacion.getHabitacionID());

        controladorReserva.reservarHabitacion(reserva, controladorHabitacion.getListaHabitaciones().get(0));
        controladorReserva.cancelarReserva(controladorHabitacion.getListaHabitaciones().get(0), controladorReserva.getListaReservas().get(0));
        
        // Verificar que la habitación no tenga las fechas canceladas
        List<LocalDate> reservasHabitacion = controladorHabitacion.getListaHabitaciones().get(0).getReservas();
        for (LocalDate ocupcion:reservasHabitacion) {
            System.out.println(ocupcion);
        }

    }


    //Actualizar parámetros de facturación (plazo en días y porcentaje de variación).
    @Test
    public void testModificarParametros() {
        ControladorReserva controladorReserva = ControladorReserva.getInstancia();
        ControladorCliente controladorCliente = ControladorCliente.getInstancia();
        ControladorHabitacion controladorHabitacion = ControladorHabitacion.getInstancia();
        
        Habitacion habitacion = new Habitacion(2, TipoHabitacion.INDIVIDUAL, new ArrayList<>(), 100.0, 1);
        controladorHabitacion.publicarHabitacion(habitacion);
        Cliente cliente = new Cliente("Juan", "Perez", 123456, 11345678, "jperez@gmail.com", PreferenciaContacto.EMAIL);
        controladorCliente.altaCliente(cliente);
        Reserva reserva = new Reserva(1, LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 25), LocalDate.of(2023, 6, 5), cliente, null, 1);
        controladorReserva.reservarHabitacion(reserva, controladorHabitacion.getListaHabitaciones().get(0));
        
        FormaPago tarjetaCredito = new TarjetaCredito();
        
        //Modifico parametros del descuento y veo los cambios en el precio
        controladorReserva.modificarDescuento(reserva, 15, .5, 60, -0.2);
        controladorReserva.pagarReserva(controladorReserva.getListaReservas().get(0), tarjetaCredito);


    }
    
    //Enviar facturas y notificaciones a clientes.
    @Test
    public void testEnviarFacturaNotificacion() {
        ControladorFactura controladorFactura = ControladorFactura.getInstancia();
        ControladorReserva controladorReserva = ControladorReserva.getInstancia();
        ControladorCliente controladorCliente = ControladorCliente.getInstancia();
        ControladorHabitacion controladorHabitacion = ControladorHabitacion.getInstancia();
        
        Habitacion habitacion = new Habitacion(2, TipoHabitacion.INDIVIDUAL, new ArrayList<>(), 100.0, 1);
        controladorHabitacion.publicarHabitacion(habitacion);

        Cliente cliente = new Cliente("Juan", "Perez", 123456, 11345678, "jperez@gmail.com", PreferenciaContacto.EMAIL);
        controladorCliente.altaCliente(cliente);
        Reserva reserva = new Reserva(1, LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 25), LocalDate.of(2023, 6, 5), cliente, null, 1);
        controladorReserva.reservarHabitacion(reserva, controladorHabitacion.getListaHabitaciones().get(0));
        
        FormaPago tarjetaCredito = new TarjetaCredito();
        controladorReserva.pagarReserva(controladorReserva.getListaReservas().get(0), tarjetaCredito);
        controladorFactura.generarFactura(controladorReserva.getListaReservas().get(0));
        
        Factura factura = controladorFactura.getListaFacturas().get(0);
        controladorFactura.enviarFactura(controladorCliente.getListaClientes().get(0), controladorFactura.getListaFacturas().get(0));

        // Comprobar que la factura se genero correctamente
        Assert.assertEquals(factura, controladorFactura.getListaFacturas().get(0));
        assertTrue(true);
    }

    @Test
    public void testGenerarReporte() {
        ControladorReserva controladorReserva = ControladorReserva.getInstancia();
        ControladorCliente controladorCliente = ControladorCliente.getInstancia();
        ControladorHabitacion controladorHabitacion = ControladorHabitacion.getInstancia();
        ControladorReportes controladorReportes = ControladorReportes.getInstancia();
        
        Habitacion habitacion = new Habitacion(2, TipoHabitacion.INDIVIDUAL, new ArrayList<>(), 100.0, 1);
        controladorHabitacion.publicarHabitacion(habitacion);
        Cliente cliente = new Cliente("Juan", "Perez", 123456, 11345678, "jperez@gmail.com", PreferenciaContacto.EMAIL);
        controladorCliente.altaCliente(cliente);
        Reserva reserva = new Reserva(1, LocalDate.of(2023, 6, 20), LocalDate.of(2023, 6, 25), LocalDate.of(2023, 6, 5), cliente, null, 1);
        controladorReserva.reservarHabitacion(reserva, controladorHabitacion.getListaHabitaciones().get(0));

        controladorReportes.generarReporte(controladorHabitacion.getListaHabitaciones());
        controladorReportes.getListaReportes().get(0).imprimir();
    }
}


