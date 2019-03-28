package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.dto;

/**
 * DTO de salida del servicio que devuelve las metricas de los analisis
 * 
 * @author jchacon
 *
 */
public class EstadisticasDTO {

	public int cantidadDeDnaMutantes;
	public int cantidadDeDnaHumano;
	public String proporcion;

	
	public EstadisticasDTO (int cantM, int cantH) {
		cantidadDeDnaMutantes = cantM;
		cantidadDeDnaHumano = cantH;
		calcularPropocion ();
	}
	
	private void calcularPropocion () {
		if (cantidadDeDnaHumano == 0 || cantidadDeDnaMutantes == 0) {
			proporcion =  "";
		}else {
			proporcion =  String.valueOf( cantidadDeDnaMutantes/cantidadDeDnaHumano);
		}
	}

}
