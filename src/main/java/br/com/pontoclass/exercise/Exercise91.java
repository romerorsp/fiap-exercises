package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise91 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Faça um programa que apresente o menu de opções a seguir, permita ao usuário escolher a opção desejada, receba os dados necessários para executar a operação e mostre o resultado. Verifique a possibilidade de opção inválida e não se preocupe com restrições do tipo salário inválido.\n" +
			   "\tMenu de opções:\n" +
			   "\t\t1. Imposto\n" +
			   "\t\t2. Novo salário\n" +
			   "\t\t3. Classificação\n" +
			   "\t\t4. Finalizar programa\n" +
			   "\tDigite a opção desejada:\n" +
			   "\tNa opção 1: receber o salário de um funcionário, calcular e mostrar o valor do imposto usando as regras a seguir:\n" +
			   "\t\t          Salário        | % de imposto\n" +
			   "\t\t   Menor que R$ 500,00   |      5\n" +
			   "\t\tDe R$ 500,00 a R$ 850,00 |     10\n" +
			   "\t\t    Acima de R$ 850,00   |     15\n" +
			   "\tNa opção 2: receber o salário de um funcionário, calcular e mostrar o valor do novo salário usando as regras a seguir:\n" +
			   "\t\t                    Salários                       | AUMENTO\n" +
			   "\t\t              Maior que R$ 1500,00                 |  R$ 25,00\n" +
			   "\t\tDe R$ 750,00 (inclusive) a R$ 1.500,00 (inclusive) |  R$ 50,00\n" +
			   "\t\t      De R$ 450,00 (inclusive) a R$ 750,00         |  R$ 75,00\n" +
			   "\t\t             Menores que R$ 450,00                 | R$ 100,00\n" +
			   "\tNa opção 3: receber o salário de um funcionário e mostrar sua classificação usando a tabela abaixo:\n" +
			   "\t\t          Salário       | Classificação\n" +
			   "\t\t       Até R$ 700,00    | MAL remunerado\n" +
			   "\t\t  Maiores que R$ 700,00 | BEM remunerado\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 11");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 11";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}