package es.uab.tqs.mastermind;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.CodiSecret;
import es.uab.tqs.mastermind.model.MasterMindModel;
import es.uab.tqs.mastermind.model.MockAleatori;
import es.uab.tqs.mastermind.model.MockAleatoriConfiguracio;
import es.uab.tqs.mastermind.vista.MockMasterMindVista;
import es.uab.tqs.mastermind.controlador.MasterMindControlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMasterMindControlador {
    
MasterMindModel model;
    MockMasterMindVista vistaMock; // Vista mock
    MasterMindControlador controlador;
    MockAleatori aleatoriMock;

    @BeforeEach
	void setUp() {
        aleatoriMock = new MockAleatori(); // Codi fixe: 1,2,2,1
        model = new MasterMindModel(aleatoriMock);
        vistaMock = new MockMasterMindVista();
        controlador = new MasterMindControlador(model, vistaMock);

	}

    @Test
    void testIniciarPartida()
    {
        vistaMock.setConfiguracioInicial(4, 1);
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1));

        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratGuanyar, "Debe ganar");
        assertFalse(vistaMock.haMostratPerdre);
        assertEquals(1, model.getIntentsFets());

        /*
        Aleatori aleatori = new MockAleatori();
        model = new MasterMindModel(aleatori););
        controlador = new MasterMindControlador(vista, model);
        controlador.iniciarPartida();

        //comprovem que agafa bé valors de la vista
        assertEquals(model.getIntentsMax(), 5);
        assertEquals(model.getCodi().getLen(), 4);
        */


    }
}
