package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise3 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Uma empresa possui dez funcionários com as seguintes características: código, número de horas trabalhadas no mês, turno de trabalho (M- matutino, V-Vespertino ou N- Noturno), categoria (O- operário ou G-Gerente), valor da hora trabalhada. Sabendo-se que essa empresa deseja informatizar sua folha de pagamento, faça um programa que:\n" + 
			   "\ta. Leia as informações dos funcionários, exceto o valor da hora trabalhada, não permitindo que sejam informados turnos nem categorias inexistentes. Trabalhe sempre com a digitação de letras maiúsculas.\n" + 
			   "\tb. Calcule o valor da hora trabalhada, conforme a tabela a seguir: Adote o valor de R$ 450,00 para o salário mínimo.\n" +
			   "\t\tCATEGORIA | TURNO  | VALOR/HORA TRABALHADA\n" +
			   "\t\t	G     |   N    |   18% sal. min.\n" +
			   "\t\t	G	  | M ou V |   15% sal. min.\n" +
			   "\t\t	O	  |   n    |   13% sal. min.\n" +
			   "\t\t	O     | M ou V |   10% sal. min.\n" +
			   "\tc. Calcule o salário inicial dos funcionários com base no valor da hora trabalhada e no número de horas trabalhadas.\n" + 
			   "\td. Calcule o valor do auxílio alimentação recebido por funcionário de acordo com seu salário inicial, conforme a tabela a seguir:\n" + 
			   "\t\t	 SALÁRIO INICIAL        | AUXÍLIO ALIMENTAÇÃO\n" +
			   "\t\t 	  Até R$ 300,00         | 20% do salário inicial\n" +
			   "\t\tEntre R$ 300,00 e R$ 600,00 | 15% do salário inicial\n" +
			   "\t\t    Acima de R$ 600,00      |  5% do salário inicial\n" +
			   "\te. Mostre o código, número de horas trabalhadas, valor da hora trabalhada, salário inicial, auxílio alimentação e salário final (salário inicial + auxílio alimentação).\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 3");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 3";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}
