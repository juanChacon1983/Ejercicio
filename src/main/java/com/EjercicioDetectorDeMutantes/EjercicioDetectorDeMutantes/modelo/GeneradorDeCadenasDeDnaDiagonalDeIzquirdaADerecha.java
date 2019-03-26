package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo;


import java.util.ArrayList;
import java.util.List;

public class GeneradorDeCadenasDeDnaDiagonalDeIzquirdaADerecha implements GeneradorDeCadenasDeDna {

	@Override
	public List<char[]> armarCadenasDeAdn(String[] dna) {
		List<char[]> listaDeCadenas = new ArrayList<char[]>();

		char[][] listaDeCadenasTemporal = new char[dna.length][dna[0].length()];
		for (int i = 0; i < dna.length; i++) {
			listaDeCadenasTemporal[i]= dna[i].toCharArray();
		}
	 

		for (int j = 3; j < listaDeCadenasTemporal.length; j++) {

			char[] temp = new char[listaDeCadenasTemporal.length];
			int altura = j;
			for (int i = 0;  altura >= 0; i++) {
				temp[i] = listaDeCadenasTemporal[altura][i];
				altura = altura-1;
			}
			listaDeCadenas.add(temp);

		}

		for (int j = 1; (listaDeCadenasTemporal.length - j) >= 4; j++) {
			char[] temp = new char[listaDeCadenasTemporal.length];
			int posicionActual = 0;

			int altura = listaDeCadenasTemporal.length-1;
			int base = j;

			for (int i = altura; base < listaDeCadenasTemporal.length; i--) {
				temp[posicionActual] = listaDeCadenasTemporal[i][base];
				base = base+1;
				posicionActual =  posicionActual +1;
			}
			listaDeCadenas.add(temp);
		}

		return listaDeCadenas;
	}

}
