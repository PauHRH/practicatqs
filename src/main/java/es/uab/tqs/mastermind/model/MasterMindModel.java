package es.uab.tqs.mastermind.model;
import java.util.List;

public class MasterMindModel {
    private CodiSecret codi;
	private int intentsMax;
	private int intentsFets;
	private Aleatori aleatori;
	private boolean haGuanyat;
	
	public MasterMindModel(int longCodi, int intentsMax, Aleatori aleatori)
	{
		this.intentsFets = 0;
		this.intentsMax = intentsMax;
		this.aleatori = aleatori;
		this.codi = new CodiSecret(aleatori, longCodi);
		this.codi.generarCodi();	
		this.haGuanyat = false;
	}

	public MasterMindModel(Aleatori aleatori)
	{
		this.intentsFets = 0;
		this.aleatori = aleatori;
		this.codi = new CodiSecret(aleatori, 4);
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
		if (longCodi < 2 || longCodi > 6) 
		{
			throw new IllegalArgumentException("Longitud codi incorrecta");
		}

		if (intentsMax < 1 || intentsMax > 10)
		{
			throw new IllegalArgumentException("Num intents incorrecta");
		}

		this.codi = new CodiSecret(this.aleatori, longCodi);
		this.codi.generarCodi();
		this.intentsMax = intentsMax;
		this.intentsFets = 0;
		this.haGuanyat = false;
	}
	
	public boolean ferJugada(List<Integer> codiProposat)
	{
		if (codiProposat.size() != codi.getLen()) 
		{
			throw new IllegalArgumentException("Longitud codi proposat incorrecta");
		}

		if (intentsFets >= intentsMax)
		{
			throw new IllegalArgumentException("Intents màxims fets");
		}

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

	public List<Integer> getArrayCorrectes(List<Integer> codiProposat)
	{
		for (int c : codiProposat)
		{
			if (c < 0 || c > 9)
				throw new IllegalArgumentException("Numero incorrecte en el codi proposat.");
		}
		
		if (codiProposat.size() != codi.getLen()) 
		{
			throw new IllegalArgumentException("Longitud codi proposat incorrecta");
		}
		
		VerificaIntent vi = new VerificaIntent(codi);
		return vi.getArrayPosicions(codiProposat);
	}
	
	
}
