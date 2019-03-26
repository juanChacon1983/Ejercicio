package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo;


import java.util.ArrayList;
import java.util.List;


public class GeneradorDeCadenasDeDnaVertical implements  GeneradorDeCadenasDeDna{

 
	@Override
	public List<char[]> armarCadenasDeAdn(String[] dna) {
		List<char[]> listaDeCadenas = new ArrayList<char[]>();
		
		char[][] listaDeCadenasTemporal = new char[dna.length][dna.length];

		for (int i = 0; i < dna.length; i++) {
			listaDeCadenasTemporal[i]= dna[i].toCharArray();
		}
		
		
		for (int i = 0; i < listaDeCadenasTemporal.length; i++) {
			char[] temp = new char[listaDeCadenasTemporal.length];
			for (int j = 0; j < listaDeCadenasTemporal.length; j++) {
				temp[j] =   listaDeCadenasTemporal[j][i];
			}
			listaDeCadenas.add(temp);
		}
		
		return listaDeCadenas;
	}
	
	
}
