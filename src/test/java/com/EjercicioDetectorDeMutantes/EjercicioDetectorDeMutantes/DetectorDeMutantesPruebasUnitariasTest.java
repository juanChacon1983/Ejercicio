package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.excepciones.CadenaDeDiferentesTamañosException;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.excepciones.DnaMalFormadoException;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.AnalizadorDeDna;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.GeneradorDeCadenasDeDna;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.GeneradorDeCadenasDeDnaDiagonalDeDerechaAIzquierda;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.GeneradorDeCadenasDeDnaDiagonalDeIzquirdaADerecha;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.GeneradorDeCadenasDeDnaHorizontal;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.GeneradorDeCadenasDeDnaVertical;

public class DetectorDeMutantesPruebasUnitariasTest {

	/**
	 * Test para comprobar que cuando se analiza un dna mutante devuelve verdadero
	 * 
	 * @throws CadenaDeDiferentesTamañosException
	 * @throws DnaMalFormadoException
	 */
	@Test
	public void quieroAnalizarUnaDnaMutanteYElResultadoSeaVerdaderoTest()
			throws CadenaDeDiferentesTamañosException, DnaMalFormadoException {

		List<GeneradorDeCadenasDeDna> listaDeGeneradoresdeCadenas = new ArrayList<GeneradorDeCadenasDeDna>();
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaHorizontal());
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaVertical());
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaDiagonalDeDerechaAIzquierda());
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaDiagonalDeIzquirdaADerecha());
		AnalizadorDeDna analizadorDeDna = new AnalizadorDeDna(listaDeGeneradoresdeCadenas);

		String[] dna = { "AAAAGGGG", "ATGCGAGA", "ATGCGATT", "ATGCGGGG", "ATGCGAAA", "ATGCGTCC", "ATGCGAAA",
				"ATGCGTCC" };
		assertTrue(analizadorDeDna.esMutante(dna));
	}

	/**
	 * Test para comprobar que cuando se analiza una cadena Humana devuelve falso
	 * 
	 * @throws CadenaDeDiferentesTamañosException
	 * @throws DnaMalFormadoException
	 */
	@Test
	public void quieroAnalizarUnaDnaHumanoYElResultadoSeaFalsoTest()
			throws CadenaDeDiferentesTamañosException, DnaMalFormadoException {

		List<GeneradorDeCadenasDeDna> listaDeGeneradoresdeCadenas = new ArrayList<GeneradorDeCadenasDeDna>();
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaHorizontal());
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaVertical());
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaDiagonalDeDerechaAIzquierda());
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaDiagonalDeIzquirdaADerecha());
		AnalizadorDeDna analizadorDeDna = new AnalizadorDeDna(listaDeGeneradoresdeCadenas);

		String[] dna = { "GACCCG", "CAGGGC", "TTATGT", "AGAGGT", "TACTGT", "ACCCTG" };
		assertFalse(analizadorDeDna.esMutante(dna));
	}

	/**
	 * Test para comprobar que cuando se analiza un dna con palabras de diferente
	 * tamaño se lanza la excepcion CadenaDeDiferentesTamañosException
	 * 
	 * @throws CadenaDeDiferentesTamañosException
	 * @throws DnaMalFormadoException
	 */
	@Test(expected = CadenaDeDiferentesTamañosException.class)
	public void quieroAnalizarUnDnaConPalabrasDeDiferentesTamañosYQueElSeLanceLaExcepcioCadenaDeDiferentesTamañosExceptionnTest()
			throws CadenaDeDiferentesTamañosException, DnaMalFormadoException {

		AnalizadorDeDna analizadorDeDna = new AnalizadorDeDna();

		String[] dna = { "ATGCGA", "CAGGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		assertTrue(analizadorDeDna.esMutante(dna));
	}

	/**
	 * Test para comprobar que cuando se analiza un dna donde las palabras tienen
	 * mas letras que la cantidad total de palabras se lanza la excepcion
	 * DnaMalFormadoException
	 * 
	 * @throws CadenaDeDiferentesTamañosException
	 * @throws DnaMalFormadoException
	 */
	@Test(expected = DnaMalFormadoException.class)
	public void quieroAnalizarUnDnaInvalidoYQueElSeLanceLaExcepcioDnaMalFormadoExceptionTest()
			throws CadenaDeDiferentesTamañosException, DnaMalFormadoException {

		AnalizadorDeDna analizadorDeDna = new AnalizadorDeDna();

		String[] dna = { "ATGCGA", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		assertTrue(analizadorDeDna.esMutante(dna));
	}

	/**
	 * Test para comprobar que se generan las cadenas correctas con las diagonales
	 * de derecha a izquierda
	 */
	@Test
	public void quieroIngresarUnaDNAyQuieroQueElAnalizadorDeDiagonalesDeDerechaAIzquierdaMeDeVuelvaLasCadenasCorrectas() {

		GeneradorDeCadenasDeDnaDiagonalDeIzquirdaADerecha generador = new GeneradorDeCadenasDeDnaDiagonalDeIzquirdaADerecha();
		String[] dna = { "ATGCG", "TTATG", "AGAAG", "CCCCT", "CCCCT" };
		List<String> listtaResultado = generador.armarCadenasDeAdn(dna);
		assertEquals(3, listtaResultado.size());
		assertTrue(listtaResultado.containsAll(Arrays.asList("CGAC", "CCATG", "CCAG")));
	}

	/**
	 * Test para comprobar que se generan las cadenas correctas con las diagonales
	 * de izquierda a derecha
	 */
	@Test
	public void quieroIngresarUnaDNAyQuieroQueElAnalizadorDeDiagonalesDeIzquierdaADerechaMeDeVuelvaLasCadenasCorrectas() {

		GeneradorDeCadenasDeDnaDiagonalDeDerechaAIzquierda generador = new GeneradorDeCadenasDeDnaDiagonalDeDerechaAIzquierda();
		String[] dna = { "ATGCG", "TTATG", "AGAAG", "CCCCT", "CCCCT" };
		List<String> listtaResultado = generador.armarCadenasDeAdn(dna);
		assertEquals(3, listtaResultado.size());
		assertTrue(listtaResultado.containsAll(Arrays.asList("TAAT", "TCATA", "CCGT")));
	}

	/**
	 * Test para comprobar que se generan las cadenas correctas de manera horizontal
	 */
	@Test
	public void quieroIngresarUnaDNAyQuieroQueElAnalizadorDeDnaHorizontalMeDeVuelvaLasCadenasCorrectas() {

		GeneradorDeCadenasDeDnaHorizontal generador = new GeneradorDeCadenasDeDnaHorizontal();
		String[] dna = { "ATGCG", "TTATG", "AGAAG", "CCCCT", "CCCCT" };
		List<String> listtaResultado = generador.armarCadenasDeAdn(dna);
		assertEquals(5, listtaResultado.size());
		assertTrue(listtaResultado.containsAll(Arrays.asList("ATGCG", "TTATG", "AGAAG", "CCCCT", "CCCCT")));
	}

	/**
	 * Test para comprobar que se generan las cadenas correctas de manera horizontal
	 */
	@Test
	public void quieroIngresarUnaDNAyQuieroQueElAnalizadorDeDnaVerticalMeDeVuelvaLasCadenasCorrectas() {

		GeneradorDeCadenasDeDnaVertical generador = new GeneradorDeCadenasDeDnaVertical();
		String[] dna = { "ATGCG", "TTATG", "AGAAG", "CCCCT", "CCCCT" };
		List<String> listtaResultado = generador.armarCadenasDeAdn(dna);
		assertEquals(5, listtaResultado.size());
		assertTrue(listtaResultado.containsAll(Arrays.asList("ATACC", "TTGCC", "GAACC", "CTACC", "GGGTT")));
	}
}