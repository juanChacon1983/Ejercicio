package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo;


import java.util.ArrayList;
import java.util.List;

public class GeneradorDeCadenasDeDnaHorizontal implements GeneradorDeCadenasDeDna {

 

	@Override
	public List<char[]> armarCadenasDeAdn(String[] dna) {
		List<char[]> listaDeCadenas = new ArrayList<char[]>();

		for (String valoresDeAdns : dna) {
			listaDeCadenas.add(valoresDeAdns.toCharArray());
		};
		
		return listaDeCadenas;
	}

}
