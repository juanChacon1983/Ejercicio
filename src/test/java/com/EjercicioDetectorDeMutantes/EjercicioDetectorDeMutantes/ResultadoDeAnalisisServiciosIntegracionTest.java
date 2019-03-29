package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.Servicios.ResultadoDeAnalisisServicios;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.ResultadoDeAnalisis;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.repositorio.ResultadoDeAnalisisRepositorio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

/**
 * Clase para testear la capa de servicios de los resultados de analisis
 * 
 * @author jchacon
 *
 */
@RunWith(SpringRunner.class)
public class ResultadoDeAnalisisServiciosIntegracionTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public ResultadoDeAnalisisServicios resultadoDeAnalisisServicios() {
			return new ResultadoDeAnalisisServicios();
		}
	}

	@Autowired
	private ResultadoDeAnalisisServicios resultadoDeAnalisisServicios;

	@MockBean
	private ResultadoDeAnalisisRepositorio resultadoDeAnalisisRepositorio;

	/**
	 * Configuracion inicial antes de comenzar los test
	 */
	@Before
	public void setUp() {

		ResultadoDeAnalisis resultadoDeAnalisis = new ResultadoDeAnalisis();
		resultadoDeAnalisis.setId(1);
		resultadoDeAnalisis.setDna("ATGCGAG");
		resultadoDeAnalisis.setEsMutante(true);

		ResultadoDeAnalisis otroResultadoDeAnalisis = new ResultadoDeAnalisis();
		otroResultadoDeAnalisis.setId(2);
		otroResultadoDeAnalisis.setDna("ATGCGA");
		otroResultadoDeAnalisis.setEsMutante(true);

		List<ResultadoDeAnalisis> listaDeResultados = new ArrayList<ResultadoDeAnalisis>(
				Arrays.asList(resultadoDeAnalisis, otroResultadoDeAnalisis));

		Mockito.when(resultadoDeAnalisisRepositorio.findById((long) 1)).thenReturn(Optional.of(resultadoDeAnalisis));
		Mockito.when(resultadoDeAnalisisRepositorio.findByDna("ATGCGAG")).thenReturn(resultadoDeAnalisis);
		Mockito.when(resultadoDeAnalisisRepositorio.findAll()).thenReturn(listaDeResultados);
		Mockito.when(resultadoDeAnalisisRepositorio.saveAndFlush(Mockito.any(ResultadoDeAnalisis.class)))
				.thenReturn(resultadoDeAnalisis);

	}

	/**
	 * Test que consulta un resultado de Analsis por id
	 */
	@Test
	public void quieroBuscarUnResultadoDeanalaisisPorIdTest() {

		ResultadoDeAnalisis resultadoDeAnalisis = resultadoDeAnalisisServicios.consultaPorID(1).get();
		assertThat(resultadoDeAnalisis.getId()).isEqualTo(1);
	}
	
	/**
	 * Test que consulta un resultado de Analsis por cadena de Dna
	 */
	@Test
	public void quieroBuscarUnResultadoDeanalaisisPorDnaTest() {

		ResultadoDeAnalisis resultadoDeAnalisis = resultadoDeAnalisisServicios.consultaPorCadenaDeDna("ATGCGAG");
		assertThat(resultadoDeAnalisis.getDna()).isEqualTo("ATGCGAG");
	}

	/**
	 * Test para el metodo eliminar un Resultado de Analsis
	 */
	@Test
	public void quieroEliminarUnResultadoDeAnalisisQueConsultoYQueSeElimineUnSoloRegistroTest() {

		ResultadoDeAnalisis resultadoDeAnalisis = resultadoDeAnalisisServicios.consultaPorID(1).get();
		resultadoDeAnalisisServicios.eliminar(resultadoDeAnalisis);
		Mockito.verify(resultadoDeAnalisisRepositorio, times(1)).delete(resultadoDeAnalisis);

	}
 

	/**
	 * Test para consultar todos los Resultados de Analisis guardados en la BD
	 */
	@Test
	public void consultarTodosLosResultadosDeAdnTest() {

		List<ResultadoDeAnalisis> resultados = resultadoDeAnalisisServicios.listar();
		assertThat(resultados.size()).isGreaterThan(0);

	}
}
