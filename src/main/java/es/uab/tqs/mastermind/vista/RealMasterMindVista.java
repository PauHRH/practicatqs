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
        boolean valid = false;
        while(!valid)
        {
            if (scanner.hasNextInt()) {
                longitud = scanner.nextInt();
            }
            if (longitud >= 2 && longitud <= 6)
            {
                valid = true;
            }
        }
        return longitud;
    }
	
	@Override
    public int recullMaxIntents()
    {
    	int maxIntents = 0;
        boolean valid = false;
        while(!valid)
        {
            if (scanner.hasNextInt()) {
                maxIntents = scanner.nextInt();
            }
            if (maxIntents >= 1 && maxIntents <= 10)
            {                
                valid = true;
            }
        }
        return maxIntents;
    }



    @Override
    public List<Integer> recullIntent(int longitudEsperada) {
        List<Integer> intent = new ArrayList<>();
        boolean valid = false;
        while (!valid) {
            intent.clear();
            System.out.println("Introdueix el teu intent (" + longitudEsperada + " números, separats per espais):");
            String linia = scanner.nextLine();
            String[] parts = linia.trim().split("\\s+");
            if (parts.length != longitudEsperada) {
                System.out.println("Has d'introduir exactament " + longitudEsperada + " números.");
                continue;
            }
            boolean totValid = true;
            for (String part : parts) {
                try {
                    int valor = Integer.parseInt(part);
                    if (valor < 0 || valor > 9) {
                        totValid = false;
                        break;
                    }
                    intent.add(valor);
                } catch (NumberFormatException e) {
                    totValid = false;
                    break;
                }
            }
            if (!totValid) {
                System.out.println("Tots els números han de ser entre 0 i 9. Torna a introduir la seqüència.");
            } else {
                valid = true;
            }
        }
        return intent;
    }

    @Override
    public void mostrarResultat(List<Integer> resultat) {
        System.out.println("----- Resultat detallat -----");
        for (int i = 0; i < resultat.size(); i++) {
            int valor = resultat.get(i);
            String missatge = "";
            
            // 0 = incorrecte, 1 = correcte, 2 = num correcte pos no.
            switch (valor) {
                case 1:
                    missatge = "[OK] Correcte (Posició i Número)";
                    break;
                case 2:
                    missatge = "[?!] Número correcte, però posició incorrecta";
                    break;
                case 0:
                    missatge = "[X] Incorrecte";
                    break;
                default:
                    missatge = "Desconegut";
            }
            System.out.println("Posició " + (i + 1) + ": " + missatge);
        }
        System.out.println("-----------------------------");
    }

    @Override
    public void mostrarGuanyar() {
        System.out.println("Felicitats! Has guanyat.");
    }

    @Override
    public void mostrarPerdre(List<Integer> codiSecret) {
        System.out.println("Has perdut. El codi era: " + codiSecret.toString());
    }

    public void getInstruccions()
    {
        System.out.println("INSTRUCCIONS DEL JOC:");
        System.out.println("Has d'introduir els diversos valors separats en espais, és a dir, si vols introduir el valor 1234 l'has d'introduir d'aquesta manera: 1 2 3 4");
    }
}
