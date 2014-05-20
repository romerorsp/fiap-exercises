package br.com.pontoclass.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise90 extends AbstractExercise {

	private Map<String, String> input;
	private String	result;

	public String getStatement() {
		return "Uma empresa decidiu fazer um levantamento em relação aos candidatos que apresentarem para preenchimento de vagas em seu quadro de funcionários. " +
			   "Supondo que você seja o programador dessa empresa, faça um programa que leia, para cada candidato, a idade, o sexo (M ou F) e a experiência no serviço (S ou N). " +
			   "Para encerrar a entrada de dados, digite zero para a idade.\n" +
			   "\tO programa também deve calcular e mostrar:\n" +
			   "\t\t* O número de candidatos do sexo feminino;\n" +
			   "\t\t* O número de candidatos do sexo masculino;\n" +
			   "\t\t* A idade média dos homens que já tem experiência no serviço;\n" +
			   "\t\t* A percentagem dos homens com mais de 45 anos entre o total dos homens;\n" +
			   "\t\t* O número de mulheres com idade inferior a 21 anos e com a experiência no serviço;\n" +
			   "\t\t* A menor idade entre as mulheres que já tem experiência no serviço.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Dados dos candidatos, no formato: TIME={{IDADE,SEXO,EXPERIENCIA}}.\nSepare os candidatos por ponto-e-vírgula (;)");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return this.result;
	}

	public void solve() {
		Pattern pattern = Pattern.compile("^((\\{[0-9]+\\,[MF]{1}\\,[SN]{1}\\}\\;)+\\{[0]{1}\\})$"),
				childrenPattern = Pattern.compile("([0-9]+\\,[MF]{1}\\,[SN]{1})");
		Matcher matcher = pattern.matcher(input.values().stream().findFirst().get());
		List<Candidato> list = new ArrayList<>();
		if(matcher.find()) {
			String[] groups = matcher.group().split(";");
			for(String cri: groups) {
				Matcher matcher2 = childrenPattern.matcher(cri);
				if(matcher2.find()) {
					String[] split = matcher2.group().split(",");
					Candidato candidato = new Candidato(Integer.valueOf(split[0]), split[1].charAt(0), split[2].charAt(0));
					list.add(candidato);
				}
			}
		} else {
			this.result = String.format("ERRO: Dados de candidatos incorretos! Não se esqueça que a última entrada é {0} =)");
			return;
		}
		
		int masc, 
		    fem, 
		    senhores, 
		    mulheresJovens, 
		    menorExperiente, 
		    totalIdadeExperientes, 
		    experientes = 
		    totalIdadeExperientes =
		    menorExperiente = 
		    mulheresJovens = 
		    senhores = 
		    fem = 
		    masc = 0;
		for(Candidato candidato: list) {
			if(candidato.getSexo() == 'M') {
				masc++;
				if(candidato.getIdade() > 45) {
					senhores++;
				}
			} else {
				fem++;
				if(candidato.getIdade() < 21) {
					mulheresJovens++;
				}
				if(candidato.isExperiente()) {
					if(menorExperiente == 0 || menorExperiente > candidato.getIdade()) {
						menorExperiente = candidato.getIdade();
					}
				}
			}
			if(candidato.isExperiente()) {
				totalIdadeExperientes += candidato.getIdade();
				experientes++;
			}
		}
		
		this.result = String.format("Quantidade de mulheres: [%d]\n" +
		              				"Quantidade de homens: [%d]\n" +
		              				"Idade média de homens com experiência: [%.2f]\n" +
		              				"Percentual de homens com mais de 45 anos: [%.2f%%]\n" +
		              				"Mulheres com menos de 21 anos de idade: [%d]\n" +
		              				"Menor idade entre as mulheres com experiência: [%d]", 
		              				fem, masc,
		              				totalIdadeExperientes/(experientes*1f),
		              				(senhores*100f)/masc,
		              				mulheresJovens,
		              				menorExperiente);
	}
	
	public static class Candidato {

		private Integer	idade;
		private char	sexo;
		private char	experiencia;

		public Candidato(Integer idade, char sexo, char experiencia) {
			this.idade = idade;
			this.sexo = sexo;
			this.experiencia = experiencia;
		}

		public boolean isExperiente() {
			return 'S' == experiencia;
		}

		public int getIdade() {
			return idade;
		}

		public char getSexo() {
			return sexo;
		}
		
	}
	
	@SuppressWarnings("serial") public static void main(String[] args) {
		Exercise exercise = new Exercise90();
		exercise.setInputMap(new HashMap<String, String>() {{
				put("input","{45,M,S};{46,F,N};{18,F,S};{19,M,N};{22,F,S};{0}");
			}
		});
		exercise.solve();
		System.out.println(exercise.getResultDescription());
	}
}