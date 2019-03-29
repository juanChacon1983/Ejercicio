package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo;
import java.util.List;

public abstract class  GeneradorDeCadenasDeDna {

	public abstract List<String> armarCadenasDeAdn(String[] dna);

	protected char[][] generarMatriz (String[] dna) {
		
		char[][] matriz = new char[dna.length][dna[0].length()];
		for (int i = 0; i < dna.length; i++) {
			matriz[i] = dna[i].toCharArray();
		}
		return matriz;
	}
	
}
