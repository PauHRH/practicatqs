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
        assert(codiProposat.size() == codi.getLen()) : "Error, longitud codi proposat incorrecte";
		VerificaIntent vi = new VerificaIntent(codi);
		return vi.getArrayPosicions(codiProposat);
	}
	
	
	public MasterMindModel(int longCodi, int intentsMax, Aleatori aleatori)
	{
		this.intentsFets = 0;
		this.intentsMax = intentsMax;
		this.aleatori = aleatori;
		this.codi = new CodiSecret(aleatori, longCodi);
		this.codi.generarCodi();	
		this.haGuanyat = false;
	}
	
	public int getIntentsMax()
	{
		return intentsMax;
	}
	
	public void setIntentsFets(int intentsFets)
	{
		this.intentsFets = intentsFets;
	}
	
	public void setIntentsMax(int intentsMax)
	{
		this.intentsMax = intentsMax;
	}
	
	public int getIntentsFets()
	{
		return intentsFets;
	}
	
	public CodiSecret getCodi()
	{
		return codi;
	}
	

	public boolean getHaGuanyat()
	{
		return haGuanyat;
	}
	
	public void novaPartida(int longCodi, int intentsMax)
	{
		//precondició:
		assert((longCodi >= 2 && longCodi <= 6)) : "Longitud codi incorrecta";
		assert((intentsMax >= 1 && intentsMax <= 10)) : "Num intents incorrecta";
		
		this.codi = new CodiSecret(this.aleatori, longCodi);
		this.intentsMax = intentsMax;
		this.intentsFets = 0;

        //postcondició: 
        assert(intentsFets == 0) : "IntentsFets no inicialitzat a 0";
	}
	
	public boolean ferJugada(List<Integer> codiProposat)
	{
		assert(codiProposat.size() == codi.getLen() || intentsFets <= intentsMax ) : "Error, fallida";

		intentsFets++;
		
		if(codi.comprovaCodi(codiProposat))
		{
			haGuanyat = true;
			return true;
		}
		
		if(intentsFets >= intentsMax)
		{
			return true;
		}
		return false;		
	}
}
