package es.uab.tqs.mastermind.model;

import java.util.ArrayList;
import java.util.List;

public class CodiSecret{
	List<Integer> codi = new ArrayList<>();
	int longitud;
	Aleatori aleatori;
	
	public CodiSecret()
	{
		codi = new ArrayList<>();
		longitud = 0;
	}
	
	public CodiSecret (Aleatori aleatori, int longitud)
	{
		this.longitud = longitud;
		this.aleatori = aleatori;
	}
	
	public void generarCodi()
	{
		codi = aleatori.getAltNumber(4);
	}
	
	public void setCodi(List<Integer> codi)
	{
		this.codi = codi;
	}
	
	
	public List<Integer> getCodi()
	{
		return codi;
	}
	
	public int getPos(int pos)
	{
		return codi.get(pos);
	}
	
	public int getLen()
	{
		return longitud;
	}
	
	public boolean comprovaCodi(List<Integer> codiIntroduit)
	{
		if (codiIntroduit.isEmpty())
		{
			throw new IllegalArgumentException("No s'ha introduit cap valor a la llista.");		
		}
		return codi.equals(codiIntroduit);

	}
	
}
