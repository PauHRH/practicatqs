package es.uab.tqs.mastermind.model;

import java.util.ArrayList;
import java.util.List;

public class CodiSecret{
	List<Integer> codi = new ArrayList<>();
	int longitud;
	Aleatori aleatori;
	
	public CodiSecret(Aleatori aleatori, int longitud)
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
		this.longitud = codi.size();
	}
	
	
	public List<Integer> getCodi()
	{
		return codi;
	}
	
	public int getLen()
	{
		return longitud;
	}
	
	public boolean comprovaCodi(List<Integer> codiIntroduit)
	{
		if (codi.size() < 2 || codi.size() > 6 || codiIntroduit.size() < 2 ||
		 codiIntroduit.size() > 6 || codi.size() != codiIntroduit.size())
		{
			throw new IllegalArgumentException("Algun codi es de mida incorrecte.");
		}

		return codi.equals(codiIntroduit);
	}
	
}
