package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo;

import java.util.ArrayList;
import java.util.List;

public class GeneradorDeCadenasDeDnaDiagonalDeDerechaAIzquierda implements GeneradorDeCadenasDeDna {

	@Override
	public List<char[]> armarCadenasDeAdn(String[] dna) {
		List<char[]> listaDeCadenas = new ArrayList<char[]>();

		char[][] listaDeCadenasTemporal = new char[dna.length][dna[0].length()];
		for (int i = 0; i < dna.length; i++) {
			listaDeCadenasTemporal[i]= dna[i].toCharArray();
		}
 

		for (int j = 3; j < listaDeCadenasTemporal.length; j++) {

			int posicionActual = 0;
			char[] temp = new char[listaDeCadenasTemporal.length];
			int altura = j;
			for (int i = listaDeCadenasTemporal.length-1;  altura >= 0; i--) {
				temp[posicionActual] = listaDeCadenasTemporal[altura][i];
				altura = altura-1;
				posicionActual =  posicionActual +1;
			}
			listaDeCadenas.add(temp);

		}
 

		for (int j = listaDeCadenasTemporal.length-2; (j-3) >= 0; j--) {
			char[] temp = new char[listaDeCadenasTemporal.length];
			int posicionActual = 0;
 			int base = j;
			for (int i = listaDeCadenasTemporal.length-1; base >= 0; i--) {
				temp[posicionActual] = listaDeCadenasTemporal[i][base];
				base = base-1;
				posicionActual =  posicionActual +1;
			}
			listaDeCadenas.add(temp);
		}

		return listaDeCadenas;
	}
}
