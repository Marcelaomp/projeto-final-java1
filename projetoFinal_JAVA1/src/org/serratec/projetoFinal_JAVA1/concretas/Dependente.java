package org.serratec.projetoFinal_JAVA1.concretas;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.serratec.projetoFinal_JAVA1.abstratas.Pessoa;
import org.serratec.projetoFinal_JAVA1.enumerador.Parentesco;
import org.serratec.projetoFinal_JAVA1.exception.DependenteException;

public class Dependente extends Pessoa {

	private Parentesco tipoParentesco;

	public static final double DESCONTO_DEPENDENTE = 189.59;

	public Dependente(String nome, String cpf, LocalDate dtNascimento, Parentesco tipoParentesco) {
		super();
		super.nome = nome;
		super.cpf = cpf;

		try {
			this.tipoParentesco = tipoParentesco;
			Period difIdade = Period.between(dtNascimento, LocalDate.now());
			if (difIdade.getYears() < 18) {
				super.dtNascimento = dtNascimento;
			} else {
				throw new DependenteException(
						"Impossível cadastrar o dependente " + super.nome + ". Dependente maior que 18 anos.");
			}
		} catch (DependenteException d) {
			System.out.println(d.getMessage());
		}
	}

	DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");

}
