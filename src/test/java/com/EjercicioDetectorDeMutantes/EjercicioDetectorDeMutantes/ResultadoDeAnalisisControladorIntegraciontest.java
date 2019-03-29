package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.Servicios.ResultadoDeAnalisisServicios;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.controlador.DnaControlador;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.dto.DnaDTO;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.ResultadoDeAnalisis;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.repositorio.ResultadoDeAnalisisRepositorio;
import com.fasterxml.jackson.databind.ObjectMapper;

 

/**
 * Clase para testear la capa de controlador
 * 
 * @author jchacon
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DnaControlador.class)
public class ResultadoDeAnalisisControladorIntegraciontest {

	@Autowired
	private MockMvc mvc;

	@MockBean
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

		ResultadoDeAnalisis otroResultadoDeAnalisisMas = new ResultadoDeAnalisis();
		otroResultadoDeAnalisisMas.setId(3);
		otroResultadoDeAnalisisMas.setDna("ATGCGA");
		otroResultadoDeAnalisisMas.setEsMutante(false);

		List<ResultadoDeAnalisis> listaDeResultados = new ArrayList<ResultadoDeAnalisis>(
				Arrays.asList(resultadoDeAnalisis, otroResultadoDeAnalisis, otroResultadoDeAnalisisMas));

		Mockito.when(resultadoDeAnalisisServicios.listar()).thenReturn(listaDeResultados);
			
		Mockito.when(resultadoDeAnalisisServicios.consultaPorCadenaDeDna(Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG").toString())).thenReturn(resultadoDeAnalisis);
 

	}

	/**
	 * Se analiza DNA mutante y el servicio devuelve HTTP 200-OK
	 * @throws Exception
	 */
	@Test
	public void quieroIngresarUnDnaYQueMeDevuelvaQueEsMutanteTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		DnaDTO dnaDTO = new DnaDTO();
		//dnaDTO.setDna(Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTT"));
		dnaDTO.setDna(Arrays.asList("AAAAGGGG","ATGCGAGA","ATGCGATT","ATGCGGGG","ATGCGAAA" ,"ATGCGTCC","ATGCGAAA" ,"ATGCGTCC"));
		 
		mvc.perform(post("/mutant").content(mapper.writeValueAsString(dnaDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	/**
	 * Se analiza un DNA humano y el servicio devuelve 403-Forbidden
	 * @throws Exception
	 */
	@Test
	public void quieroIngresarUnDnaYQueMeDevuelvaQueEsHumanoTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		DnaDTO dnaDTO = new DnaDTO();
		dnaDTO.setDna(Arrays.asList("ATGC", "CAGT", "TTAT", "TTAT"));

		mvc.perform(post("/mutant").content(mapper.writeValueAsString(dnaDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());

	}

	/**
	 * Valida que no se puedan ingresar letras no permitidas
	 * @throws Exception
	 */
	@Test
	public void quieroIngrsarUnDnaConLetrasInvalidasYQueMeDevuelvaBadRequestTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		DnaDTO dnaDTO = new DnaDTO();
		dnaDTO.setDna(Arrays.asList("JJJJ", "JJJJ", "JJJJ", "JJJJ"));

		mvc.perform(post("/mutant").content(mapper.writeValueAsString(dnaDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage()
				.compareTo("las unicas letras permitidas son A,T,C,G y la lista no puede estar vacialas unicas letras permitidas son A,T,C,G");

	}

	/**
	 * Valida que no se puedan ingresar cadenas que contengan menos letras que la cantidad de palabras
	 * @throws Exception
	 */
	@Test
	public void quieroIngrsarUnDnaConMenosPalabrasQueCantidadDeLetrasPorPalabrasYQueMeDevuelvaBadRequestTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		DnaDTO dnaDTO = new DnaDTO();
		dnaDTO.setDna(Arrays.asList("ATGGG", "CAGGG", "TTAGG", "TTAGG"));

		mvc.perform(post("/mutant").content(mapper.writeValueAsString(dnaDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage()
				.compareTo("el dna tiene que ser igual a la cantidad de palabras que lo componen");

	}

	/**
	 * valida que las palabras tengan por lo menos 4 caracteres
	 * @throws Exception
	 */
	@Test
	public void quieroIngrsarUnDnaConPalabrasDeMenosDeCuatroLetrasYQueMeDevuelvaBadRequestTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		DnaDTO dnaDTO = new DnaDTO();
		dnaDTO.setDna(Arrays.asList("ATG", "CAG", "TGG", "TTG"));

		mvc.perform(post("/mutant").content(mapper.writeValueAsString(dnaDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage()
				.compareTo("El tamaño minimo de las cadenas es 4 caracteres");
 
	}

	/**
	 * valida que se tenga que envias si o si una cadenas
	 * @throws Exception
	 */
	@Test
	public void quieroIngrsarUnDnVacioYQueMeDevuelvaBadRequestTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		DnaDTO dnaDTO = new DnaDTO();
		mvc.perform(post("/mutant").content(mapper.writeValueAsString(dnaDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

	/**
	 * valida que no se puedan mandar DNA formad por palabras de diferente tamaño
	 * @throws Exception
	 */
	@Test
	public void quieroIngrsarUnDnaConPalabrasDeDiferentesTamañosYQueMeDevuelvaBadRequestTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		DnaDTO dnaDTO = new DnaDTO();
		dnaDTO.setDna(Arrays.asList("CAGGG", "CAGGG", "CAGGG", "CAGG"));

		mvc.perform(post("/mutant").content(mapper.writeValueAsString(dnaDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn().getResolvedException().getMessage()
				.compareTo("El tamaño de las cadenas de adn tiene que ser igual");

	}
	
	/**
	 * Valida que si consulta dos veces la misma cadena devuelve el mismo resultado
	 * @throws Exception
	 */
	@Test
	public void quieroIngresarDosVecesElMismoDnaYDaElMismoResultadoBDTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		DnaDTO dnaDTO = new DnaDTO();
		dnaDTO.setDna(Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));

		mvc.perform(post("/mutant").content(mapper.writeValueAsString(dnaDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		mvc.perform(post("/mutant").content(mapper.writeValueAsString(dnaDTO)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	/**
	 * Valida que el servicio de los stats devuelve 400
	 * @throws Exception
	 */
	@Test
	public void quieroConsultarLasEstadisticasYQueMeDevuelva200OKTest() throws Exception {
		mvc.perform(get("/stats").contentType(MediaType.APPLICATION_JSON_VALUE))
		 .andExpect(status().isOk());
	}
	
	/**
	 * Valida que cuando ejecuto un servicio que ya existe en la BD no vuelva a hacer el calculo y me devuelva el resultado anterior
	 * @throws Exception
	 */
	@Test
	public void quieroQueCuandoIngreseUnDnaQueExisteEnLaBDNoloGuardeNuevamenteTest() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		DnaDTO dnaDTO = new DnaDTO();
		dnaDTO.setDna(Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));

		mvc.perform(post("/mutant").content(mapper.writeValueAsString(dnaDTO)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		Mockito.verify(resultadoDeAnalisisServicios, times(0)).guardar(Mockito.any(ResultadoDeAnalisis.class));

	}

}
