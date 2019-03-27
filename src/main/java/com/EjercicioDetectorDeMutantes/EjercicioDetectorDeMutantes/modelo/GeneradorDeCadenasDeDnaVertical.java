package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo;


import java.util.ArrayList;
import java.util.List;


public class GeneradorDeCadenasDeDnaVertical extends  GeneradorDeCadenasDeDna{

 
	@Override
	public List<String> armarCadenasDeAdn(String[] dna) {
		List<String> listaDeCadenas = new ArrayList<String>();
		
		char[][] matrizTemporal = generarMatriz (dna);
		
		for (int i = 0; i < matrizTemporal.length; i++) {
			StringBuffer temp = new StringBuffer();
			for (int j = 0; j < matrizTemporal.length; j++) {
				temp.append(matrizTemporal[j][i]);
			}
			listaDeCadenas.add(temp.toString());
		}
		
		return listaDeCadenas;
	}
	
	
}
