package com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.Servicios.ResultadoDeAnalisisServicios;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.dto.DnaDTO;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.dto.EstadisticasDTO;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.excepciones.CadenaDeDiferentesTamañosException;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.excepciones.DnaMalFormadoException;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.AnalizadorDeDna;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.modelo.ResultadoDeAnalisis;
import com.EjercicioDetectorDeMutantes.EjercicioDetectorDeMutantes.util.ErrorInfo;

/**
 * Capa de controlador de la clae Resultado de analisis
 * 
 * @author jchacon
 *
 */
@RestController
@RequestMapping("/")
public class DnaControlador {

	@Autowired
	ResultadoDeAnalisisServicios resultadoDeAnalisisServicios;

	@Autowired
	AnalizadorDeDna detectorDeMutantes;

	/**
	 * Servicio que analiza el dna y lo guarda en la BD.
	 * 
	 * @param dnaDTO
	 * @return
	 */
	@RequestMapping(value = "mutant", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity analizarDna(@Valid @RequestBody DnaDTO dnaDTO) {

		ResultadoDeAnalisis resultadoDeAnalisis = resultadoDeAnalisisServicios.consultaPorCadenaDeDna(dnaDTO.getDna().toString());
		if (resultadoDeAnalisis == null) {
			try {
				resultadoDeAnalisis = new ResultadoDeAnalisis(dnaDTO.getDna().toString(),detectorDeMutantes.esMutante(dnaDTO.getDna().toArray(new String[dnaDTO.getDna().size()])));
				resultadoDeAnalisisServicios.guardar(resultadoDeAnalisis);
				if (resultadoDeAnalisis.esMutante()) {
					return new ResponseEntity(HttpStatus.OK);
				}
				return new ResponseEntity(HttpStatus.FORBIDDEN);
				
			} catch (CadenaDeDiferentesTamañosException | DnaMalFormadoException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
		}
		if (resultadoDeAnalisis.esMutante())
			return new ResponseEntity(HttpStatus.OK);
		return new ResponseEntity(HttpStatus.FORBIDDEN);
	}

	/**
	 * Servicio que devuelve las metricas de los Dna analizados hasta el momento
	 * 
	 * @return
	 */
	@RequestMapping(value = "stats", method = RequestMethod.GET, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity consultas() {

		List<ResultadoDeAnalisis> listaDeResultados = resultadoDeAnalisisServicios.listar();

		EstadisticasDTO estadisticasDTO = new EstadisticasDTO();
		estadisticasDTO.setCount_mutant_dna((int) listaDeResultados.stream()
				.filter((ResultadoDeAnalisis) -> ResultadoDeAnalisis.esMutante()).count());
		estadisticasDTO.setCount_human_dna(listaDeResultados.size() - estadisticasDTO.getCount_mutant_dna());
		estadisticasDTO.calcularRatio();
		return new ResponseEntity<EstadisticasDTO>(estadisticasDTO, HttpStatus.OK);
	}

	/**
	 * metodo para darle formato cuando se produce una excepcion de tipo
	 * MethodArgumentNotValidException
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> methodArgumentNotValidException(HttpServletRequest request,
			MethodArgumentNotValidException e) {

		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		StringBuilder errorMessage = new StringBuilder();
		fieldErrors.forEach(f -> errorMessage.append(f.getDefaultMessage()));

		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), "Bad Request", errorMessage.toString(),
				request.getRequestURI());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);

	}

}
