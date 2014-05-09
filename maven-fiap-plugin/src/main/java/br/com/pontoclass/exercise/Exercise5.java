package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise5 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Foi feita uma pesquisa para determinar o índice de mortalidade infantil em certo período. Faça um programa que:\n" +
			   "\t\ta. Leia o número de crianças nascidas no período;\n" +
			   "\t\tb. Identifique sexo (M ou F) e o tempo de vida de cada criança nascida.\n" + 
			   "\tO programa deve calcular e mostrar:\n" +
			   "\t\ta. A percentagem de crianças do sexo feminino mortas no período;\n" + 
			   "\t\tb. A percentagem de crianças do sexo masculino mortas no período;\n" +
			   "\t\tc. A percentagem de crianças que viveram 24 meses ou menos no período.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 5");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 5";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}