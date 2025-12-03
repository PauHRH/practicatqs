package es.uab.tqs.mastermind.vista;

import java.util.ArrayList;
import java.util.List;

public class MockMasterMindVista extends MasterMindVista {

    // Inputs per test
    private int longitudSimulada = 4;
    private int maxIntentsSimulats = 5;
    private List<List<Integer>> intentsUsuari = new ArrayList<>();
    private int indexIntent = 0;

    public boolean haMostratGuanyar = false;
    public boolean haMostratPerdre = false;
    public List<List<Integer>> resultatsMostrats = new ArrayList<>();

    public MockMasterMindVista()
    {
        super();
    }

    @Override
    public void mostrarBenvinguda() { }

    @Override
    public void demanarMaxIntents() { }

    public void setIndexIntent(int index)
    {
        this.indexIntent = index;
    }

    // metodes per tests
    public void setConfiguracioInicial(int longitud, int maxIntents) {
        this.longitudSimulada = longitud;
        this.maxIntentsSimulats = maxIntents;
        // reset d'estat per a noves partides
        this.indexIntent = 0;
        this.intentsUsuari.clear();
        this.haMostratGuanyar = false;
        this.haMostratPerdre = false;
        this.resultatsMostrats.clear();
    }

    public void afegirIntentUsuari(List<Integer> intent) {
        this.intentsUsuari.add(intent);
    }

    @Override
    public List<Integer> recullIntent(int longitudEsperada) {
        if (indexIntent < intentsUsuari.size()) {
            return intentsUsuari.get(indexIntent++);
        }
        // retorna llista de 0s si esta buida
        return new ArrayList<>(java.util.Collections.nCopies(longitudEsperada, 0));
    }

    @Override
    public void mostrarResultat(List<Integer> resultat) {
        resultatsMostrats.add(resultat);
    }

    @Override
    public void mostrarGuanyar() {
        haMostratGuanyar = true;
    }

    @Override
    public void mostrarPerdre(List<Integer> codiSecret) {
        haMostratPerdre = true;
    }


    @Override
    public int recullLongitud()
    {
        return this.longitudSimulada;
    }

    @Override
    public int recullMaxIntents()
    {
        return this.maxIntentsSimulats;
    }

    public void getInstruccions()
    {
        
    }

}
