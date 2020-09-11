package org.serratec.projetoFinal_JAVA1;

import java.util.Scanner;

import org.serratec.projetoFinal_JAVA1.concretas.DadosEntrada;
import org.serratec.projetoFinal_JAVA1.concretas.DadosSaida;

public class Main {

	public static void main(String[] args) {
		System.out.print("Digite o diretorio e o nome do arquivo: ");
		Scanner lerArquivo = new Scanner(System.in);
		String ler = lerArquivo.next();
		String continua = DadosEntrada.organizador(ler);

		if (continua != "Deu errado") {
			System.out.print("Digite o nome do arquivo de saida: ");
			String nomeSaida = lerArquivo.next();
			DadosSaida.imprimir(nomeSaida, ler);
		}
		lerArquivo.close();
	}
}