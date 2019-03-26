package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.ResultadoDeAnalisis;
 
 
 

public interface ResultadoDeAnalisisRepositorio  extends JpaRepository<ResultadoDeAnalisis, Long>{

	ResultadoDeAnalisis findByDna(String titulo);
	
}
