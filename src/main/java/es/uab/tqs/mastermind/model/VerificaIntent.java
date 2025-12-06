package es.uab.tqs.mastermind.model;

import java.util.ArrayList;
import java.util.List;

public class VerificaIntent {
    CodiSecret codi;
	List<Integer> codiIntroduit;
	
	public VerificaIntent(CodiSecret codi)
	{
		this.codi = codi;
	}
	
	public List<Integer> getArrayPosicions(List<Integer> codiIntroduit) // 0 = incorrecte, 1 = correcte, 2 = num correcte pos no.
	{
		if (codiIntroduit.size() != codi.getLen()) {
			throw new Error("Error, longitud codi proposat incorrecta: esperat " + codi.getLen() + ", rebut " + codiIntroduit.size());
		}
	    List<Integer> verificador = new ArrayList<>();
	    for (int i = 0; i < codi.getLen(); i++) {
	        verificador.add(0);
	    }

	    List<Integer> codiCopia = new ArrayList<>(codi.getCodi());

	    // primer marquem els correctes (posicio i numero)
	    for (int i = 0; i < codi.getLen(); i++) {
			if(codiIntroduit.get(i) < 0 || codiIntroduit.get(i) > 9)
				throw new Error("Error, valor fuera del límite");
	        if (codi.getCodi().get(i).equals(codiIntroduit.get(i))) {
	            verificador.set(i, 1);          // correcto
	            codiCopia.set(i, -1);           // marcar com usat
	        }
	    }

	    // marquem els numeros correctes pero en mala posicio
		for (int i = 0; i < codi.getLen(); i++) {
	        if (verificador.get(i) == 0) {      // només els que encara son 0
	            int index = codiCopia.indexOf(codiIntroduit.get(i));
	            if (index != -1) {
	                verificador.set(i, 2);      // numero correcte, mala posicio
	                codiCopia.set(index, -1);   // marcar com usat
	            }
	        }
	    }

	    return verificador;
	
	}
}
