package es.uab.tqs.mastermind;
import org.junit.jupiter.api.Test;

import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.CodiSecret;
import es.uab.tqs.mastermind.model.VerificaIntent;
import es.uab.tqs.mastermind.model.MockAleatoriConfiguracio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

class TestVerificaIntent {
    CodiSecret codi;
	Aleatori aleatori;
	
	@BeforeEach
	void setUp() {
		Aleatori aleatori =  new MockAleatoriConfiguracio();
		codi = new CodiSecret(aleatori, 4);
	}

	@Test
	void testVerificaIntent() {
	//valor correcte
		//generem primer codi aleatori 1,3,2,3
		
		codi.generarCodi();
		VerificaIntent verifica = new VerificaIntent(codi);
		//correcte amb totes pos iguals
		assertEquals(Arrays.asList(1,1,1,1), verifica.getArrayPosicions(Arrays.asList(1,3,2,3)));
		//primera pos incorrecta
		assertEquals(Arrays.asList(0,1,1,1), verifica.getArrayPosicions(Arrays.asList(4,3,2,3)));
		//segona pos incorrecta
		assertEquals(Arrays.asList(1,0,1,1), verifica.getArrayPosicions(Arrays.asList(1,5,2,3)));
		assertEquals(Arrays.asList(1,1,0,1), verifica.getArrayPosicions(Arrays.asList(1,3,6,3)));
		assertEquals(Arrays.asList(1,1,1,0), verifica.getArrayPosicions(Arrays.asList(1,3,2,8)));
		
		//dos pos incorrectes:
		assertEquals(Arrays.asList(0,0,1,1), verifica.getArrayPosicions(Arrays.asList(6,5,2,3)));
		assertEquals(Arrays.asList(1,0,0,1), verifica.getArrayPosicions(Arrays.asList(1,5,7,3)));
		assertEquals(Arrays.asList(1,1,0,0), verifica.getArrayPosicions(Arrays.asList(1,3,8,8)));
		assertEquals(Arrays.asList(0,1,0,1), verifica.getArrayPosicions(Arrays.asList(4,3,6,3)));
		assertEquals(Arrays.asList(1,0,1,0), verifica.getArrayPosicions(Arrays.asList(1,9,2,4)));
	
		
		//tres pos incorrectes
		assertEquals(Arrays.asList(0,0,0,1), verifica.getArrayPosicions(Arrays.asList(7,6,5,3)));
		assertEquals(Arrays.asList(1,0,0,0), verifica.getArrayPosicions(Arrays.asList(1,5,7,8)));
		assertEquals(Arrays.asList(0,1,0,0), verifica.getArrayPosicions(Arrays.asList(4,3,5,8)));
		assertEquals(Arrays.asList(0,0,1,0), verifica.getArrayPosicions(Arrays.asList(5,6,2,4)));	
		
		//tot incorrecte
		//tres pos incorrectes
		assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(7,6,5,4)));
	
		//una pos correcta dos no una casi
		assertEquals(Arrays.asList(0,2,1,0), verifica.getArrayPosicions(Arrays.asList(4,1,2,9)));
		
		//dos pos correctes
		assertEquals(Arrays.asList(2,2,1,0), verifica.getArrayPosicions(Arrays.asList(3,1,2,9)));
		
		//valor fuera de rango
		try
		{
		assertEquals(Arrays.asList(2,2,1,0), verifica.getArrayPosicions(Arrays.asList(-1,1,2,4)));
		assertTrue(false);
		}catch(Error e) {}
		try
		{
		assertEquals(Arrays.asList(2,2,1,0), verifica.getArrayPosicions(Arrays.asList(-1,1,10,4)));
		assertTrue(false);
		} catch (Error e) {}
	}

	
	
	void testGetArrayPosicions() {
		fail("Not yet implemented");
	}
}
