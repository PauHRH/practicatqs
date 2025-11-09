package es.uab.tqs.mastermind.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockAleatoriConfiguracio implements Aleatori{
	List<List<Integer>> listaIntentsRetornar = new ArrayList<>();
	
	public MockAleatoriConfiguracio()
	{
		listaIntentsRetornar.add(Arrays.asList(1,3,2,3));
		listaIntentsRetornar.add(Arrays.asList(3,2,1,5));
		listaIntentsRetornar.add(Arrays.asList(6,4,3,7));
		
		
	}
	
	public List<Integer> getAltNumber(int longitud)
	{
		List<Integer> primer = listaIntentsRetornar.get(0);
		listaIntentsRetornar.remove(0);
		return primer;
	}

}
