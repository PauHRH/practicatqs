package es.uab.tqs.mastermind;
import org.junit.jupiter.api.Test;

import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.CodiSecret;
import es.uab.tqs.mastermind.model.VerificaIntent;
import es.uab.tqs.mastermind.model.MockAleatoriVerificaIntent;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		codi2.generarCodi();
		verifica2 = new VerificaIntent(codi2);
	}

	
	//passem d'string a list el string del fitxer csv
	 private List<Integer> stringToList(String s) {
        if (s == null || s.trim().isEmpty()) return new ArrayList<>();
        return Stream.of(s.split(","))
                     .map(String::trim)
                     .map(Integer::parseInt)
                     .collect(Collectors.toList());
    }

    @ParameterizedTest(name = "Intent: {0} -> Resultat Esperat: {1}")
    @CsvSource(delimiter = ';', value = {
        "1, 3, 2, 3; 1, 1, 1, 1",  // Tot correcte
        "4, 3, 2, 3; 0, 1, 1, 1",  // 1r incorrecte
        "1, 5, 2, 3; 1, 0, 1, 1",  // 2n incorrecte
        "6, 5, 2, 3; 0, 0, 1, 1",  // Dos incorrectes
        "4, 1, 2, 9; 0, 2, 1, 0",  // Barreja (incorrecte, mal pos, bé, incorrecte)
        "7, 6, 5, 4; 0, 0, 0, 0"   // Tot malament
    })

    void testGetArrayPosicionsDataDriven(String intentStr, String resultatEsperatStr) {
        // Fixem el codi secret a [1, 3, 2, 3] per aquest set de dades
        codi.setCodi(Arrays.asList(1, 3, 2, 3));
        verifica = new VerificaIntent(codi);

        // Transformació de dades
        List<Integer> intent = stringToList(intentStr);
        List<Integer> resultatEsperat = stringToList(resultatEsperatStr);

        // Execució
        List<Integer> resultatObtingut = verifica.getArrayPosicions(intent);

        // Verificació
        assertEquals(resultatEsperat, resultatObtingut, 
            "El càlcul de posicions ha fallat per l'entrada: " + intentStr);
    }

	@Test
	void testGetArrayPosicions() {
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
			Decision Coverage i Condition Coverage del primer if
			codiIntroduit.size() != codi.getLen()
			· true: en particio segons la validesa de l’entrada on llista massa llarga
			· false: en particions segons relació posicional entre codi secret i intent on tots els números correctes i a la posició correcta
		*/


		/*
		 * Decision i Condition Coverage del rang del segon if
		 * if (valor < 0 || valor > 9)
		 */
		// Cas valor < 0, if (true || false)
		try {
            verifica.getArrayPosicions(Arrays.asList(-5,3,2,1));
            assertTrue(false);
        } catch (Error e) {}
		// Cas valor > 9, if (false || true)
        try {
            verifica.getArrayPosicions(Arrays.asList(1,3,12,1));
            assertTrue(false);
        } catch (Error e) {}
        // Cas valor dins rang --> condició falsa, if (false || false)
        assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(0,9,0,9)));

		// Cas valor > 9 i valor < 0, if (true || true)
		try {
            verifica.getArrayPosicions(Arrays.asList(-1,3,12,1));
            assertTrue(false);
        } catch (Error e) {}
		
        /*
         * Condition Coverage del tercer if
         * if (secret[i] == intent[i])
         */
        // Cas on entra al if (trobem un 1)
        assertEquals(Arrays.asList(1,0,0,0), verifica.getArrayPosicions(Arrays.asList(1,9,9,9)));
        // Cas on no entra al if
        assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(9,9,9,9)));
        
        /*
         * Condition Coverage del quart if
         * if(verificador[i] == 0)
         */
        // Cas verificador[i] == 0
        assertEquals(Arrays.asList(2,0,0,0), verifica.getArrayPosicions(Arrays.asList(3,9,9,9)));
        // Cas verificador[i] != 0 (no s'entra al segon if)
        assertEquals(Arrays.asList(1,0,0,0), verifica.getArrayPosicions(Arrays.asList(1,9,9,9)));
        
        /*
         * Condition coverage del cinquè if
         * if (index != -1)
         */
        // Cas index != -1 (valor correcte però mala posició)
        assertEquals(Arrays.asList(2,0,0,0), verifica.getArrayPosicions(Arrays.asList(3,9,9,9)));
        // Cas index == -1 (valor no existeix)
        assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(8,8,8,8)));
        
		
		/*
		 * Path Coverage
		 */
		
		// Camí 1:
		try {
			verifica.getArrayPosicions(Arrays.asList(1,2,3));
			assertTrue(false);
		} catch(Error e) { }
		
		// Camí 2:
		try {
			verifica.getArrayPosicions(Arrays.asList(1, 3, 10, 3));
			assertTrue(false);
		} catch(Error e) {		}

		// Camí 3:
		assertEquals(Arrays.asList(1,1,1,1), verifica.getArrayPosicions(Arrays.asList(1,3,2,3)));
		
		// Camí 4:
		assertEquals(Arrays.asList(0,0,0,0), verifica.getArrayPosicions(Arrays.asList(9,8,7,6)));
		
		// Camí 5:
		assertEquals(Arrays.asList(0,2,1,0), verifica.getArrayPosicions(Arrays.asList(4,1,2,9)));

	
	}


   

}
