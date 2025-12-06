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

        boolean acabarPartides = false;

        while(!acabarPartides)
        {
            view.mostrarBenvinguda();
            int longitud = view.recullLongitud();

            view.demanarMaxIntents();
            int maxIntents = view.recullMaxIntents();

            view.getInstruccions();

            model.novaPartida(longitud, maxIntents);
            historialIntents.clear(); 

            boolean fin = false;
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

                if (model.getHaGuanyat()) {
                    view.mostrarGuanyar();
                    fin = true;
                } else if (partidaAcabada || model.getIntentsFets() >= model.getIntentsMax()) {
                    view.mostrarPerdre(model.getCodi().getCodi());
                    fin = true;
                }
            }

            boolean seguirJugant = view.seguirJugant();
            if (seguirJugant == false)
            {
                acabarPartides = true;
            }
        }
    }

    public boolean esIntentRepetit(List<Integer> nouIntent) {
        for (List<Integer> intentAntic : historialIntents) {
            boolean esIdentic = true;

            if (intentAntic.size() != nouIntent.size()) {
                continue; 
            }

            for (int i = 0; i < nouIntent.size(); i++) {
                if (!intentAntic.get(i).equals(nouIntent.get(i))) {
                    esIdentic = false;
                    break; // Si un número ja no coincideix, sortim d'aquest bucle intern
                }
            }

            // Si després de mirar tots els números 'esIdentic' segueix sent true,
            // vol dir que hem trobat una coincidència exacta.
            if (esIdentic) {
                return true;
            }
        }
        
        // Si hem mirat tot l'historial i no hem trobat cap idèntic
        return false;
    }
}
