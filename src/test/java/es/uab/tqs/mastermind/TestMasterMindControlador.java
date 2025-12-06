package es.uab.tqs.mastermind;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.uab.tqs.mastermind.model.MasterMindModel;
import es.uab.tqs.mastermind.model.MockAleatoriCodiSecret;
import es.uab.tqs.mastermind.vista.MockMasterMindVista;
import es.uab.tqs.mastermind.controlador.MasterMindControlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMasterMindControlador {
    
MasterMindModel model;
    MockMasterMindVista vistaMock; // Vista mock
    MasterMindControlador controlador;
    MockAleatoriCodiSecret aleatoriMock;

    @BeforeEach
	void setUp() {
        aleatoriMock = new MockAleatoriCodiSecret(); // Codi fixe: 1,2,2,1
        model = new MasterMindModel(aleatoriMock);
        vistaMock = new MockMasterMindVista();
        controlador = new MasterMindControlador(model, vistaMock);
	}

    @Test
    void testIniciarPartida()
    {
        /*
            Particions equivalents
        */

        // Guanya a la primera ronda amb més intents restants
        vistaMock.setConfiguracioInicial(4, 5);
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1));
        vistaMock.setIndexIntent(0);

        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratGuanyar);
        assertFalse(vistaMock.haMostratPerdre);
        assertEquals(1, model.getIntentsFets());

        // Perd a la primera ronda amb un únic intent
        vistaMock.setConfiguracioInicial(4, 1);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); 
        vistaMock.setIndexIntent(0);
        controlador.iniciarPartida();

        assertFalse(vistaMock.haMostratGuanyar);
        assertTrue(vistaMock.haMostratPerdre);
        assertEquals(1, model.getIntentsFets());


        // Guanya en un intent intermedi
        vistaMock.setConfiguracioInicial(4, 5);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0));
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1));
        vistaMock.setIndexIntent(0);
        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratGuanyar);
        assertEquals(2, model.getIntentsFets());


        // Perd després de múltiples intents
        vistaMock.setConfiguracioInicial(4, 2);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); 
        vistaMock.afegirIntentUsuari(Arrays.asList(9, 9, 9, 9)); 
        vistaMock.setIndexIntent(0);
        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratPerdre);
        assertEquals(2, model.getIntentsFets());

        // Guanya utilitzant el nombre màxim d'intents
        vistaMock.setConfiguracioInicial(4, 2);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); 
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1)); 
        vistaMock.setIndexIntent(0);
        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratGuanyar);
        assertEquals(2, model.getIntentsFets());

        
        /*
            Statement coverage
         */

        // Intent repetit, no ha d'acceptar el segon valor (0,0,0,0) al ja haver
        // estat posat abans.
        vistaMock.setConfiguracioInicial(4, 4);
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); 
        vistaMock.afegirIntentUsuari(Arrays.asList(3, 2, 5, 1)); 
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0)); 
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1)); 
        vistaMock.setIndexIntent(0);
        controlador.iniciarPartida();

        assertTrue(vistaMock.haMostratGuanyar);
        assertEquals(3, model.getIntentsFets());


        /*
            Loop testing aniuat
            per loops de la funció

            Loop testing bucle intern
        */

        // Cas 1: 1 passada pel loop intern (Guanya a la primera)
        vistaMock = new MockMasterMindVista();
        vistaMock.setConfiguracioInicial(4, 5); 
        vistaMock.setSeguirJugant(false);
        
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1));
        
        controlador = new MasterMindControlador(model, vistaMock);
        controlador.iniciarPartida();
        
        assertTrue(vistaMock.haMostratGuanyar);
        assertEquals(1, model.getIntentsFets()); // 1 iteració


        // Cas 2: 2 passades pel loop intern (Falla 1a, Guanya 2a)
        vistaMock = new MockMasterMindVista();
        vistaMock.setConfiguracioInicial(4, 5);
        vistaMock.setSeguirJugant(false);
        
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0));
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1));
        
        controlador = new MasterMindControlador(model, vistaMock);
        controlador.iniciarPartida();
        
        assertTrue(vistaMock.haMostratGuanyar);
        assertEquals(2, model.getIntentsFets());


        // Cas 3: m passades pel loop intern (Ex: 3 intents de 5)
        vistaMock = new MockMasterMindVista();
        vistaMock.setConfiguracioInicial(4, 5);
        vistaMock.setSeguirJugant(false);
        
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0));
        vistaMock.afegirIntentUsuari(Arrays.asList(3, 4, 5, 6));
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 2, 1));
        
        controlador = new MasterMindControlador(model, vistaMock);
        controlador.iniciarPartida();
        
        assertEquals(3, model.getIntentsFets());


        // Cas 4: n passades (màxim) pel loop intern (Esgota intents)
        vistaMock = new MockMasterMindVista();
        vistaMock.setConfiguracioInicial(4, 4); 
        vistaMock.setSeguirJugant(false);
        
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 2, 3, 4));
        vistaMock.afegirIntentUsuari(Arrays.asList(0, 0, 0, 0));
        vistaMock.afegirIntentUsuari(Arrays.asList(3, 4, 5, 6));
        vistaMock.afegirIntentUsuari(Arrays.asList(1, 3, 2, 1));
        
        
        controlador = new MasterMindControlador(model, vistaMock);
        controlador.iniciarPartida();
        
        assertTrue(vistaMock.haMostratPerdre); // Confirmem que ha sortit per derrota
        assertEquals(4, model.getIntentsFets());

        /*
            Loop testing bucle extern
        */

        // Cas 1: 1 passada pel loop extern (Jugar una sola partida)
        vistaMock = new MockMasterMindVista();
        vistaMock.setConfiguracioInicial(4, 5);
        vistaMock.setSeguirJugant(false);
        vistaMock.afegirIntentUsuari(Arrays.asList(0,0,0,0));
        vistaMock.afegirIntentUsuari(Arrays.asList(1,2,2,1));
        
        controlador = new MasterMindControlador(model, vistaMock);
        controlador.iniciarPartida();

        // Cas 2: 2 passades pel loop extern (Jugar dues partides)
        vistaMock = new MockMasterMindVista();
        
        // Config Partida 1
        vistaMock.setConfiguracioInicial(4, 5);
        vistaMock.afegirIntentUsuari(Arrays.asList(0,0,0,0)); 
        vistaMock.afegirIntentUsuari(Arrays.asList(1,2,2,1));
        
        vistaMock.setSeguirJugant(true); 
        
        // Config Partida 2
        vistaMock.afegirConfiguracioExtra(4, 5);
        vistaMock.afegirIntentUsuari(Arrays.asList(0,0,0,0));
        vistaMock.afegirIntentUsuari(Arrays.asList(1,2,2,1));
        
        vistaMock.afegirRespostaSeguirJugant(false);

        controlador = new MasterMindControlador(model, vistaMock);
        controlador.iniciarPartida();
        
        
        
        // Cas 3: m passades pel loop extern (Jugar 3 partides)
        vistaMock = new MockMasterMindVista();
        // Partida 1
        vistaMock.setConfiguracioInicial(4, 5);
        vistaMock.afegirIntentUsuari(Arrays.asList(1,2,2,1));
        vistaMock.setSeguirJugant(true);
        
        // Partida 2
        vistaMock.afegirConfiguracioExtra(4, 5);
        vistaMock.afegirIntentUsuari(Arrays.asList(1,2,2,1));
        vistaMock.afegirRespostaSeguirJugant(true);
        
        // Partida 3
        vistaMock.afegirConfiguracioExtra(4, 5);
        vistaMock.afegirIntentUsuari(Arrays.asList(1,2,2,1));
        vistaMock.afegirRespostaSeguirJugant(false);

        controlador = new MasterMindControlador(model, vistaMock);
        controlador.iniciarPartida();


    }

    // S'utilitza per el test de EsIntentRepetit
    private void inserirHistorial(List<List<Integer>> historialSimulat) {
        if (controlador == null) {
             controlador = new MasterMindControlador(new MasterMindModel(new MockAleatoriCodiSecret()), new MockMasterMindVista());
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

        // Intent nou
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
        assertFalse(controlador.esIntentRepetit(intent5));

        //cas 7: cas entre mig. Comprovem amb valor entre mig després d'haver afegit 
        //altres que existeixi
        List<Integer> intent6 = Arrays.asList(2,2,2,2);
        assertTrue(controlador.esIntentRepetit(intent6));

       //cas 8: Passa per la condició que els arrays siguin diferents, torna a iterar bucle
        List<Integer> intent7 = Arrays.asList(2,2,2);
        controlador.esIntentRepetit(intent7);


        /*
            Loop testing

            Bucle intern:
         */
        List<Integer> intentBase = Arrays.asList(1, 2, 3, 4);
        List<List<Integer>> historialUnic = new ArrayList<>();
        historialUnic.add(intentBase);
        
        controlador = new MasterMindControlador(model, vistaMock);
        inserirHistorial(historialUnic);

        // Cas 1: 1 pasada pel loop intern
        assertFalse(controlador.esIntentRepetit(Arrays.asList(5, 2, 3, 4)));

        // Cas 2: 2 pasades pel loop intern
        assertFalse(controlador.esIntentRepetit(Arrays.asList(1, 5, 3, 4)));

        // Cas 3: m pasades pel loop intern
        assertFalse(controlador.esIntentRepetit(Arrays.asList(1, 2, 5, 4)));

        // Cas 4: n pasades (màxim) - mateix intent que intentBase [1,2,3,4]
        assertTrue(controlador.esIntentRepetit(intentBase));

        /*
            Loop extern
        */

        // Cas 1: 0 pasades pel loop extern (historial buit)
        controlador = new MasterMindControlador(model, vistaMock);
        assertFalse(controlador.esIntentRepetit(intentBase));

        // Cas 2: 1 pasada pel loop extern (historial.size() == 1)
        controlador = new MasterMindControlador(model, vistaMock);
        List<List<Integer>> h1 = new ArrayList<>();
        h1.add(Arrays.asList(9, 9, 9, 9));
        inserirHistorial(h1);
        
        // Ho provem amb un no repetit
        assertFalse(controlador.esIntentRepetit(intentBase));

        // Cas 3: 2 pasades pel loop extern (historial.size() == 2)
        controlador = new MasterMindControlador(model, vistaMock);
        List<List<Integer>> h2 = new ArrayList<>();
        h2.add(Arrays.asList(9, 9, 9, 9));
        h2.add(intentBase);
        inserirHistorial(h2);
        
        assertTrue(controlador.esIntentRepetit(intentBase));

        // Cas 4: m pasades pel loop extern (historial.size() == m (3 en aquest cas))
        controlador = new MasterMindControlador(model, vistaMock);
        List<List<Integer>> h3 = new ArrayList<>();
        h3.add(Arrays.asList(9, 9, 9, 9));
        h3.add(Arrays.asList(8, 8, 8, 8));
        h3.add(intentBase);
        inserirHistorial(h3);
        
        assertTrue(controlador.esIntentRepetit(intentBase));
        
        // Cas 5: n pasades pel loop extern (historial.size() == n) - No troba res al historial, acaba retornant fals
        controlador = new MasterMindControlador(model, vistaMock);
        inserirHistorial(h3);
        
        assertFalse(controlador.esIntentRepetit(Arrays.asList(0, 0, 0, 0)));

    }
}
