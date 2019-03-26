package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.dto;

/**
 * DTO de salida del servicio que devuelve las metricas de los analisis
 * @author jchacon
 *
 */
public class EstadisticasDTO {

	private int count_mutant_dna;
	private int count_human_dna;
	private long ratio;
	
	public int getCount_mutant_dna() {
		return count_mutant_dna;
	}
	public void setCount_mutant_dna(int count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	public int getCount_human_dna() {
		return count_human_dna;
	}
	public void setCount_human_dna(int count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	public long getRatio() {
		return ratio;
	}

	public void calcularRatio () {
		this.ratio = count_mutant_dna / count_human_dna;
	}
	
}
