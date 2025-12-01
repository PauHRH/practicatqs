package es.uab.tqs.mastermind.vista;
import java.util.List;
import java.util.ArrayList;

public class RealMasterMindVista extends MasterMindVista {

    public RealMasterMindVista()
	{
		super();
	}

	@Override
	public int recullLongitud()
    {
    	int longitud = 0;
    	if (scanner.hasNextInt()) {
            longitud = scanner.nextInt();
    	}
    	assert(longitud != 0) : "Error, la longitud no ha estat introduida";
        return longitud;
    }
	
	@Override
    public int recullMaxIntents()
    {
    	int maxIntents = 0;
    	if (scanner.hasNextInt()) {
    		maxIntents = scanner.nextInt();
    	}
    	assert(maxIntents >= 1 && maxIntents <= 10) : "Error, la longitud no ha estat introduida";
        return maxIntents;
    }

	@Override
    public List<Integer> recullIntent(int longitudEsperada) {
        List<Integer> intent = new ArrayList<>();
        System.out.println("Introdueix el teu intent (" + longitudEsperada + " numeros):");
        for(int i=0; i<longitudEsperada; i++) {
            if(scanner.hasNextInt()) {
                intent.add(scanner.nextInt());
            }
        }
        return intent;
    }

    @Override
    public void mostrarResultat(List<Integer> resultat) {
        System.out.println("Resultat: " + resultat.toString());
    }

    @Override
    public void mostrarGuanyar() {
        System.out.println("Felicitats! Has guanyat.");
    }

    @Override
    public void mostrarPerdre(List<Integer> codiSecret) {
        System.out.println("Has perdut. El codi era: " + codiSecret.toString());
    }
}
