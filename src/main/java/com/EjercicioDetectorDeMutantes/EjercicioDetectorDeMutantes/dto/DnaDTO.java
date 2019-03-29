package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO de entrada del servicio que analiza los DNA
 * @author jchacon
 *
 */
public class DnaDTO {

	@NotNull
	List< @NotNull @NotEmpty  @Size(min=4, message = "El tamaño minimo de las cadenas es 4 caracteres") @Pattern(regexp="[@ATGC\\s]*" ,	message = "las unicas letras permitidas son A,T,C,G y la lista no puede estar vacia") String> dna;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}
	
 
}
