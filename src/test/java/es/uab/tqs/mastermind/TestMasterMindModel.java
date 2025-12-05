package es.uab.tqs.mastermind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.MasterMindModel;
import es.uab.tqs.mastermind.model.MockAleatori;
import es.uab.tqs.mastermind.model.MockAleatoriConfiguracio;

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

		/*
		Condition i decision coverage testing:
		amb els casos verificats anteriorment ja es compleix el condition testing, per exemple en aquests casos:
		- longCodi < 2: 
			· true: en particions equivalents per longCodi on valors fora de rang cap als negatius (< 2)
			· false: en particions equivalents per longCodi on valors intermitjos (4)
		- longCodi > 6:
			· true: en particions equivalents per longCodi on valors fora de rang cap als positius (> 6)
			· false: en particions equivalents per longCodi on valors intermitjos (4)
		- intentsMax < 1:
			· true: en particions equivalents per intentsMax on valors fora de rang cap als negatius (< 1)
			· false: en particions equivalents per intentsMax on valors intermitjos (4)
		- intentsMax > 10:
			· true: en particions equivalents per intentsMax on valors fora de rang cap als positius (> 10)
			· false: en particions equivalents per intentsMax on valors intermitjos (4)
		*/
		
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

		/*
			Path Coverage Testing
		*/

		//Camí 1: 
		MasterMindModel m11 = new MasterMindModel(4,5,alt);
		try {
			m11.ferJugada(Arrays.asList(1,2,3));
			assertTrue(false);
		} catch(Exception e) {}

		//Camí 2: 
		//Intenta guanyar amb valor SUPERIORS de INTENTS fets a MAX
		MasterMindModel m12 = new MasterMindModel(4,5,alt);
		m12.setIntentsFets(7);
		try {
			m12.ferJugada(Arrays.asList(1,2,2,1));
			assertTrue(false);
		} catch (Exception e) {}

		//Camí 3:
		MasterMindModel m13 = new MasterMindModel(4,5,alt);
		assertTrue(m13.ferJugada(Arrays.asList(1,2,2,1)));
		assertEquals(1, m13.getIntentsFets());

		// Camí 4:
		MasterMindModel m14 = new MasterMindModel(4,5,alt);
		m14.setIntentsFets(4);
		assertFalse(m14.ferJugada(Arrays.asList(1,2,1,1)));

		// Camí 5:
		MasterMindModel m15 = new MasterMindModel(4,6,alt);
		m15.setIntentsFets(4);
		assertFalse(m15.ferJugada(Arrays.asList(1,2,1,1)));



	}

	@Test
	void testGetArrayCorrectes() {
		// Mock que genera: [1, 3, 2, 3]
		MasterMindModel m1 = new MasterMindModel(4,5,new MockAleatoriConfiguracio());

		/*
			Particio equivalent segons la resposta de verificaIntent
		*/
		// correcte amb totes pos iguals
		assertEquals(Arrays.asList(1,1,1,1), m1.getArrayCorrectes(Arrays.asList(1,3,2,3)));
		// primera pos incorrecta
		assertEquals(Arrays.asList(0,1,1,1), m1.getArrayCorrectes(Arrays.asList(4,3,2,3)));
		// segona pos incorrecta
		assertEquals(Arrays.asList(1,0,1,1), m1.getArrayCorrectes(Arrays.asList(1,5,2,3)));
		// tercera posicio incorrecta
		assertEquals(Arrays.asList(1,1,0,1), m1.getArrayCorrectes(Arrays.asList(1,3,6,3)));
		assertEquals(Arrays.asList(1,1,1,0), m1.getArrayCorrectes(Arrays.asList(1,3,2,8)));
		// dos posicions incorrectes
		assertEquals(Arrays.asList(0,0,1,1), m1.getArrayCorrectes(Arrays.asList(6,5,2,3)));
		assertEquals(Arrays.asList(1,0,0,1), m1.getArrayCorrectes(Arrays.asList(1,5,7,3)));
		assertEquals(Arrays.asList(1,1,0,0), m1.getArrayCorrectes(Arrays.asList(1,3,8,8)));
		assertEquals(Arrays.asList(0,1,0,1), m1.getArrayCorrectes(Arrays.asList(4,3,6,3)));
		assertEquals(Arrays.asList(1,0,1,0), m1.getArrayCorrectes(Arrays.asList(1,9,2,4)));
		// tres pos incorrectes
		assertEquals(Arrays.asList(1,0,0,0), m1.getArrayCorrectes(Arrays.asList(1,5,7,8)));
		assertEquals(Arrays.asList(0,1,0,0), m1.getArrayCorrectes(Arrays.asList(4,3,5,8)));
		assertEquals(Arrays.asList(0,0,1,0), m1.getArrayCorrectes(Arrays.asList(5,6,2,4)));	
		assertEquals(Arrays.asList(0,0,0,1), m1.getArrayCorrectes(Arrays.asList(7,6,5,3)));
		// tot incorrecte
		assertEquals(Arrays.asList(0,0,0,0), m1.getArrayCorrectes(Arrays.asList(7,6,5,4)));
		// barreja de correctes, incorrectes i fora de posicio
		assertEquals(Arrays.asList(0,2,1,0), m1.getArrayCorrectes(Arrays.asList(4,1,2,9)));
		assertEquals(Arrays.asList(2,2,1,0), m1.getArrayCorrectes(Arrays.asList(3,1,2,9)));
		
		/*
			Particio valors fora de rang
		*/
		// per els negatius
		try
		{
			m1.getArrayCorrectes(Arrays.asList(-1,1,2,4));
			assertTrue(false);
		}catch(Exception e) {}
		// per als positius
		try
		{
			m1.getArrayCorrectes(Arrays.asList(2,1,10,4));
			assertTrue(false);
		} catch (Exception e) {}
		
		/*
			Particio mida de la llista incorrecta
		*/
		//llista amb valors menors a codi
		try
		{
			m1.getArrayCorrectes(Arrays.asList(1,1,1));
			assertTrue(false);
		} catch (Exception e) {}
		//llista amb valors majors a codi
		try
		{
			m1.getArrayCorrectes(Arrays.asList(1,1,1,1,1));
			assertTrue(false);
		} catch (Exception e) {}
	}

}
