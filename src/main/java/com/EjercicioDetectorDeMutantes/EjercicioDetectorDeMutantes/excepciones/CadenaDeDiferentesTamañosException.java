package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.excepciones;

/**
 * Excepcion para cuando los tamaños de las palabras del adn son diferentes
 * @author jchacon
 *
 */
public class CadenaDeDiferentesTamañosException extends Exception{

	private static final long serialVersionUID = 1L;

	public CadenaDeDiferentesTamañosException() {
		super("El tamaño de las cadenas de adn tiene que ser igual");
	}
}
