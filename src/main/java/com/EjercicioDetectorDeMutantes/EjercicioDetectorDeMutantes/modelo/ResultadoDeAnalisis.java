package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ResultadoDeAnalisis {

	@Id
	@GeneratedValue
	private long id;
	private String dna;
	private boolean esMutante;
	
	public ResultadoDeAnalisis ( ) {

	}

	
	public ResultadoDeAnalisis (String dna, boolean esMutate) {
		this.dna = dna;
		this.esMutante = esMutate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}


	public boolean esMutante() {
		return esMutante;
	}


	public void setEsMutante(boolean esMutante) {
		this.esMutante = esMutante;
	}
 
 }
