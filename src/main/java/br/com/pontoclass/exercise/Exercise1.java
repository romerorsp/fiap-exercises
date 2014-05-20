package br.com.pontoclass.exercise;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Exercise1 extends AbstractExercise {

	@SuppressWarnings("unused") private Map<String, String> input;

	private double salario;

	public String getStatement() {
		return "Um funcionário de uma empresa recebe aumento salarial anualmente.\n" +
	           "Sabe-se que esse funcionário foi contratado em 2005, com salário inicial de R$ 1.000,00.\n" +
			   "Em 2006, ele recebeu aumento de 1,5% sobre seu salário inicial.\n" +
	           "A partir de 2007 (inclusive), os aumentos salariais sempre corresponderam ao dobro do percentual do ano anterior.\n" +
			   "Faça um programa que determine o salário atual desse funcionário.\n";
	}

	public List<String> getInputNames() {
		return Collections.emptyList();
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return String.format("O salário atual do funcionário é de R$ %.2f.", salario);
	}

	public void solve() {
		int ano = 2005;
		float aumento = (1.5f/100);
		this.salario = 1000.00;

		Calendar tempo = Calendar.getInstance();
		tempo.setTime(new Date(System.currentTimeMillis()));
		int anoAtual = tempo.get(Calendar.YEAR);
		while(++ano <= anoAtual) {
			salario *= (1.0f + aumento);
			aumento *= 2;
		}
	}
}
