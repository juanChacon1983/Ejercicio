package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.dto;

/**
 * DTO de salida del servicio que devuelve las metricas de los analisis
 * 
 * @author jchacon
 *
 */
public class EstadisticasDTO {

	private int cantidadDeDnaMutantes;
	private int cantidadDeDnaHumano;
	private long proporcion;

	public int getCount_mutant_dna() {
		return cantidadDeDnaMutantes;
	}

	public void setCount_mutant_dna(int count_mutant_dna) {
		this.cantidadDeDnaMutantes = count_mutant_dna;
	}

	public int getCount_human_dna() {
		return cantidadDeDnaHumano;
	}

	public void setCount_human_dna(int count_human_dna) {
		this.cantidadDeDnaHumano = count_human_dna;
	}

	public long getRatio() {
		return proporcion;
	}

	public void calcularRatio() {
		if (cantidadDeDnaHumano == 0) {
			this.proporcion = 0;
		} else {
			this.proporcion = cantidadDeDnaMutantes / cantidadDeDnaHumano;
		}
	}

}
