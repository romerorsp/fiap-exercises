package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise7 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Faça um programa que receba o preço unitário, a refrigeração (S para os produtos que necessitem de refrigeração e N para os que não necessitem) e a categoria (A – alimentação, L-limpeza e V-vestuário) de doze produtos, e que calcule e mostre:\n" +
			   "\tO custo de estocagem, calculado de acordo com a tabela a seguir:\n" +
			   "\t\t     Preço Unitário       | Refrigeração | Categoria | Custo de Estocagem\n" + 
			   "\t\t                          |              |     A     |      R$ 2,00\n" +
			   "\t\t                          |              |     L     |      R$ 3,00\n" +
			   "\t\t         Até 20           |              |     V     |      R$ 4,00\n" +
			   "\t\t-------------------------------------------------------------------------\n" +
			   "\t\t                          |       S      |           |      R$ 6,00\n" +
			   "\t\t Entre 20 e 50 (inclusive)|       N      |           |      R$ 0,00\n" +
			   "\t\t-------------------------------------------------------------------------\n" +
			   "\t\t                          |              |     A     |      R$ 5,00\n" +
			   "\t\t                          |              |     L     |      R$ 2,00\n" +
			   "\t\t                          |       S      |     V     |      R$ 4,00\n" +
			   "\t\t                          |----------------------------------------------\n" +
			   "\t\t                          |              |   A ou V  |      R$ 0,00\n" +
			   "\t\t      Mais que 50         |       N      |     L     |      R$ 1,00\n" +
			   "\t\t A. O imposto calculado de acordo com as regras a seguir:\n" +
			   "Se o produto não preencher nenhum dos requisitos abaixo, seu imposto será de 2% sobre o preço unitário; caso contrário, será de 4%.\n" +
			   "Os requisitos são: categoria –A e refrigeração – S. O preço final, ou seja, preço unitário mais custo de estocagem mais imposto.\n" + 
			   "\tA classificação calculada usando a tabela a seguir:\n" + 
			   "\t\t         Preço Final       | Classificação\n" +
			   "\t\t        Até R$ 20,00       | Barato\n" +
			   "\t\tEntre R$ 20,00 e R$ 100,00 | Normal\n" +
			   "\t\t     Acima de R$ 100,00    | Caro\n" +
			   "A média dos valores adicionais, ou seja, a média dos custos de estocagem e dos impostos dos doze produtos.\n" +
			   "O maior preço final.\n" +
			   "O menor preço final.\n" +
			   "O total dos impostos.\n" +
			   "A quantidade de produtos com classificação barato.\n" + 
			   "A quantidade de produtos com classificação caro.\n" +
			   "A quantidade de produtos com classificação normal.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 7 (Com uma quebra \nde linha)");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 7\nPULANDO LINHA...";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}