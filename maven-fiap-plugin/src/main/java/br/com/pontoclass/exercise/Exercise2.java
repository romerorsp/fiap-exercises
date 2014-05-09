package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise2 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Foi feita uma estatística em cinco cidades brasileiras para coletar dados sobre acidentes de trânsito.\n" +
			   "Foram obtidos os seguintes dados:\n" + 
			   "\ta. Código da cidade\n" +
			   "\tb. Número de veículos de passeio (em 2007).\n" +
			   "\tc. Número de acidentes de trânsito com vítimas (em 2007).\n" + 
			   "Deseja-se saber:\n" +
			   "\ta. Qual o maior e menor índice de acidentes de trânsito e a que cidades pertencem;\n" + 
			   "\tb. Qual a média de veículos nas cinco cidades juntas;\n" +
			   "\tc. Qual a média de acidentes de trânsito nas cidades com menos de 2.000 veículos de passeio.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 2");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 2";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}
