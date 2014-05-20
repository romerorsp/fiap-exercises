package br.com.pontoclass.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.plexus.util.StringUtils;

public class Exercise5 extends AbstractExercise {

	private Map<String, String> input;
	private String	result;

	public String getStatement() {
		return "Foi feita uma pesquisa para determinar o índice de mortalidade infantil em certo período. Faça um programa que:\n" +
			   "\t\ta. Leia o número de crianças nascidas no período;\n" +
			   "\t\tb. Identifique sexo (M ou F) e o tempo de vida de cada criança nascida.\n" + 
			   "\tO programa deve calcular e mostrar:\n" +
			   "\t\ta. A percentagem de crianças do sexo feminino mortas no período;\n" + 
			   "\t\tb. A percentagem de crianças do sexo masculino mortas no período;\n" +
			   "\t\tc. A percentagem de crianças que viveram 24 meses ou menos no período.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Número de crianças nascidas no período",
				"Dados das crianças, no formato: {SEXO_CRIANCA,TEMPO_VIVIDO}.\nInforme o tempo vivido no padrão (1a2m) para um ano e dois meses, por exemplo.\nSepare as informações de cada criança por ponto-e-vírgula (;)");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return this.result;
	}

	public void solve() {
		String limitStr = input.get(getInputNames().get(0));
		if(!StringUtils.isNumeric(limitStr)) {
			this.result = String.format("ERRO: O numero de crianças nascidas no período precisa ser um valor numérico. VALOR DIGITADO: [%s]", limitStr);
			return;
		}
		int limit = Integer.valueOf(limitStr);
		Pattern pattern = Pattern.compile(String.format("^(((\\{(([MF]\\,){1}([0-9]+a([0-9]+m)?))\\})\\;){%d}(\\{(([MF]\\,){1}([0-9]+a([0-9]+m)?))\\}))$", limit-1)),
				childrenPattern = Pattern.compile("(([MF]\\,){1}([0-9]+a([0-9]+m)?))");
		Matcher matcher = pattern.matcher(input.get(getInputNames().get(1)));
		List<Crianca> list = new ArrayList<>();
		if(matcher.find()) {
			String[] groups = matcher.group().split(";");
			for(String cri: groups) {
				Matcher matcher2 = childrenPattern.matcher(cri);
				matcher2.find();
				String[] split = matcher2.group().split(",");
				int meses = Integer.valueOf(split[1].substring(0, split[1].lastIndexOf("a")))*12 + (split[1].contains("m")? Integer.valueOf(split[1].substring(split[1].indexOf("a") + 1, split[1].lastIndexOf("m"))): 0);
				Crianca crianca = new Crianca(split[0].charAt(0), meses);
				list.add(crianca);
			}
			if(list.size() < limit) {
				this.result = "ERRO: Numero de crianças informadas menor que o prometido =)";
				return;
			}
		} else {
			this.result = String.format("ERRO: Dados de times incorretos!");
			return;
		}
		
		int masc, fem, bebes = fem = masc = 0;
		for(Crianca crianca: list) {
			if(crianca.getSexo() == 'M') {
				masc++;
			} else {
				fem++;
			}
			if(crianca.getMeses() <= 24) {
				bebes++;
			}
		}
		
		this.result = String.format("Percentual de meninos: [%.2f]\n" +
		              				"Percentual de meninas: [%.2f]\n" +
		              				"Percentual de meninos e meninas que viveram de 0 a 24 meses: [%.2f]", masc*100f/limit, fem*100f/limit, bebes*100f/limit);
	}
	
	private static class Crianca {

		private char	sexo;
		private int	meses;

		public Crianca(char sexo, int meses) {
			this.sexo = sexo;
			this.meses = meses;
		}

		public int getMeses() {
			return meses;
		}

		public char getSexo() {
			return sexo;
		}
	}
	
	@SuppressWarnings("serial") public static void main(String[] args) {
		Exercise exercise = new Exercise5();
		exercise.setInputMap(new HashMap<String, String>() {{
				put("XX", "5");
				put("input","{M,1a2m};{M,0a2m};{F,1a2m};{M,2a2m};{F,3a2m}");
			}
		});
		exercise.solve();
		System.out.println(exercise.getResultDescription());
	}
}