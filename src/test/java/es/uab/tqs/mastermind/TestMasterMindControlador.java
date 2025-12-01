package es.uab.tqs.mastermind;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
        vistaMock.setIndexIntent(0);

        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratGuanyar, "Debe ganar");
        assertFalse(vistaMock.haMostratPerdre);
        assertEquals(1, model.getIntentsFets());

        // Intents=1, Resultat=Derrota (falla la unica vida)
        vistaMock.setConfiguracioInicial(4, 1);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); // Incorrecto
        vistaMock.setIndexIntent(0);
        controlador.iniciarPartida();

        assertFalse(vistaMock.haMostratGuanyar);
        assertTrue(vistaMock.haMostratPerdre, "Debe perder por max intentos");
        assertEquals(1, model.getIntentsFets());


        // Intents=5, Resultat=Victoria (guanya en intent intermedi)
        vistaMock.setConfiguracioInicial(4, 5);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); // Fallo
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1)); // Acierto
        vistaMock.setIndexIntent(0);
        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratGuanyar);
        assertEquals(2, model.getIntentsFets());


        // Intents=2, Resultat=Derrota (esgota tots los intents)
        vistaMock.setConfiguracioInicial(4, 2);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); 
        vistaMock.afegirIntentUsuari(Arrays.asList(9, 9, 9, 9)); 
        vistaMock.setIndexIntent(0);
        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratPerdre);
        assertEquals(2, model.getIntentsFets());


    }

    
    private void inserirHistorial(List<List<Integer>> historialSimulat) {
        if (controlador == null) {
             controlador = new MasterMindControlador(new MasterMindModel(new MockAleatori()), new MockMasterMindVista());
        }
        if (vistaMock == null) {
              vistaMock = new MockMasterMindVista();
        }

        for (List<Integer> historial : historialSimulat) {
              controlador.afegirHistorialIntents(historial);
        }
    }

    @Test
    void testEsIntentRepetit()
    {
        
        /*
        Particions equivalents
         */
        //cas 1: llista historial buida, no ha de fallar
        inserirHistorial(new ArrayList<>());
        List<Integer> intent0 = Arrays.asList(1, 2, 3, 4);
        assertFalse(controlador.esIntentRepetit(intent0));

        //cas 2: intent amb número NO repetit a historial
        List<List<Integer>> lista1 = new ArrayList();
        lista1.add(Arrays.asList(1, 1, 1, 1));
        lista1.add(Arrays.asList(2,2,2,2));
        inserirHistorial(lista1);
        // Intento nuevo
        List<Integer> intent1 = Arrays.asList(3, 3, 3, 3);

        assertFalse(controlador.esIntentRepetit(intent1));

        //cas 3: intent amb número SÍ repetit a historial
        //aprofitem historial de cas 2
        List<Integer> intent2 = Arrays.asList(2,2,2,2);
        assertTrue(controlador.esIntentRepetit(intent2));

        //cas 4: comprovem primer de historial
        List<Integer> intent3 = Arrays.asList(1,1,1,1);
        assertTrue(controlador.esIntentRepetit(intent3));

        //cas 5: comprovem ultim de historial
        List<Integer> intent4 = Arrays.asList(2,2,2,2);
        assertTrue(controlador.esIntentRepetit(intent4));

        //cas 6: cas entre mig. Comprovem que amb diversos numeros no es confon
        List<Integer> intent5 = Arrays.asList(1,2,3,5);
        assertTrue(controlador.esIntentRepetit(intent5));
    }
}
