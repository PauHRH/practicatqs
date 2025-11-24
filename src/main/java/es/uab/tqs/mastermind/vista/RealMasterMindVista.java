package es.uab.tqs.mastermind.vista;

public class RealMasterMindVista extends MasterMindVista {

    public RealMasterMindVista()
	{
		super();
	}
	
	public int recullLongitud()
    {
    	int longitud = 0;
    	if (scanner.hasNextInt()) {
            longitud = scanner.nextInt();
    	}
    	assert(longitud != 0) : "Error, la longitud no ha estat introduida";
        return longitud;
    }
	
    public int recullMaxIntents()
    {
    	int maxIntents = 0;
    	if (scanner.hasNextInt()) {
    		maxIntents = scanner.nextInt();
    	}
    	assert(maxIntents >= 1 && maxIntents <= 10) : "Error, la longitud no ha estat introduida";
        return maxIntents;
    }
}
