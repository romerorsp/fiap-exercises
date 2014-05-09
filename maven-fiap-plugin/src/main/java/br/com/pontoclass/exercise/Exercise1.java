package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise1 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Um funcionário de uma empresa recebe aumento salarial anualmente.\n" +
	           "Sabe-se que esse funcionário foi contratado em 2005, com salário inicial de R$ 1.000,00.\n" +
			   "Em 2006, ele recebeu aumento de 1,5% sobre seu salário inicial.\n" +
	           "A partir de 2007 (inclusive), os aumentos salariais sempre corresponderam ao dobro do percentual do ano anterior.\n" +
			   "Faça um programa que determine o salário atual desse funcionário.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 1");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 1";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}
