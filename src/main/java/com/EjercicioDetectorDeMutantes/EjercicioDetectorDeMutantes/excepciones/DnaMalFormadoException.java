package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.excepciones;

/**
 * Excepcion para cuando los dna estan mal formados y las palabras tienen mas
 * letras que cantidad de palabras que componen el dna
 * 
 * @author jchacon
 *
 */
public class DnaMalFormadoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DnaMalFormadoException() {
		super("El tamaño de las palabras que forman el dna tiene que ser igual a la cantidad de palabras que lo componen");
	}

}
