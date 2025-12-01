package es.uab.tqs.mastermind;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

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
        /*
        Pairwise testing #2
        */

        // Intents=1, Resultat=Victoria (guanya a la primera)
        vistaMock.setConfiguracioInicial(4, 1);
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1));

        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratGuanyar, "Debe ganar");
        assertFalse(vistaMock.haMostratPerdre);
        assertEquals(1, model.getIntentsFets());

        // Intents=1, Resultat=Derrota (falla la unica vida)
        vistaMock.setConfiguracioInicial(4, 1);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); // Incorrecto

        controlador.iniciarPartida();

        assertFalse(vistaMock.haMostratGuanyar);
        assertTrue(vistaMock.haMostratPerdre, "Debe perder por max intentos");
        assertEquals(1, model.getIntentsFets());


        // Intents=5, Resultat=Victoria (guanya en intent intermedi)
        vistaMock.setConfiguracioInicial(4, 5);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); // Fallo
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1)); // Acierto

        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratGuanyar);
        assertEquals(2, model.getIntentsFets());


        // Intents=2, Resultat=Derrota (esgota tots los intents)
        vistaMock.setConfiguracioInicial(4, 2);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); 
        vistaMock.afegirIntentUsuari(Arrays.asList(9, 9, 9, 9)); 

        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratPerdre);
        assertEquals(2, model.getIntentsFets());


    }

    @Test
    void testEsIntentRepetit()
    {

    }
}
