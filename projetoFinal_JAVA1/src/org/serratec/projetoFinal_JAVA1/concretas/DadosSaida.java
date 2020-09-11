package org.serratec.projetoFinal_JAVA1.concretas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class DadosSaida {

	public static void imprimir(String escrever, String ler) {
		OutputStreamWriter arquivo = null;

		try {
			arquivo = new OutputStreamWriter(new FileOutputStream(escrever + ".csv", true));
			arquivo.append(DadosEntrada.organizador(ler));
			arquivo.close();
		} catch (IOException d) {
			System.out.println("Não foi possível gravar o arquivo!");
		}
	}
}