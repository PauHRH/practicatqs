package es.uab.tqs.mastermind.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AleatoriReal implements Aleatori{
    	
	
	public List<Integer> getAltNumber(int longitud)
	{
		List<Integer> list = new ArrayList<>();
		Random randomNumber = new Random();
		for (int i = 0; i < longitud; i++)
		{
			list.add(randomNumber.nextInt(10));
		}
		return list;
	}

}
