package es.uab.tqs.mastermind.vista;
import java.util.Scanner;
import java.util.List;

public abstract class MasterMindVista {
    protected Scanner scanner;

    public MasterMindVista()
    {
        scanner = new Scanner(System.in);
    }

    public void mostrarBenvinguda()
    {
    	System.out.println("Benvingut al MasterMind, introdueix la longitud de la paraula que vulguis posar: ");

    }

    public void demanarMaxIntents()
    {
    	System.out.println("Quants intents vols fer? ");
    }

    public abstract int recullLongitud();
    public abstract int recullMaxIntents();
    
}
