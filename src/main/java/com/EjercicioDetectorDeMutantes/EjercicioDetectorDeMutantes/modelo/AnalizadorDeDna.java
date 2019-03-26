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
	 * @param dnaParaAnalizar arreglo de String que se va a analizar
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

			for (char[] cadenaDeAdn : listaDeCadenas) {
				cantidadDeCadenas = cantidadDeCadenas+analizarCadena(cadenaDeAdn, 0, cantidadDeCadenas);
				if (cantidadDeCadenas == 2) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Recibe y cuenta hasta dos ocurrencias grupos de 4 letras iguales.
	 * 
	 * @param cadena          string para analizar
	 * @param posicionInicial lugar desde donde se comienza a analizar el arreglo de
	 *                        letras
	 * @param cantidadActual  cantidad de cuatro letras consecutivas que se
	 *                        encontraron
	 * @return
	 */
	private int analizarCadena(char[] cadena, int posicionInicial, int cantidadActual) {
		char primerValor = cadena[posicionInicial];
		int cantidadDeLetrasIguales = 0;
		if (posicionInicial + 4 > cadena.length)
			return 0;
		for (int j = posicionInicial; j < cadena.length; j++) {
			if (cadena[j] == primerValor) {
				cantidadDeLetrasIguales = cantidadDeLetrasIguales + 1;
				if (cantidadDeLetrasIguales == 4 && ((j + 4) <= cadena.length) && cantidadActual + 1 < 2) {
					return 1 + analizarCadena(cadena, j + 1, cantidadActual + 1);
				} else if (cantidadDeLetrasIguales == 4) {
					return 1;
				}
			} else {
				return analizarCadena(cadena, posicionInicial + 1, cantidadActual);
			}
		}
		return 0;
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
