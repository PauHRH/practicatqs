package es.uab.tqs.mastermind.model;

import java.util.Arrays;
import java.util.List;

public class MockAleatori implements Aleatori{
    	public List<Integer> getAltNumber(int longitud)
	{
		return Arrays.asList(1,2,2,1);
	}
}
