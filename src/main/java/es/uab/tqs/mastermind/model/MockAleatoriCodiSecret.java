package es.uab.tqs.mastermind.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockAleatoriCodiSecret implements Aleatori {
    private static List<Integer> codi = new ArrayList<>();
	
	

	public MockAleatoriCodiSecret()
	{	}

	public MockAleatoriCodiSecret(List<Integer> codi)
	{
		this.codi = codi;
	}
	
	public List<Integer> getAltNumber(int longitud)
	{
		if(longitud == 1)
		{
			return Arrays.asList(1);
		}
		else if(longitud == 2)
		{
			return Arrays.asList(1,3);
		}
		else if(longitud == 3)
		{
			return Arrays.asList(1,2,3);
		}
		else if(longitud == 4)
		{
			return Arrays.asList(1,2,2,1);
		}
		else if(longitud == 5)
		{
			return Arrays.asList(1,2,3,4,5);
		}
		else if(longitud == 6)
		{
			return Arrays.asList(1,2,3,4,5,4);
		}
		else if(longitud == 7)
		{
			return Arrays.asList(1,2,3,4,5,6,7);
		}
		else if(longitud == 8)
		{
			return Arrays.asList(1,2,3,4,5,6,7,8);
		}
		return null;
	}

}