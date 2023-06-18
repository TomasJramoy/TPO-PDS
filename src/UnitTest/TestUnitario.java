package UnitTest;


import org.junit.Assert;
import org.junit.Test;

import controllers.ControladorCliente;

import ennumerations.PreferenciaContacto;

import model.Cliente;



public class TestUnitario {
    @Test
    public void cargarCliente() {
        Cliente cliente = new Cliente("Juan", "Perez", 123456, 11345678, "jperez@gmail.com", PreferenciaContacto.EMAIL);
        ControladorCliente controladorCliente = ControladorCliente.getInstancia();

        controladorCliente.altaCliente(cliente);

        Cliente clienteCargado = controladorCliente.getListaClientes().get(0);
        Assert.assertNotNull(clienteCargado);

        Assert.assertEquals("Juan", clienteCargado.getNombre());
        Assert.assertEquals("Perez", clienteCargado.getApellido());
        Assert.assertEquals("jperez@gmail.com", clienteCargado.getEmail());
        Assert.assertEquals(PreferenciaContacto.EMAIL, clienteCargado.getPreferenciaContacto());
    }

}


