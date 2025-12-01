package es.uab.tqs.mastermind.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockAleatoriVerificaIntent implements Aleatori {
    private static List<List<Integer>> codisGlobals = new ArrayList<>();
	private static int indexGlobal = 0;
	
	static {
		//Codis que es comparteixen entre totes les instancies
		codisGlobals.add(Arrays.asList(1,3,2,3));  // Per verifica
		codisGlobals.add(Arrays.asList(1,2,3,4));  // Per verifica2 (sense repeticions)
	}
	
	public MockAleatoriVerificaIntent()
	{
		
	}
	
	public List<Integer> getAltNumber(int longitud)
	{
		if (indexGlobal < codisGlobals.size()) {
			return codisGlobals.get(indexGlobal++);
		}
		// Si es demanen més codis, retorna el últim
		return codisGlobals.get(codisGlobals.size() - 1);
	}

	public List<Integer> getValorByPosicio(int index)
	{
		if (index < codisGlobals.size()) {
			return codisGlobals.get(index);
		}
		return new ArrayList<>();
	}
}