package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise9 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Faça um programa que receba vários números, calcule e mostre:\n" +
			   "\t\t* A soma dos números digitados;\n" +
			   "\t\t* A quantidade dos números digitados;\n" +
			   "\t\t* A média dos números digitados;\n" +
			   "\t\t* O maior número digitado;\n" +
			   "\t\t* O menor número digitado;\n" +
			   "\t\t* A média dos números pares;\n" +
			   "\t\t* A percentagem dos números ímpares entre todos os números digitados.\n" +
			   "\tFinalize a entrada de dados com a digitação do número 30.000.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 9");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 9";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}