package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise8 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Faça um programa para ler o código, o sexo (M- masculino, F- feminino) e o número de horas/aula dadas mensalmente pelos professores de uma universidade, sabendo-se que cada hpra/aula vale R$ 30,00. Emita uma listagem contendo o código, o salário bruto e o salário líquido (levando em consideração os descontos explicados a seguir) de todos os professores.\n" +
			   "Mostre também a média dos salários líquidos dos professores do sexo masculino e a média dos salários líquidos dos professores do sexo feminino. Considere:\n" +
			   "\tDesconto para homens, 10% e, para mulheres, 5%.\n" +
			   "\tAs informações terminarão quando for lido o código = 99999.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 8");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 8";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}