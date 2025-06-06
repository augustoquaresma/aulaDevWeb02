package br.ufra.edu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	private INotas notas;
	
	@GetMapping
	public List<Nota> listar(){
		return notas.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nota> retornaUmaNota(@PathVariable Long id) {
		return notas.findById(id)
			      .map(ResponseEntity::ok)
			      .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Nota adicionar(@RequestBody Nota nota) {
		return notas.save(nota);
	}
	
	 @PutMapping("/{id}")
	    public ResponseEntity<Nota> atualizar(@PathVariable Long id, @RequestBody Nota notaAtualizada) {
	        return notas.findById(id)
	                .map(nota -> {
	                    nota.setAluno(notaAtualizada.getAluno());
	                    nota.setDisciplina(notaAtualizada.getDisciplina());
	                    Nota atualizado = notas.save(nota);
	                    return ResponseEntity.ok(atualizado);
	                })
	                .orElse(ResponseEntity.notFound().build());
	    }
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		Nota nota = notas.findById(id).orElseThrow(()-> new 
				ResponseStatusException(HttpStatus.NOT_FOUND, 
						"Nota não encontrada"));
		notas.delete(nota);
	}

}
