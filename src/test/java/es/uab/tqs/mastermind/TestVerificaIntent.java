package es.uab.tqs.mastermind;
import org.junit.jupiter.api.Test;

import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.CodiSecret;
import es.uab.tqs.mastermind.model.VerificaIntent;
import es.uab.tqs.mastermind.model.MockAleatoriVerificaIntent;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

class TestVerificaIntent {
    CodiSecret codi;
	CodiSecret codi2;
	VerificaIntent verifica;
	VerificaIntent verifica2;
	
	@BeforeEach
	void setUp() {
		// primer codi --> [1,3,2,3]
		Aleatori aleatori =  new MockAleatoriVerificaIntent();
		codi = new CodiSecret(aleatori, 4);
		codi.generarCodi();
		verifica = new VerificaIntent(codi);
		
		// segon codi --> [1,2,3,4]
		Aleatori aleatori2 = new MockAleatoriVerificaIntent();
		codi2 = new CodiSecret(aleatori2, 4);
		codi2.generarCodi();
		verifica2 = new VerificaIntent(codi2);
	}

	@Test
	void testVerificaIntent() {
		/*
		 * Particio segons la validesa de l’entrada
		 */
		// llista de longitud correcta
		assertEquals(Arrays.asList(1,2,0,0), verifica.getArrayPosicions(Arrays.asList(1,2,4,8)));
		// llista massa curta
		try {
			assertEquals(Arrays.asList(1,2,0), verifica.getArrayPosicions(Arrays.asList(1,2,4)));
			assertTrue(false);
		} catch(Error e) {}
		// llista massa llarga
		try {
			assertEquals(Arrays.asList(0,2,1,0), verifica.getArrayPosicions(Arrays.asList(7,1,2,4,5)));
			assertTrue(false);
		} catch(Error e) {}
		
		/*
		 * Particio segons la validesa dels valors
		 */
		// tots els valors dins rang 0–9
		assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(0,5,6,9)));
		//valors fora de rang < 0
		try
		{
		assertEquals(Arrays.asList(2,2,1,0), verifica.getArrayPosicions(Arrays.asList(-1,1,2,4)));
		assertTrue(false);
		}catch(Error e) {}
		// valors fora de rang > 9
		try
		{
		assertEquals(Arrays.asList(2,2,1,0), verifica.getArrayPosicions(Arrays.asList(1,1,10,4)));
		assertTrue(false);
		} catch (Error e) {}
		
		/*
		 * Particions segons relació posicional entre codi secret i intent
		 */
		// tots els números correctes i a la posició correcta
		assertEquals(Arrays.asList(1,1,1,1), verifica.getArrayPosicions(Arrays.asList(1,3,2,3)));
		// tots incorrectes
		assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(0,6,5,4)));
		// exactament 1 correcte
		assertEquals(Arrays.asList(0,0,0,1), verifica.getArrayPosicions(Arrays.asList(7,6,5,3)));
		assertEquals(Arrays.asList(1,0,0,0), verifica.getArrayPosicions(Arrays.asList(1,5,7,8)));
		assertEquals(Arrays.asList(0,1,0,0), verifica.getArrayPosicions(Arrays.asList(4,3,5,8)));
		assertEquals(Arrays.asList(0,0,1,0), verifica.getArrayPosicions(Arrays.asList(5,6,2,4)));
		// exactament 2 correctes
		assertEquals(Arrays.asList(0,0,1,1), verifica.getArrayPosicions(Arrays.asList(6,5,2,3)));
		assertEquals(Arrays.asList(1,0,0,1), verifica.getArrayPosicions(Arrays.asList(1,5,7,3)));
		assertEquals(Arrays.asList(1,1,0,0), verifica.getArrayPosicions(Arrays.asList(1,3,8,8)));
		assertEquals(Arrays.asList(0,1,0,1), verifica.getArrayPosicions(Arrays.asList(4,3,6,3)));
		assertEquals(Arrays.asList(1,0,1,0), verifica.getArrayPosicions(Arrays.asList(1,9,2,4)));
		// exactament 3 correctes
		assertEquals(Arrays.asList(0,1,1,1), verifica.getArrayPosicions(Arrays.asList(4,3,2,3)));
		assertEquals(Arrays.asList(1,0,1,1), verifica.getArrayPosicions(Arrays.asList(1,5,2,3)));
		assertEquals(Arrays.asList(1,1,0,1), verifica.getArrayPosicions(Arrays.asList(1,3,6,3)));
		assertEquals(Arrays.asList(1,1,1,0), verifica.getArrayPosicions(Arrays.asList(1,3,2,8)));
		// tots correctes però en mala posició (només "2")
		assertEquals(Arrays.asList(2,2,2,2), verifica.getArrayPosicions(Arrays.asList(3,1,3,2)));
		// barreja de correctes (1), semi-correctes (2) i incorrectes (0)
		assertEquals(Arrays.asList(0,2,1,0), verifica.getArrayPosicions(Arrays.asList(4,1,2,9)));
		assertEquals(Arrays.asList(2,2,1,0), verifica.getArrayPosicions(Arrays.asList(3,1,2,9)));
		
		/*
		 * Particions per repeticions en els valors
		 */
		// repeticions només al codi secret
		assertEquals(Arrays.asList(1,2,2,0), verifica.getArrayPosicions(Arrays.asList(1,2,3,4)));
		// repeticions només al codi introduït (utilitzem el codi [1,2,3,4])
		assertEquals(Arrays.asList(0,0,1,0), verifica2.getArrayPosicions(Arrays.asList(3,3,3,3)));
		// repeticions en ambdós
		assertEquals(Arrays.asList(1,1,2,0), verifica.getArrayPosicions(Arrays.asList(1,3,3,5)));
		// repeticions que no corresponen al secret
		assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(4,4,4,4)));
		
		/*
		 * Decision i Condition Coverage del rang 
		 * if (valor < 0 || valor > 9)
		 */
		// Cas valor < 0
		try {
            verifica.getArrayPosicions(Arrays.asList(-5,3,2,1));
            assertTrue(false);
        } catch (Error e) {}
		// // Cas valor > 9
        try {
            verifica.getArrayPosicions(Arrays.asList(1,3,12,1));
            assertTrue(false);
        } catch (Error e) {}
        // Cas valor dins rang --> condició falsa, passa
        assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(0,9,0,9)));
		
        /*
         * Decision Coverage del segon if
         * if (secret[i] == intent[i])
         */
        // Cas on entra al if (trobem un 1)
        assertEquals(Arrays.asList(1,0,0,0), verifica.getArrayPosicions(Arrays.asList(1,9,9,9)));
        // Cas on no entra al if
        assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(9,9,9,9)));
        
        /*
         * Decision Coverage del tercer if
         * if(verificador[i] == 0)
         */
        // Cas verificador[i] == 0
        assertEquals(Arrays.asList(2,0,0,0), verifica.getArrayPosicions(Arrays.asList(3,9,9,9)));
        // Cas verificador[i] != 0 (no s'entra al segon if)
        assertEquals(Arrays.asList(1,0,0,0), verifica.getArrayPosicions(Arrays.asList(1,9,9,9)));
        
        /*
         * Decision Coverage del quart if
         * if (index != -1)
         */
        // Cas index != -1 (valor correcte però mala posició)
        assertEquals(Arrays.asList(2,0,0,0), verifica.getArrayPosicions(Arrays.asList(3,9,9,9)));
        // Cas index == -1 (valor no existeix)
        assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(8,8,8,8)));
        
		/*
		 * Path Coverage
		 */
        // P1 – Tot correcte
        assertEquals(Arrays.asList(1,1,1,1), verifica.getArrayPosicions(Arrays.asList(1,3,2,3)));
        // P2 – Tot incorrecte
        assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(9,8,7,6)));
        // P3 – Mix (0,2,1,0)
        assertEquals(Arrays.asList(0,2,1,0), verifica.getArrayPosicions(Arrays.asList(4,1,2,9)));
        // P4 – Repeticions al intent
        assertEquals(Arrays.asList(0,1,0,1), verifica.getArrayPosicions(Arrays.asList(3,3,3,3)));
	}

	
	
	void testGetArrayPosicions() {
		fail("Not yet implemented");
	}
}
