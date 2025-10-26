package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Curso;
import com.curso.service.CursosService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

	@Autowired
	private CursosService cursosService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> listadoCursos() {
		return cursosService.getAllCursos();
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> crearCurso(@RequestBody Curso curso) {
		cursosService.addCurso(curso);
		return cursosService.getAllCursos();
	}

	@DeleteMapping(value = "/{codCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> borrarCurso(@PathVariable String codCurso) {
		cursosService.deleteCurso(codCurso);
		return cursosService.getAllCursos();
	}

	@PatchMapping(value = "/{codCurso}/duracion/{nuevaDuracion}")
	public void actualizarCurso(@PathVariable String codCurso, @PathVariable Double nuevaDuracion) {
		cursosService.updateCurso(codCurso, nuevaDuracion);
	}

	@GetMapping(value = "/{codCurso}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarCurso(@PathVariable String codCurso) {
		return cursosService.getCurso(codCurso);
	}

	@GetMapping(value = "/rangoPrecios")
	public List<Curso> listadoCursosPorPrecio(@RequestParam Double precioMin, @RequestParam Double precioMax) {
		return cursosService.getCursosByPrecioRange(precioMin, precioMax);
	}

	@GetMapping(value = "/nombre/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarCursoPorNombre(@PathVariable String nombre) {
		return cursosService.findByNombre(nombre);
	}

}
