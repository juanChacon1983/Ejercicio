package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.ResultadoDeAnalisis;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.repositorio.ResultadoDeAnalisisRepositorio;

/**
 * Clase para testear la capa de repositorio de los resultados de analisis
 * 
 * @author jchacon
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ResultadoDeAnalisisRepositorioIntegracionTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ResultadoDeAnalisisRepositorio resultadoDeAnalisisRepositorio;
	
	
	/**
	 * test para validar que se guardan los resultados de los analisis y se pueden recuperar por el ID
	 */
	@Test
	public void quieroGuardarUnResultadoDeAnalisisYRecuperarloPorElIdTest() {
		ResultadoDeAnalisis resultadoDeAnalisis = new ResultadoDeAnalisis();
		resultadoDeAnalisis.setDna("ATGCGA");
		resultadoDeAnalisis.setEsMutante(true);
		entityManager.persist(resultadoDeAnalisis);
		entityManager.flush();
		ResultadoDeAnalisis resultadoDeAnalisisRecuperado = resultadoDeAnalisisRepositorio.findById(resultadoDeAnalisis.getId()).get();
		assertThat(resultadoDeAnalisisRecuperado).isEqualTo(resultadoDeAnalisis);
	}

	/**
	 * test para validar que se guardan los resultados de los analisis y se pueden recuperar por la cadena de DNA
	 */
	@Test
	public void quieroGuardarUnResultadoDeAnalisisYRecuperarloPorElDnaTest() {
		ResultadoDeAnalisis resultadoDeAnalisis = new ResultadoDeAnalisis();
		resultadoDeAnalisis.setDna("ATGCGA");
		resultadoDeAnalisis.setEsMutante(true);
		entityManager.persist(resultadoDeAnalisis);
		entityManager.flush();
		ResultadoDeAnalisis resultadoDeAnalisisRecuperado = resultadoDeAnalisisRepositorio.findByDna(resultadoDeAnalisis.getDna());
		assertThat(resultadoDeAnalisisRecuperado).isEqualTo(resultadoDeAnalisis);
	}
	
	
	/**
	 * Test donde se crea un resultado de analisis, se lo consulta para luego eliminarlo y comprobar que no existe mas.
	 */
	@Test
	public void quieroGuardarUnResultadoDeAnalisisparaRecuperarloListandoTodosLosAnalisisYDespuesEliminarloTest() {
		ResultadoDeAnalisis resultadoDeAnalisis = new ResultadoDeAnalisis();
		resultadoDeAnalisis.setDna("ATGCGA");
		resultadoDeAnalisis.setEsMutante(true);
		entityManager.persist(resultadoDeAnalisis);
		entityManager.flush();
		List<ResultadoDeAnalisis> listaDeResultados = resultadoDeAnalisisRepositorio.findAll();
		assertThat(listaDeResultados.contains(resultadoDeAnalisis)).isTrue();
		assertThat(listaDeResultados.size()).isEqualTo(1);
		resultadoDeAnalisisRepositorio.delete(resultadoDeAnalisis);
		listaDeResultados = resultadoDeAnalisisRepositorio.findAll();
		assertThat(listaDeResultados.size()).isEqualTo(0);
		
	}
	
}
