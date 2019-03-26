package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.excepciones.CadenaDeDiferentesTamañosException;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.excepciones.DnaMalFormadoException;

/**
 * Clase encargada de analizar los DNA
 * 
 * @author jchacon
 *
 */
public class AnalizadorDeDna {

	/**
	 * Lista de generadores de cadenas para ser analizadas
	 */
	@Autowired
	List<GeneradorDeCadenasDeDna> listaDeGeneradoresdeCadenas;

	public AnalizadorDeDna() {

	}

	public AnalizadorDeDna(List<GeneradorDeCadenasDeDna> listaDeGeneradoresdeCadenas) {
		this.listaDeGeneradoresdeCadenas = listaDeGeneradoresdeCadenas;
	}

	/**
	 * Analiza si el DNA ingresado es mutante o humano
	 * 
	 * @param dnaParaAnalizar
	 * @return devuelve verdadero si el Dna es mutante y Falso si el Dna es humano
	 * @throws CadenaDeDiferentesTamañosException se lanza cuando se valida que el
	 *                                            Dna esta formado por palabras de
	 *                                            diferentes tamaños
	 * @throws DnaMalFormadoException             se lanza cuando el Dna esta mal
	 *                                            formado
	 */
	public boolean esMutante(String[] dnaParaAnalizar)
			throws CadenaDeDiferentesTamañosException, DnaMalFormadoException {

		validarTamañoDeLosElementos(dnaParaAnalizar);

		int cantidadDeCadenas = 0;
		for (GeneradorDeCadenasDeDna generadorDeCadenasDeAdn : listaDeGeneradoresdeCadenas) {

			List<char[]> listaDeCadenas = generadorDeCadenasDeAdn.armarCadenasDeAdn(dnaParaAnalizar);

			for (char[] valoresDeAdns : listaDeCadenas) {
				if (analizarCadena(valoresDeAdns, 0)) {
					cantidadDeCadenas = cantidadDeCadenas + 1;
					if (cantidadDeCadenas == 2) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Recibe una cadena para controlar si tiene 4 letras iguales consecutivas
	 * 
	 * @param cadena
	 * @param posicionInicial
	 * @return devuelve verdadero si la cadena tiene 4 letras iguales consecutivas
	 *         de caso contrario devuelve falso.
	 */
	private boolean analizarCadena(char[] cadena, int posicionInicial) {
		char primerValor = cadena[posicionInicial];
		int cantidadDeCadenasIguales = 0;
		if (posicionInicial + 4 > cadena.length)
			return false;
		for (int j = posicionInicial; j < cadena.length; j++) {
			if (cadena[j] == primerValor) {
				cantidadDeCadenasIguales = cantidadDeCadenasIguales + 1;
				if (cantidadDeCadenasIguales == 4)
					return true;
			} else {
				return analizarCadena(cadena, posicionInicial + 1);
			}
		}
		return false;
	};

	/**
	 * Valida que el Dna a analizar es correcto
	 * 
	 * @param dna
	 * @throws CadenaDeDiferentesTamañosException se lanza cuando se valida que el
	 *                                            Dna esta formado por palabras de
	 *                                            diferentes tamaños
	 * @throws DnaMalFormadoException             se lanza cuando el Dna esta mal
	 *                                            formado
	 */
	private void validarTamañoDeLosElementos(String[] dna)
			throws CadenaDeDiferentesTamañosException, DnaMalFormadoException {
		int tamaño = dna[0].length();

		for (String string : dna) {
			if (string.length() != tamaño)
				throw new CadenaDeDiferentesTamañosException();
		}

		if (tamaño != dna.length)
			throw new DnaMalFormadoException();

	}

}
