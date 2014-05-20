package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise9 extends AbstractExercise {

	private Map<String, String> input;
	private String	result;

	public String getStatement() {
		return "Faça um programa que receba vários números, calcule e mostre:\n" +
			   "\t\t* A soma dos números digitados;\n" +
			   "\t\t* A quantidade dos números digitados;\n" +
			   "\t\t* A média dos números digitados;\n" +
			   "\t\t* O maior número digitado;\n" +
			   "\t\t* O menor número digitado;\n" +
			   "\t\t* A média dos números pares;\n" +
			   "\t\t* A percentagem dos números ímpares entre todos os números digitados.\n" +
			   "\tFinalize a entrada de dados com a digitação do número 30.000.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Números, no formato: TIME={{NUMERO1,NUMERO2,..., 30000}}.\nNão formate o número 30.000, deixe sem pontuação. ;-)\nNão serão aceitos valores negativos.");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return result;
	}

	public void solve() {
		Pattern pattern = Pattern.compile("^(\\{(((([0-9]+)?\\.?[0-9]+\\,)+)?([0-9]+)?\\.?[0-9]+)\\})$");
		Matcher matcher = pattern.matcher(input.values().stream().findFirst().get());
		if(matcher.find()) {
			List<Double> values = 
			Stream.of(matcher.group(2).split(","))
				  .map(Double::valueOf)
				  .collect(Collectors.toList());
			if(values.indexOf(30000.00) != values.size()-1) {
				this.result = "O valor 30.000 deveria ser o último da lista informada.";
				return;
			}
			
			//firula...
			final int[] quantidade = new int[1], 
						quantidadePares = new int[1],
						impares = new int[1];
			final double[] maior = new double[1],
					       menor = new double[1],
					       pares = new double[1], 
					       soma  = new double[1];
			menor[0] = -1;
			values.parallelStream()
				.forEach((valor)->{
					soma[0] += valor;
					quantidade[0]++;
					if(maior[0] < valor) {
						maior[0] = valor;
					}
					if(menor[0] == -1 || menor[0] > valor) {
						menor[0] = valor;
					}
					if(valor%2 == 0) {
						pares[0] += valor;
						quantidadePares[0]++;
					} else {
						impares[0]++;
					}
				});
			this.result = String.format("A soma dos números digitados: [%.2f]\n" +
										"A quantidade dos números digitados: [%d]\n" +
										"A média dos números digitados: [%.2f]\n" +
										"O maior digitado: [%.2f]\n" +
										"O menor digitado: [%.2f]\n" +
										"Média de pares: [%.2f]\n" +
										"Percentual de ímpares: [%.2f%%]",
										soma[0],
										quantidade[0],
										soma[0]/(quantidade[0]*1f),
										maior[0],
										menor[0],
										quantidadePares[0] == 0? 0: pares[0]/(quantidadePares[0]*1f),
										(impares[0]*100f)/quantidade[0]);
		} else {
			this.result = String.format("ERRO: Lista de números incorreta! Não se esqueça que a última entrada é 30000 =)");
			return;
		}
	}
	
	@SuppressWarnings("serial") public static void main(String[] args) {
		Exercise exercise = new Exercise9();
		exercise.setInputMap(new HashMap<String, String>() {{
				put("input","{99,123,32,343,9,21,39445,2,93.43,.23423,234,30000}");
			}
		});
		exercise.solve();
		System.out.println(exercise.getResultDescription());
	}
}