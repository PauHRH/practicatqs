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
	
	}

	
	
	void testGetArrayPosicions() {
		fail("Not yet implemented");
	}
}
