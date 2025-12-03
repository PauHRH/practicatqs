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

		/*
			Particions equivalents per longCodi	
		*/
		// Valors fora de rang cap als negatius (< 2)
		try
		{
			m1.novaPartida(-5, 5);
			assertTrue(false);
		} catch(Exception e) {}
		try
		{
			m1.novaPartida(-1, 5);
			assertTrue(false);
		} catch(Exception e) {}
		// Valor límit (1)
		try
		{
			m1.novaPartida(1,5);
			assertTrue(false);
		} catch(Exception e) {}
		// Valor frontera (2)
		m1.novaPartida(2,5);
		assertEquals(2,m1.getCodi().getLen());
		// Valor límit (3)
		m1.novaPartida(3,5);
		assertEquals(3,m1.getCodi().getLen());
		// Valors intermitjos (4)
		m1.novaPartida(4,5);
		assertEquals(4,m1.getCodi().getLen());
		// Valor límit (5)
		m1.novaPartida(5,5);
		assertEquals(5,m1.getCodi().getLen());
		// Valor frontera (6)
		m1.novaPartida(6,5);
		assertEquals(6,m1.getCodi().getLen());
		// Valor límit (7)
		try
		{
			m1.novaPartida(7, 5);
			assertTrue(false);
		} catch(Exception e) {}
		// Valors fora de rang cap als positius (> 6)
		try
		{
			m1.novaPartida(10, 5);
			assertTrue(false);
		} catch(Exception e) {}
		
		/*
			Particions equivalents per intentsMax	
		*/
		// Valors fora de rang cap als negatius (< 1)
		try
		{
			m1.novaPartida(5, -1);
			assertTrue(false);
		} catch(Exception e) {}
		try
		{
			m1.novaPartida(5, -5);
			assertTrue(false);
		} catch(Exception e) {}
		// Valor límit (0)
		try
		{
			m1.novaPartida(5,0);
			assertTrue(false);
		} catch(Exception e) {}
		// Valor frontera (1)
		m1.novaPartida(5,1);
		assertEquals(1,m1.getIntentsMax());
		// Valor límit (2)
		m1.novaPartida(5,2);
		assertEquals(2,m1.getIntentsMax());
		// Valors intermitjos (4)
		m1.novaPartida(5,4);
		assertEquals(4,m1.getIntentsMax());
		// Valor límit (9)
		m1.novaPartida(5,9);
		assertEquals(9,m1.getIntentsMax());
		// Valor frontera (10)
		m1.novaPartida(5,10);
		assertEquals(10,m1.getIntentsMax());
		// Valor límit (11)
		try
		{
			m1.novaPartida(5, 11);
			assertTrue(false);
		} catch(Exception e) {}
		// Valors fora de rang cap als positius (> 10)
		try
		{
			m1.novaPartida(5, 15);
			assertTrue(false);
		} catch(Exception e) {}

		/*
			Particio reinici de partida
		*/
		m1.novaPartida(4, 6);
		assertEquals(0, m1.getIntentsFets());
        assertFalse(m1.getHaGuanyat());
	}
	
	@Test
	void testFerJugada() {
		Aleatori alt = new MockAleatori();
		MasterMindModel m1 = new MasterMindModel(4,5,alt);
		
		/*
			Particions equivalents
		*/
		//Ha guanyat amb num max intents 5, suma 1 a intents fets 0
		assertTrue(m1.ferJugada(Arrays.asList(1,2,2,1)));
		assertEquals(1, m1.getIntentsFets());
		assertTrue(m1.getHaGuanyat());
		
		//Ha guanyat amb num intents max 5, intentsfets 4 canvia a 5 però guanya igual
		MasterMindModel m2 = new MasterMindModel(4,5,alt);
		m2.setIntentsFets(4);
		assertTrue(m2.ferJugada(Arrays.asList(1,2,2,1)));
		assertEquals(5, m2.getIntentsFets());
		assertTrue(m2.getHaGuanyat());
		
		//Intenta guanyar amb 5 intents, INTENTS fets 5 ERROR
		MasterMindModel m3 = new MasterMindModel(4,5,alt);
		m3.setIntentsFets(5);
		try {
			m3.ferJugada(Arrays.asList(1,2,2,1));
			assertTrue(false);
		} catch (Exception e) {}
		
		//Intenta guanyar amb valor SUPERIORS de INTENTS fets a MAX
		MasterMindModel m4 = new MasterMindModel(4,4,alt);
		m4.setIntentsFets(6);
		try {
			m4.ferJugada(Arrays.asList(1,2,2,1));
			assertTrue(false);
		} catch (Exception e) {}
		
		
		MasterMindModel m5 = new MasterMindModel(4,4,alt);
		m5.setIntentsFets(10);
		try {
			m5.ferJugada(Arrays.asList(1,2,2,1));
			assertTrue(false);
		} catch (Exception e) {}
		
		//Perd una ronda, no l'ha esbrinat però segueix el joc
		MasterMindModel m6 = new MasterMindModel(4,5,alt);
		assertFalse(m6.ferJugada(Arrays.asList(1,2,2,0)));
		assertEquals(1, m6.getIntentsFets());
		assertFalse(m6.getHaGuanyat());
		
		//Perd una ronda, però s'acaba el joc
		MasterMindModel m7 = new MasterMindModel(4,5,alt);
		m7.setIntentsFets(4);
		assertTrue(m7.ferJugada(Arrays.asList(1,2,2,0)));
		assertEquals(5, m7.getIntentsFets());
		assertFalse(m7.getHaGuanyat());
		
		//Intenta fer una ronda perduda però sense intents
		MasterMindModel m8 = new MasterMindModel(4,5,alt);
		m8.setIntentsFets(5);
		try {
			m8.ferJugada(Arrays.asList(1,2,2,0));
			assertTrue(false);
		} catch(Exception e) {}
		
		// Longitud array introduïda no coincideix amb la mida del codiSecret
		MasterMindModel m10 = new MasterMindModel(4,5,alt);
		try {
			m10.ferJugada(Arrays.asList(1,2,2));
			assertTrue(false);
		} catch(Exception e) {}
		
		try {
			m10.ferJugada(Arrays.asList(1,2,2,6,7));
			assertTrue(false);
		} catch(Exception e) {}	
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
		}catch(Exception e) {}
		try
		{
		m1.getArrayCorrectes(Arrays.asList(-1,1,10,4));
		assertTrue(false);
		} catch (Exception e) {}
		
		//llista amb valors menors a codi
		try
		{
		m1.getArrayCorrectes(Arrays.asList(1,1,10));
		assertTrue(false);
		} catch (Exception e) {}
	}

}
