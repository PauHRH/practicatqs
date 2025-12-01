package es.uab.tqs.mastermind.controlador;

import java.util.ArrayList;
import java.util.List;

import es.uab.tqs.mastermind.model.MasterMindModel;
import es.uab.tqs.mastermind.vista.MasterMindVista;

public class MasterMindControlador {

    private MasterMindModel model;
    private MasterMindVista view;

    private List<List<Integer>> historialIntents;

    public MasterMindControlador(MasterMindModel model, MasterMindVista view)
    {
    	this.model = model;
    	this.view = view;
        historialIntents = new ArrayList<>();
    }

    public void afegirHistorialIntents(List<Integer> listaNum)
    {
        historialIntents.add(listaNum);
    }

    public void iniciarPartida() {
        view.mostrarBenvinguda();
        int longitud = view.recullLongitud();
        
        view.demanarMaxIntents();
        int maxIntents = view.recullMaxIntents();

        model.novaPartida(longitud, maxIntents);
        historialIntents.clear(); 

        boolean fin = false;
        
        // loop simple
        while (!fin) {
            List<Integer> intent = view.recullIntent(longitud);
            
            // verifica si ja s'ha posat anteriorment
            if (esIntentRepetit(intent)) {
                // si és repetit, tornem a demanar codi (pasa al principi del bucle)
                continue; 
            }

            historialIntents.add(intent);
            boolean partidaAcabada = model.ferJugada(intent);
            List<Integer> feedback = model.getArrayCorrectes(intent);
            view.mostrarResultat(feedback);

            // Path Coverage: Camins de victoria, derrota o continuació
            if (model.getHaGuanyat()) {
                view.mostrarGuanyar();
                fin = true;
            } else if (partidaAcabada || model.getIntentsFets() >= model.getIntentsMax()) {
                view.mostrarPerdre(model.getCodi().getCodi());
                fin = true;
            }
        }
    }

    public boolean esIntentRepetit(List<Integer> nouIntent) {
        // Considerem repetit si qualsevol número del nou intent
        // ja s'ha utilitzat en intents previs (definició segons tests)
        for (List<Integer> intentAntic : historialIntents) {
            for (Integer num : nouIntent) {
                if (intentAntic.contains(num)) {
                    return true;
                }
            }
        }
        return false;
    }
}
