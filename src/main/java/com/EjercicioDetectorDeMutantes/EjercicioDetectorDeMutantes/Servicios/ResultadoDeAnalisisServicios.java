package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.Servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.ResultadoDeAnalisis;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.repositorio.ResultadoDeAnalisisRepositorio;
 
@Service
public class ResultadoDeAnalisisServicios {

	@Autowired
	private ResultadoDeAnalisisRepositorio resultadoDeAnalisisRepositorio;

	public List<ResultadoDeAnalisis> listar() {
		return resultadoDeAnalisisRepositorio.findAll();
	}

	public Optional<ResultadoDeAnalisis> consultaPorID(long id) {
		return resultadoDeAnalisisRepositorio.findById(id);
	}
	
	public ResultadoDeAnalisis consultaPorCadenaDeDna(String dna) {
		return resultadoDeAnalisisRepositorio.findByDna(dna);
	}
	
	public ResultadoDeAnalisis guardar(ResultadoDeAnalisis resultadoDeAnalisis) {
		return resultadoDeAnalisisRepositorio.saveAndFlush(resultadoDeAnalisis);
	}

	public void eliminar(ResultadoDeAnalisis resultadoDeAnalisis) {
		resultadoDeAnalisisRepositorio.delete(resultadoDeAnalisis);
	}
}
