package es.uab.tqs.mastermind;
import org.junit.jupiter.api.Test;

import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.CodiSecret;
import es.uab.tqs.mastermind.model.MockAleatori;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;


class TestCodiSecret {
    CodiSecret codi;
	Aleatori alt;
	
	@BeforeEach
	void setUp()
	{
		alt = new MockAleatori();
		codi = new CodiSecret(alt, 4);
		codi.generarCodi(); // Genera [1, 2, 2, 1]
	}
	

	@Test
	void testGenerarCodi() {
		codi.generarCodi();
		
		assertTrue(codi.comprovaCodi(Arrays.asList(1,2,2,1)));
	}


    @Test
	void testComprovaCodi() {
		/*
			Particions equivalents per longitud 4 (Valor entremig)
		*/
		// Llista igual: codiIntroduit es exactament igual a codi
		assertTrue(codi.comprovaCodi(Arrays.asList(1,2,2,1)));
		// Llista diferent: codiIntroduit es diferent a codi
		assertFalse(codi.comprovaCodi(Arrays.asList(1,5,2,1)));
		assertFalse(codi.comprovaCodi(Arrays.asList(9,1,3,5)));
		// Llista buida
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList()));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista null
		try {
			assertFalse(codi.comprovaCodi(null));
			assertTrue(false);
		} catch (Exception e) {}

		/*
			Particions equivalents per longitud 1 (Valor límit)
		*/
		codi.setCodi(Arrays.asList(1));
		// Llista igual: codiIntroduit es exactament igual a codi
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList(1)));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista diferent: codiIntroduit es diferent a codi
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList(2)));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista buida
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList()));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista null
		try {
			assertFalse(codi.comprovaCodi(null));
			assertTrue(false);
		} catch (Exception e) {}

		/*
			Particions equivalents per longitud 2 (Valor frontera)
		*/
		codi.setCodi(Arrays.asList(5, 4));
		// Llista igual: codiIntroduit es exactament igual a codi
		assertTrue(codi.comprovaCodi(Arrays.asList(5, 4)));
		// Llista diferent: codiIntroduit es diferent a codi
		assertFalse(codi.comprovaCodi(Arrays.asList(2, 6)));
		// Llista buida
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList()));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista null
		try {
			assertFalse(codi.comprovaCodi(null));
			assertTrue(false);
		} catch (Exception e) {}

		/*
			Particions equivalents per longitud 3 (Valor límit)
		*/
		codi.setCodi(Arrays.asList(6, 7, 2));
		// Llista igual: codiIntroduit es exactament igual a codi
		assertTrue(codi.comprovaCodi(Arrays.asList(6, 7, 2)));
		// Llista diferent: codiIntroduit es diferent a codi
		assertFalse(codi.comprovaCodi(Arrays.asList(2, 6, 7)));
		// Llista buida
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList()));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista null
		try {
			assertFalse(codi.comprovaCodi(null));
			assertTrue(false);
		} catch (Exception e) {}

		/*
			Particions equivalents per longitud 5 (Valor límit)
		*/
		codi.setCodi(Arrays.asList(1, 8, 3, 5, 7));
		// Llista igual: codiIntroduit es exactament igual a codi
		assertTrue(codi.comprovaCodi(Arrays.asList(1, 8, 3, 5, 7)));
		// Llista diferent: codiIntroduit es diferent a codi
		assertFalse(codi.comprovaCodi(Arrays.asList(2, 6, 7, 5, 3)));
		// Llista buida
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList()));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista null
		try {
			assertFalse(codi.comprovaCodi(null));
			assertTrue(false);
		} catch (Exception e) {}

		/*
			Particions equivalents per longitud 6 (Valor frontera)
		*/
		codi.setCodi(Arrays.asList(1, 8, 3, 5, 7, 2));
		// Llista igual: codiIntroduit es exactament igual a codi
		assertTrue(codi.comprovaCodi(Arrays.asList(1, 8, 3, 5, 7, 2)));
		// Llista diferent: codiIntroduit es diferent a codi
		assertFalse(codi.comprovaCodi(Arrays.asList(2, 6, 7, 5, 3, 4)));
		// Llista buida
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList()));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista null
		try {
			assertFalse(codi.comprovaCodi(null));
			assertTrue(false);
		} catch (Exception e) {}

		/*
			Particions equivalents per longitud 7 (Valor límit)
		*/
		codi.setCodi(Arrays.asList(1, 8, 3, 5, 7, 2, 1));
		// Llista igual: codiIntroduit es exactament igual a codi
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList(1, 8, 3, 5, 7, 2, 1)));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista diferent: codiIntroduit es diferent a codi
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList(2, 6, 7, 5, 3, 4, 3)));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista buida
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList()));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista null
		try {
			assertFalse(codi.comprovaCodi(null));
			assertTrue(false);
		} catch (Exception e) {}

		/*
			Particions equivalents per longitud 8 (Valor fora de rang per part dels positius)
		*/
		codi.setCodi(Arrays.asList(1, 8, 3, 5, 7, 2, 1, 9));
		// Llista igual: codiIntroduit es exactament igual a codi
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList(1, 8, 3, 5, 7, 2, 1, 9)));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista diferent: codiIntroduit es diferent a codi
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList(2, 6, 7, 5, 3, 4, 3, 9)));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista buida
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList()));
			assertTrue(false);
		} catch (Exception e) {}
		// Llista null
		try {
			assertFalse(codi.comprovaCodi(null));
			assertTrue(false);
		} catch (Exception e) {}

		/*
			Decision i Condition coverage
		*/
		// comprovacio true: codi.size() != codiIntroduit.size()
		codi.setCodi(Arrays.asList(1, 2, 2, 1));
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList(1, 2, 3)));
			assertTrue(false);
		} catch (Exception e) {}

		// comprovacio true: codiIntroduit.size() > 6
		codi.setCodi(Arrays.asList(1, 2, 2, 1));
		try {
			assertFalse(codi.comprovaCodi(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
			assertTrue(false);
		} catch (Exception e) {}
	}
}
