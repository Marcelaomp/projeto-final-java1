package org.serratec.projetoFinal_JAVA1.concretas;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.serratec.projetoFinal_JAVA1.abstratas.Pessoa;
import org.serratec.projetoFinal_JAVA1.interfaces.CalculoFolha;

public class Funcionario extends Pessoa implements CalculoFolha {

	protected double salarioBruto;
	protected double inss;
	protected double irrf;
	protected List<Dependente> dependentes = new ArrayList<Dependente>();

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(Dependente dependente) {
		this.dependentes.add(dependente);
	}

	public int contarDependentes() {
		return dependentes.size();
	}

	@Override
	public double descontarInss(double salarioBruto) {
		if (salarioBruto <= 1751.81) {
			inss = salarioBruto * 0.08;
		} else if (salarioBruto <= 2919.72) {
			inss = salarioBruto * 0.09;
		} else if (salarioBruto <= 5839.45) {
			inss = salarioBruto * 0.11;
		} else {
			inss = 5839.45 * 0.11;
		}
		return inss;
	}

	@Override
	public double descontarIr(double salarioBruto, double inss) {
		double baseIrrf = 0;
		baseIrrf = salarioBruto - inss - Dependente.DESCONTO_DEPENDENTE * contarDependentes();

		if (baseIrrf < 1903.98) {
			irrf = 0.00;
		} else if (baseIrrf <= 2826.65) {
			irrf = baseIrrf * 0.075 - 142.80;
		} else if (baseIrrf <= 3751.05) {
			irrf = baseIrrf * 0.15 - 354.80;
		} else if (baseIrrf <= 4664.68) {
			irrf = baseIrrf * 0.225 - 636.13;
		} else {
			irrf = baseIrrf * 0.275 - 869.36;
		}
		return irrf;
	}

	public double salarioLiquido() {
		double salarioLiquido = salarioBruto - inss - irrf;
		return salarioLiquido;
	}

	DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public String toString() {
		return nome + ";" + cpf + ";" + String.format("%.2f", descontarInss(salarioBruto)) + ";"
				+ String.format("%.2f", descontarIr(salarioBruto, inss)) + ";" + String.format("%.2f", salarioLiquido())
				+ ";";
	}
}
