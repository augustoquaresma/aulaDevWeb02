package br.ufra.edu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nota {
	
	private Long id;
	private String aluno;
	private String disciplina;
	private Double valor;

}
