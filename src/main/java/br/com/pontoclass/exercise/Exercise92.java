package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise92 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Faça um programa que receba os dados a seguir de vários produtos: preço unitário, país de origem (1- Estados Unidos, 2- México e 3- outros), " +
			   "meio de transporte (T- terrestre, F- fluvial e A- aéreo), carga perigosa (s-sim, N-não), finalize a entrada de dados com um preço inválido, ou seja, " +
			   "menor ou igual a zero e que calcule e mostre:\n" +
			   "\t* O valor do imposto, usando a tabela a seguir:\n" +
			   "\t\t                      |    Percentual de\n" +
			   "\t\t                      | imposto sobre o preço\n" +
			   "\t\t    Preço Unitário    |      unitário\n" +
			   "\t\t     Até R$ 100,00    |         5%\n" +
			   "\t\tMaiores que R$ 100,00 |        10%\n" +
			   "\t* O valor do transporte usando a tabela a seguir:\n" +
			   "\t\tCarga Perigosa | País de origem | Valor do transporte\n" +
			   "\t\t               |        1       |      R$ 50,00\n" +
			   "\t\t               |        2       |      R$ 21,00\n" +
			   "\t\t       S       |        3       |      R$ 24,00\n" +
			   "\t\t---------------|----------------|-------------------\n" +
			   "\t\t               |        1       |      R$ 12,00\n" +
			   "\t\t               |        2       |      R$ 21,00\n" +
			   "\t\t       N       |        3       |      R$ 60,00\n" +
			   "\t* O valor do seguro, usando a regra a seguir:\n" +
			   "\tOs produtos que vêm do México e os produtos que utilizam transporte áereo pagam metade do valor do seu preço unitário como seguro.\n" +
			   "\t\t* O preço final, ou seja, preço unitário, mais imposto mais valor do transporte mais valor do seguro.\n" +
			   "\t\t* O total dos impostos.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 12");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 12";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}