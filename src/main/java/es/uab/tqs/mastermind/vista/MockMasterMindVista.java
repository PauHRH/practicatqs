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

    // metodes per tests
    public void setConfiguracioInicial(int longitud, int maxIntents) {
        this.longitudSimulada = longitud;
        this.maxIntentsSimulats = maxIntents;
    }

    public void afegirIntentUsuari(List<Integer> intent) {
        this.intentsUsuari.add(intent);
    }

    @Override
    public List<Integer> recullIntent(int longitudEsperada) {
        if (indexIntent < intentsUsuari.size()) {
            return intentsUsuari.get(indexIntent++);
        }
        return new ArrayList<>(); 
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
        return 4;
    }

    @Override
    public int recullMaxIntents()
    {
        return 5;
    }


}
