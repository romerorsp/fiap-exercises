package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise6 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Faça um programa que receba o valor de uma dívida e mostre uma tabela com os seguintes dados:\n" +
			   "\t* Valor da dívida, valor do juros, quantidade de parcelas e valor da parcela.\n" +
			   "\t\tOs juros e a quantidade de parcelas seguem a tabela:\n" +
			   "\t\tQuantidade de Parcelas | % de juros sobre o valor inicial da dívida\n" +
			   "\t		   1           |                     0\n" +
			   "\t\t           3           |                    10\n" +
			   "\t\t           6           |                    15\n" +
			   "\t\t           9           |                    20\n" +
			   "\t\t          12           |                    25\n" +
			   "\t\tExemplo de saída do programa:\n" +
			   "\t\tVALOR DA DÍVIDA | VALOR DOS JÚROS | QUANTIDADE DE PARCELAS | VALOR DA PARCELA\n" +
			   "\t\t  R$ 1.000,00   |        0        |           1            |   R$ 1.000,00\n" +
			   "\t\t  R$ 1.100,00   |       100       |           3            |    R$ 366,67\n" +
			   "\t\t  R$ 1.150,00   |       150       |           6            |    R$ 191,67\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 6");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 6";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}