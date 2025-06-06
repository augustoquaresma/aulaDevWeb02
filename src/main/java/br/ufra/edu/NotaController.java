package br.ufra.edu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/notas")
public class NotaController {
	
	private List<Nota> lista = new ArrayList<>();
	
	@GetMapping
	public List<Nota> listar(){
		return lista;
	}
	
	@GetMapping("/{id}")
	public Nota retornaUmaNota(@PathVariable Long id) {
		return lista.stream().
				filter(nota -> nota.getId().equals(id)).
				findFirst().
				orElseThrow(
				()-> new 
				ResponseStatusException(HttpStatus.NOT_FOUND, 
						"Nota não encontrada"));
	}
	
	@PostMapping
	public Nota adicionar(@RequestBody Nota nota) {
		nota.setId((long) (lista.size()+1));
		lista.add(nota);
		return nota;
	}
	
	@PutMapping("/{id}")
	public Nota atualizar(@PathVariable Long id, 
			@RequestBody Nota notaNova) {
		Nota notaexistente = lista.stream().
				filter(nota -> nota.getId().equals(id)).
				findFirst().
				orElseThrow(
				()-> new 
				ResponseStatusException(HttpStatus.NOT_FOUND, 
						"Nota não encontrada"));
		notaexistente.setAluno(notaNova.getAluno());
		notaexistente.setDisciplina(notaNova.getDisciplina());
		notaexistente.setValor(notaNova.getValor());
		return notaexistente;
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		Nota nota = lista.stream().
				filter(n -> n.getId().equals(id)).
				findFirst().
				orElseThrow(
				()-> new 
				ResponseStatusException(HttpStatus.NOT_FOUND, 
						"Nota não encontrada"));
		lista.remove(nota);
	}

}
