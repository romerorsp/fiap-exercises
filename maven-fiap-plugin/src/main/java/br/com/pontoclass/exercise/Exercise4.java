package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise4 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Em um campeonato de futebol existem cinco times e cada um possui onze jogadores. Faça um programa que receba a idade, o peso e a altura de cada um dos jogadores, calcule e mostre:\n" + 
			   "\ta. A quantidade de jogadores com idade inferior a 18 anos;\n" + 
			   "\tb. A média das idades dos jogadores de cada time;\n" +
			   "\tc. A média das alturas de todos os jogadores do campeonato;\n" +
			   "\td. A percentagem de jogadores com mais de 80 quilos entre todos os jogadores do campeonato.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 4");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 4";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}