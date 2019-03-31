package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.AnalizadorDeDna;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.GeneradorDeCadenasDeDna;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.GeneradorDeCadenasDeDnaDiagonalDeDerechaAIzquierda;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.GeneradorDeCadenasDeDnaDiagonalDeIzquirdaADerecha;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.GeneradorDeCadenasDeDnaHorizontal;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.GeneradorDeCadenasDeDnaVertical;

@SpringBootApplication
@EnableCaching
public class EjercicioDetectorDeMutantesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioDetectorDeMutantesApplication.class, args);
	}

	@Bean
	public AnalizadorDeDna crearAnalizadorDeDna() {
		return new AnalizadorDeDna();
	}

	@Bean
	public List<GeneradorDeCadenasDeDna> crearListaDeAnalizadores() {
		List<GeneradorDeCadenasDeDna> listaDeGeneradoresdeCadenas = new ArrayList<GeneradorDeCadenasDeDna>();
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaHorizontal());
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaVertical());
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaDiagonalDeDerechaAIzquierda());
		listaDeGeneradoresdeCadenas.add(new GeneradorDeCadenasDeDnaDiagonalDeIzquirdaADerecha());
		return listaDeGeneradoresdeCadenas;
	}

}
