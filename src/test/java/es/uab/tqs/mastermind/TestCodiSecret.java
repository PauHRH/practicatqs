package es.uab.tqs.mastermind;
import org.junit.jupiter.api.Test;

import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.CodiSecret;
import es.uab.tqs.mastermind.model.MockAleatori;
import es.uab.tqs.mastermind.model.MockAleatoriCodiSecret;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	

	// convertidor string 1,2,3,4 a list per test automatització
	private List<Integer> stringToList(String s) {
        if (s == null || s.trim().isEmpty()) return new ArrayList<>();
        return Stream.of(s.split(","))
                     .map(String::trim)
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());
    }

	@org.junit.jupiter.params.ParameterizedTest(name = "Intent: {0} -> Esperat: {1}")
	@org.junit.jupiter.params.provider.CsvSource({
		"1 2 2 1,true",   // Cas correcte (Coincideix amb el setup del mock)
		"1 5 2 1,false",  // Un número diferent
		"9 1 3 5,false",  // Tot diferent
		"1 2 2 0,false",  // Últim dígit diferent
		"1 2 2,false"     // Longitud incorrecta (si la lògica ho gestiona així)
	})
	void testComprovaCodiAutomatitzacio(String intentString, boolean esperat) {
        // Assumim que el codi secret esperat per aquestes dades és [1, 2, 2, 1]
        codi.setCodi(Arrays.asList(1, 2, 2, 1));

        // passa el string del fitxer a un array
		List<Integer> intent = new ArrayList<>();
		if (intentString != null && !intentString.trim().isEmpty()) {
			for (String num : intentString.trim().split("\\s+")) {
				intent.add(Integer.parseInt(num));
			}
		}

        // verifica el resultat amb el esperat del fitxer
        try {
            boolean resultat = codi.comprovaCodi(intent);
            assertEquals(esperat, resultat);
        } catch (Exception e) { }
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

		// la resta de casos ja es compleixen amb el tests executats previament.
		// Dona 100% al condition testing. Els casos són els següents:
		// En particions equivalents per longitud 1 (Valor límit), valor límit qualsevol cas, es comprova que codi.size() < 2 és true.
		// En particions equivalents per longitud 7 (Valor límit), valor límit qualsevol cas, es comprova que codi.size() > 6 és true.
		// En particions equivalents per longitud entre 2 i 6 es comprova que codi.size() < 2 i codi.size() > 6 és false.
		// En particions equivalents per longitud 4 (Valor entremig) on codiIntroduit es exactament igual a codi es comprova que codiIntroduit.size() < 2 i codiIntroduit.size() > 6 és false.
		// En particions equivalents per longitud 1 (Valor límit) codiIntroduit es exactament igual a codi es comprova que codiIntroduit.size() < 2 és true.
		// En particions equivalents per longitud 6 (Valor frontera) on codiIntroduit es exactament igual a codi es comprova que codi.size() != codiIntroduit.size() és false.
		// El decision coverage ja es compleix quan alguna de les condicions és true (al ser un or) i serà false quan el valor és 4 
		// (és a dir, per exemple, en el cas: "En particions equivalents per longitud 4 (Valor entremig) on codiIntroduit es exactament igual a codi").
	}
}
