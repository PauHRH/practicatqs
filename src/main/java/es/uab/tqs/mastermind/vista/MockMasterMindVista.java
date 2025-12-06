package es.uab.tqs.mastermind.vista;

import java.util.ArrayList;
import java.util.List;

public class MockMasterMindVista extends MasterMindVista {

    // Inputs per test
    private int longitudSimulada = 4;
    private int maxIntentsSimulats = 5;
    private List<List<Integer>> intentsUsuari = new ArrayList<>();
    private int indexIntent = 0;
    private boolean seguirJugant = false;

    public boolean haMostratGuanyar = false;
    public boolean haMostratPerdre = false;
    public List<List<Integer>> resultatsMostrats = new ArrayList<>();

    // Per loop testing
    private List<int[]> configuracions = new ArrayList<>(); // Guarda {longitud, maxIntents}
    private List<Boolean> respostesSeguirJugant = new ArrayList<>();
    private int indexConfig = 0;
    private int indexSeguir = 0;

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
        this.configuracions.clear();
        this.configuracions.add(new int[]{longitud, maxIntents});
        this.indexConfig = 0;

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
        if (indexConfig < configuracions.size()) {
            int val = configuracions.get(indexConfig)[1];
            indexConfig++;
            return val;
        }
        return 5;
    }

    @Override
    public void getInstruccions()
    {
        // no ha de retornar res
    }

    public void setSeguirJugant(boolean seguirJugant)
    {
        this.respostesSeguirJugant.clear();
        this.respostesSeguirJugant.add(seguirJugant);
    }

    @Override
    public boolean seguirJugant()
    {
        if (indexSeguir < respostesSeguirJugant.size()) {
            return respostesSeguirJugant.get(indexSeguir++);
        }
        return false;
    }

    /*
        Per loop testing
    */
    public void afegirConfiguracioExtra(int longitud, int maxIntents) {
        this.configuracions.add(new int[]{longitud, maxIntents});
    }

    public void afegirRespostaSeguirJugant(boolean seguir) {
        this.respostesSeguirJugant.add(seguir);
    }
}
