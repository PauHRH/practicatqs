package es.uab.tqs.mastermind;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.MasterMindModel;
import es.uab.tqs.mastermind.model.MockAleatori;
import es.uab.tqs.mastermind.model.MockAleatoriConfiguracio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMasterMindModel {

    @Test
	void testNovaPartida() {
		Aleatori alt = new MockAleatori();
		MasterMindModel m1 = new MasterMindModel(2,4,alt);
		//per longitud:
		//valors frontera:
		m1.novaPartida(2,5);
		assertEquals(2,m1.getCodi().getLen());
		
		m1.novaPartida(6,5);
		assertEquals(6,m1.getCodi().getLen());
		
		//valors límit
		try
		{
			m1.novaPartida(-1, 4);
			assertTrue(false);
		} catch(AssertionError e) {}
		
		try
		{
			m1.novaPartida(7, 4);
			assertTrue(false);
		} catch(AssertionError e) {}
		
		try
		{
			m1.novaPartida(1,5);
			assertTrue(false);
		} catch(AssertionError e) {}
		
		m1.novaPartida(5,5);
		assertEquals(5,m1.getCodi().getLen());
		
		//valor entre mig
		m1.novaPartida(4,5);
		assertEquals(4,m1.getCodi().getLen());
		
		try
		{
		    m1.novaPartida(10, 4);
			assertTrue(false);
		} catch(AssertionError e) {}
		
		try
		{
			m1.novaPartida(-5, 4);
			assertTrue(false);
		} catch(AssertionError e) {}
		
		
		//per MAX INTENTS
		//valors frontera: entre 1 i 10 intents
		MasterMindModel m2 = new MasterMindModel(5,4,alt);
		m2.novaPartida(4,1);
		assertEquals(1, m2.getIntentsMax());
		
		m2.novaPartida(4,10);
		assertEquals(10, m2.getIntentsMax());
		
		//valors límits
		m2.novaPartida(4,9);
		assertEquals(9, m2.getIntentsMax());
		
		m2.novaPartida(2,2);
		assertEquals(2, m2.getIntentsMax());

		try {
			m2.novaPartida(2,0);
			assertTrue(false);
		} catch(AssertionError e) {}
		
		try {
			m2.novaPartida(2,11);
			assertTrue(false);
		} catch(AssertionError e) {}
		
		//valor entre mig
		
		try {
			m2.novaPartida(2,15);
			assertTrue(false);
		} catch(AssertionError e) {}
		
		try {
			m2.novaPartida(2,-5);
			assertTrue(false);
		} catch(AssertionError e) {}
		
		m2.novaPartida(2,5);
		assertEquals(5, m2.getIntentsMax());
		
		assertEquals(0,m2.getIntentsFets());
	}
	
	@Test
	void testFerJugada() {
		
	}

	@Test
	void testGetArrayCorrectes() {
		//valor correcte
		//generem primer codi aleatori 1,3,2,3
		
		MasterMindModel m1 = new MasterMindModel(4,5,new MockAleatoriConfiguracio());

		//correcte amb totes pos iguals
		assertEquals(Arrays.asList(1,1,1,1), m1.getArrayCorrectes(Arrays.asList(1,3,2,3)));
		//primera pos incorrecta
		assertEquals(Arrays.asList(0,1,1,1), m1.getArrayCorrectes(Arrays.asList(4,3,2,3)));
		//segona pos incorrecta
		assertEquals(Arrays.asList(1,0,1,1), m1.getArrayCorrectes(Arrays.asList(1,5,2,3)));
		assertEquals(Arrays.asList(1,1,0,1), m1.getArrayCorrectes(Arrays.asList(1,3,6,3)));
		assertEquals(Arrays.asList(1,1,1,0), m1.getArrayCorrectes(Arrays.asList(1,3,2,8)));
		
		//dos pos incorrectes:
		assertEquals(Arrays.asList(0,0,1,1), m1.getArrayCorrectes(Arrays.asList(6,5,2,3)));
		assertEquals(Arrays.asList(1,0,0,1), m1.getArrayCorrectes(Arrays.asList(1,5,7,3)));
		assertEquals(Arrays.asList(1,1,0,0), m1.getArrayCorrectes(Arrays.asList(1,3,8,8)));
		assertEquals(Arrays.asList(0,1,0,1), m1.getArrayCorrectes(Arrays.asList(4,3,6,3)));
		assertEquals(Arrays.asList(1,0,1,0), m1.getArrayCorrectes(Arrays.asList(1,9,2,4)));
	
		
		//tres pos incorrectes
		assertEquals(Arrays.asList(0,0,0,1), m1.getArrayCorrectes(Arrays.asList(7,6,5,3)));
		assertEquals(Arrays.asList(1,0,0,0), m1.getArrayCorrectes(Arrays.asList(1,5,7,8)));
		assertEquals(Arrays.asList(0,1,0,0), m1.getArrayCorrectes(Arrays.asList(4,3,5,8)));
		assertEquals(Arrays.asList(0,0,1,0), m1.getArrayCorrectes(Arrays.asList(5,6,2,4)));	
		
		//tot incorrecte
		//tres pos incorrectes
		assertEquals(Arrays.asList(0,0,0,0), m1.getArrayCorrectes(Arrays.asList(7,6,5,4)));
	
		//una pos correcta dos no una casi
		assertEquals(Arrays.asList(0,2,1,0), m1.getArrayCorrectes(Arrays.asList(4,1,2,9)));
		
		//dos pos correctes
		assertEquals(Arrays.asList(2,2,1,0), m1.getArrayCorrectes(Arrays.asList(3,1,2,9)));
		
		//valor fuera de rango
		try
		{
		m1.getArrayCorrectes(Arrays.asList(-1,1,2,4));
		assertTrue(false);
		}catch(Error e) {}
		try
		{
		m1.getArrayCorrectes(Arrays.asList(-1,1,10,4));
		assertTrue(false);
		} catch (Error e) {}
		
		//lista amb valors menors a codi
		try
		{
		m1.getArrayCorrectes(Arrays.asList(1,1,10));
		assertTrue(false);
		} catch (AssertionError e) {}
		
	}

}
