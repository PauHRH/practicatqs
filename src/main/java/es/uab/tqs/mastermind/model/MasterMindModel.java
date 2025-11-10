package es.uab.tqs.mastermind.model;
import java.util.List;
import java.util.ArrayList;

public class MasterMindModel {
    private CodiSecret codi;
	private int intentsMax;
	private int intentsFets;
	private Aleatori aleatori;
	boolean haGuanyat;
	
	public List<Integer> getArrayCorrectes(List<Integer> codiProposat)
	{
		return null;
	}
	
	
	public MasterMindModel(int longCodi, int intentsMax, Aleatori aleatori)
	{
		
	}
	
	public int getIntentsMax()
	{
		return 0;
	}
	
	public void setIntentsFets(int intentsFets)
	{
		
	}
	
	public void setIntentsMax(int intentsMax)
	{
		
		
	}
	
	public int getIntentsFets()
	{
		return 0;
	}
	
	public CodiSecret getCodi()
	{
		return null;
	}
	

	public boolean getHaGuanyat()
	{
		return true;
	}
	
	public void novaPartida(int longCodi, int intentsMax)
	{
		
	}
	
	public boolean ferJugada(List<Integer> codiProposat)
	{
		return true;		
	}
}
