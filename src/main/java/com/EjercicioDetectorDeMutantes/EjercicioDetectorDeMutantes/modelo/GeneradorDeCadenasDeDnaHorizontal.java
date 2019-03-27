package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo;


import java.util.ArrayList;
import java.util.List;

public class GeneradorDeCadenasDeDnaHorizontal extends GeneradorDeCadenasDeDna {

 

	@Override
	public List<String> armarCadenasDeAdn(String[] dna) {
		List<String> listaDeCadenas = new ArrayList<String>();

		for (String valoresDeAdns : dna) {
			listaDeCadenas.add(valoresDeAdns);
		};
		
		return listaDeCadenas;
	}

}
