package org.serratec.projetoFinal_JAVA1.concretas;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.serratec.projetoFinal_JAVA1.abstratas.Pessoa;
import org.serratec.projetoFinal_JAVA1.enumerador.Parentesco;

public class DadosEntrada {
	Scanner lerArquivo = new Scanner(System.in);
	String ler = lerArquivo.next();

	public static String organizador(String ler) {
		String saida = "";
		int cont = 0;

		DateTimeFormatter formatadorArquivo = DateTimeFormatter.ofPattern("yyyyMMdd");
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		Funcionario f = null;
		Dependente d = null;

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(ler), "UTF-8"));
			String linha = in.readLine();

			while (linha != null) {
				String[] palavra = linha.split(";");
				String nome = palavra[0];
				String cpf = palavra[1];
				String data = palavra[2];
				LocalDate dtNascimento = LocalDate.parse(data, formatadorArquivo);
				Period difIdade = Period.between(dtNascimento, LocalDate.now());

				if (cont == 0) {
					f = new Funcionario();
					f.setNome(nome);
					f.setDtNascimento(dtNascimento);
					f.setCpf(cpf);

					if (pessoas.contains(f)) {
						System.out.println("Não foi possivel registrar o funcionario " + f.getNome() + " Cpf inválido");

						while (linha.isEmpty() == false) {
							linha = in.readLine();
							cont = -1;// teste2
						}

					} else {

						double salario = Double.parseDouble(palavra[3]);
						f.setSalarioBruto(salario);
						pessoas.add(f);
						funcionarios.add(f);

					}

				} else {
					Parentesco parentesco = Parentesco.valueOf(palavra[3]);
					d = new Dependente(nome, cpf, dtNascimento, parentesco);

					if (pessoas.contains(d)) {
						System.out.println("Não foi possivel registrar o dependente " + d.getNome() + " Cpf inválido");
					} else if (difIdade.getYears() < 18) {

						pessoas.add(d);
						f.setDependentes(d);

					}
				}
				linha = in.readLine();

				cont++;
				if (linha.isEmpty()) {
					cont = 0;
					linha = in.readLine();
					continue;
				}
			}
			in.close();

		} catch (IOException e) {
			String erro = "Deu errado";
			System.out.println("Erro ao acessar arquivo");
			return erro;

		} catch (NullPointerException ex) {
			for (Funcionario funcionario : funcionarios) {
				saida = saida + funcionario.toString() + "\n";
			}

			System.out.println("Operação concluida!");
		}
		return saida;
	}
}