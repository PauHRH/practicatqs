package es.uab.tqs.mastermind;
import org.junit.jupiter.api.Test;

import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.CodiSecret;
import es.uab.tqs.mastermind.model.MockAleatori;
import es.uab.tqs.mastermind.model.MockAleatoriCodiSecret;

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

		/*
			Particions equivalents per longitud 2 (Valor frontera)
		*/
		Aleatori altCodiGenerar = new MockAleatoriCodiSecret(Arrays.asList(1,3));
		CodiSecret test1 = new CodiSecret(altCodiGenerar, 2);
		test1.generarCodi();	
		assertTrue(test1.comprovaCodi(Arrays.asList(1,3)));
		
		/*
			Particions equivalents per longitud 6 (Valor frontera)
		*/
		CodiSecret test2 = new CodiSecret(altCodiGenerar, 6);
		test2.generarCodi();
		assertTrue(test2.comprovaCodi(Arrays.asList(1,2,3,4,5,4)));
		
		/*
			Particions equivalents per longitud 1 (Valor límit)
		*/
		CodiSecret test3 = new CodiSecret(altCodiGenerar, 1);
		try
		{
			test3.generarCodi();
			assertTrue(test3.comprovaCodi(Arrays.asList(1)));
			assertTrue(false);
		} catch(Exception e) {}

		/*
			Particions equivalents per longitud 3 (Valor límit)
		*/
		CodiSecret test4 = new CodiSecret(altCodiGenerar, 3);
		test4.generarCodi();
		assertTrue(test4.comprovaCodi(Arrays.asList(1,2,3)));
		
		/*
			Particions equivalents per longitud 5 (Valor límit)
		*/
		CodiSecret test5 = new CodiSecret(altCodiGenerar, 5);
		test5.generarCodi();
		assertTrue(test5.comprovaCodi(Arrays.asList(1,2,3,4,5)));


		/*
			Particions equivalents per longitud 7 (Valor límit)
		*/
		CodiSecret test6 = new CodiSecret(altCodiGenerar, 7);

		try {
			test6.generarCodi();
			assertTrue(test6.comprovaCodi(Arrays.asList(1,2,3,4,5,6,7)));
			assertTrue(false);
		} catch(Exception e) {}

		/*
			Particions equivalents per longitud 8 (Valor fora de rang per sobre)
		*/
		CodiSecret test7 = new CodiSecret(altCodiGenerar, 8);

		
		try{
			test7.generarCodi();
			assertTrue(test7.comprovaCodi(Arrays.asList(1,2,3,4,5,6,7,8)));
			assertTrue(false);
		} catch(Exception e) {}

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
