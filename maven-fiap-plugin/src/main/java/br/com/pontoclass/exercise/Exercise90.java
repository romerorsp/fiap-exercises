package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Exercise90 extends AbstractExercise {

	private Map<String, String> input;

	public String getStatement() {
		return "Uma empresa decidiu fazer um levantamento em relação aos candidatos que apresentarem para preenchimento de vagas em seu quadro de funcionários. Supondo que você seja o programador dessa empresa, faça um programa que leia, para cada candidato, a idade, o sexo (M ou F) e a experiência no serviço (S ou N). Para encerrar a entrada de dados, digite zero para a idade.\n" +
			   "\tO programa também deve calcular e mostrar:\n" +
			   "\t\t* O número de candidatos do sexo feminino;\n" +
			   "\t\t* O número de candidatos do sexo masculino;\n" +
			   "\t\t* A idade média dos homens que já tem experiência no serviço;\n" +
			   "\t\t* A percentagem dos homens com mais de 45 anos entre o total dos homens;\n" +
			   "\t\t* O número de mulheres com idade inferior a 21 anos e com a experiência no serviço;\n" +
			   "\t\t* A menor idade entre as mulheres que já tem experiência no serviço.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 10");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return "O resultado é: Exercicio 10";
	}

	public void solve() {
		// TODO Auto-generated method stub
	}
}