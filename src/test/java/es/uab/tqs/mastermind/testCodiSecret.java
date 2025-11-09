package es.uab.tqs.mastermind;
import org.junit.jupiter.api.Test;

import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.CodiSecret;
import es.uab.tqs.mastermind.model.MockAleatori;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;


public class testCodiSecret {
    CodiSecret codi;
	Aleatori alt;
	
	@BeforeEach
	void setUp()
	{
		alt = new MockAleatori();
		codi = new CodiSecret(alt, 4);
	}
	

	@Test
	void testGenerarCodi() {
		codi.generarCodi();
		
		assertTrue(codi.comprovaCodi(Arrays.asList(1,2,2,1)));
	}

	@Test
	void testComprovaCodi() {
		// valor normal
		codi.setCodi(Arrays.asList(1,3,4,2));
		assertTrue(codi.comprovaCodi(Arrays.asList(1,3,4,2)));
		assertFalse(codi.comprovaCodi(Arrays.asList(9,1,3,5)));
		
		//valor de 2 valors:
		codi.setCodi(Arrays.asList(5,4));
		assertTrue(codi.comprovaCodi(Arrays.asList(5,4)));
		assertFalse(codi.comprovaCodi(Arrays.asList(2,6)));
		
		//valor de 1 valor
		codi.setCodi(Arrays.asList(5));
		assertTrue(codi.comprovaCodi(Arrays.asList(5)));
		assertFalse(codi.comprovaCodi(Arrays.asList(2)));
		
		//introdueix llista buida, falla
		codi.setCodi(Arrays.asList(5));
		try
		{
			codi.comprovaCodi(Arrays.asList());
			assertTrue(false);
		} catch(Exception exc) {
		}
	}
}
