package es.uab.tqs.mastermind.model;

import java.util.ArrayList;
import java.util.Arrays;
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
	    List<Integer> verificador = new ArrayList<>(Arrays.asList(0, 0, 0, 0));

	    // copia del codigo secreto para marcar los elementos ya contados
	    List<Integer> codiCopia = new ArrayList<>(codi.getCodi());

	    // primero marcamos los correctos (posicion y numero)
	    for (int i = 0; i < codi.getLen(); i++) {
	    	if(codiIntroduit.get(i) < 0 || codiIntroduit.get(i) > 9)
	    		throw new Error("Error, valor fuera del límite");
	        if (codi.getCodi().get(i).equals(codiIntroduit.get(i))) {
	            verificador.set(i, 1);          // correcto
	            codiCopia.set(i, -1);           // marcar como usado
	        }
	    }

	    // marcamos los numeros correctos pero en mala posicion
	    for (int i = 0; i < codiIntroduit.size(); i++) {
	        if (verificador.get(i) == 0) {      // solo los que aun son 0
	            int index = codiCopia.indexOf(codiIntroduit.get(i));
	            if (index != -1) {
	                verificador.set(i, 2);      // numero correcto, mala posicion
	                codiCopia.set(index, -1);   // marcar como usado
	            }
	        }
	    }

	    return verificador;
	
	}
}
