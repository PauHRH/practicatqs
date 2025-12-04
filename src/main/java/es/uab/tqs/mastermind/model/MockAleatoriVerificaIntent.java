package es.uab.tqs.mastermind.model;

import java.util.Arrays;
import java.util.List;

public class MockAleatoriVerificaIntent implements Aleatori {

    private final List<List<Integer>> codis;
    private int index = 0;

    public MockAleatoriVerificaIntent() {
        this.codis = Arrays.asList(
                Arrays.asList(1,3,2,3), // primer codi
                Arrays.asList(1,2,3,4)  // segon codi
        );
    }

    @Override
    public List<Integer> getAltNumber(int longitud) {
        if (index < codis.size()) {
            return codis.get(index++);
        }
        // si ja no queden codis, retorna l’últim sempre
        return codis.get(codis.size() - 1);
    }

    public List<Integer> getValorByPosicio(int pos) {
        if (pos >= 0 && pos < codis.size()) {
            return codis.get(pos);
        }
        return codis.get(codis.size() - 1);
    }
}