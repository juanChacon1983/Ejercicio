package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo;

import java.util.ArrayList;
import java.util.List;

public class GeneradorDeCadenasDeDnaDiagonalDeDerechaAIzquierda extends GeneradorDeCadenasDeDna {

	@Override
	public List<String> armarCadenasDeAdn(String[] dna) {
		List<String> listaDeCadenas = new ArrayList<String>();

		char[][] matrizTemporal = generarMatriz (dna);
		
		for (int j = 3; j < matrizTemporal.length; j++) {
			StringBuffer temp = new StringBuffer();
			int altura = j;
			for (int i = matrizTemporal.length - 1; altura >= 0; i--) {
				temp.append(matrizTemporal[altura][i]);
				altura = altura - 1;
			}
			listaDeCadenas.add(temp.toString());

		}

		for (int j = matrizTemporal.length - 2; (j - 3) >= 0; j--) {
			StringBuffer temp = new StringBuffer();
			int base = j;
			for (int i = matrizTemporal.length - 1; base >= 0; i--) {
				temp.append(matrizTemporal[i][base]);
				base = base - 1;
			}
			listaDeCadenas.add(temp.toString());
		}

		return listaDeCadenas;
	}
}
