package br.com.pontoclass.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise4 extends AbstractExercise {

	private Map<String, String> input;
	private String	result;
	private static final int QTD_JOGADORES = 5 * 11;

	public String getStatement() {
		return "Em um campeonato de futebol existem cinco times e cada um possui onze jogadores. Faça um programa que receba a idade, o peso e a altura de cada um dos jogadores, " +
	           "calcule e mostre:\n" + 
			   "\ta. A quantidade de jogadores com idade inferior a 18 anos;\n" + 
			   "\tb. A média das idades dos jogadores de cada time;\n" +
			   "\tc. A média das alturas de todos os jogadores do campeonato;\n" +
			   "\td. A percentagem de jogadores com mais de 80 quilos entre todos os jogadores do campeonato.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Dados dos times, no formato: TIME={{IDADE_JOGADOR,PESO_JOGADOR,ALTURA_JOGADOR}}.\nSepare os jogadores por pipe (|) e os times por ponto-e-vírgula (;)");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return this.result;
	}

	public void solve() {
		Pattern pattern = Pattern.compile("^((([\\s\\w]+\\=\\{((\\{(([0-9]+\\,){2}([0-9]+)?\\.?[0-9]+)\\}\\|){10}(\\{(([0-9]+\\,){2}([0-9]+)?\\.?[0-9]+)\\}))\\})\\;){4}([\\s\\w]+\\=\\{((\\{(([0-9]+\\,){2}([0-9]+)?\\.?[0-9]+)\\}\\|){10}(\\{(([0-9]+\\,){2}([0-9]+)?\\.?[0-9]+)\\}))\\}))$"),
				teamPattern = Pattern.compile("((\\{(([0-9]+\\,){2}([0-9]+)?\\.?[0-9]+)\\}\\|){10}(\\{(([0-9]+\\,){2}([0-9]+)?\\.?[0-9]+)\\}))"),
				playerPattern = Pattern.compile("((([0-9]+\\,){2}([0-9]+)?\\.?[0-9]+))");
		Matcher matcher = pattern.matcher(input.values().stream().findFirst().get());
		Map<String, TimeFutebol> map = new HashMap<>();
		if(matcher.find()) {
			String[] groups = matcher.group().split(";");
			for(String timeFut: groups) {
				String val = timeFut.substring(timeFut.lastIndexOf("=") + 1);
				Matcher matcher2 = teamPattern.matcher(val);
				timeFut = timeFut.substring(0, timeFut.lastIndexOf("="));
				TimeFutebol timeFutebol = new TimeFutebol(timeFut);
				matcher2.find();
				String[] split = matcher2.group().split("[|]");
				for(String jog: split) {					
					Matcher matcher3 = playerPattern.matcher(jog);
					matcher3.find();
					String[] split2 = matcher3.group().split(",");
					timeFutebol.addJogador(new JogadorFutebol(Integer.valueOf(split2[0]), Integer.valueOf(split2[1]), Double.valueOf(split2[2])));
				}
				map.put(timeFut, timeFutebol);
			}
			if(map.size() < 5) {
				this.result = "ERRO: Não tente me enganar, não repita nomes de times =)";
				return;
			}
		} else {
			this.result = String.format("ERRO: Dados de times incorretos!");
			return;
		}
		
		Set<String> keys = map.keySet();
		int jogadoresAcima80 = 0,
			jogadoresMenoresIdade = 0;
		double alturaTotal = 0;
		for(String key: keys) {
			TimeFutebol time = map.get(key);
			List<JogadorFutebol> jogadores = time.getJogadores();
			for(JogadorFutebol jogador: jogadores) {
				if(jogador.getPeso() > 80) {
					jogadoresAcima80++;
				}
				alturaTotal += jogador.getAltura();
				if(jogador.getIdade() < 18) {
					jogadoresMenoresIdade++;
				}
			}
		}
		
		this.result = String.format("Resultado do processamento:\n" +
									"Quantidade de Jogadores com idade inferior a 18 anos: [%d]\n" +
									"A média das idades dos jogadores de cada time: [%s]\n" +
									"A média das alturas de todos os jogadores do campeonato: [%.2f]\n" +
									"A percentagem de jogadores com mais de 80 quilos entre todos os jogadores do campeonato: [%.2f%%]", 
									jogadoresMenoresIdade,
									map.values().toString(),
									alturaTotal/QTD_JOGADORES,
									(jogadoresAcima80*100f)/QTD_JOGADORES);
	}
	
	private static class TimeFutebol {
		private String	nome;
		private final List<JogadorFutebol> jogadores = new ArrayList<>();

		public TimeFutebol(String nome) {
			this.setNome(nome);
		}
		
		public List<JogadorFutebol> getJogadores() {
			return jogadores;
		}

		public TimeFutebol addJogador(JogadorFutebol jogador) {
			jogadores.add(jogador);
			return this;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}
		
		@Override public String toString() {
			double idadeTotal = 0;
			for(JogadorFutebol jogador: jogadores) {
				idadeTotal += jogador.getIdade();
			}
			return String.format("%s [idade média = %.2f]", getNome(), idadeTotal/11);
		}
	}
	
	private static class JogadorFutebol {

		private Integer	idade;
		private Integer	peso;
		private Double	altura;

		public JogadorFutebol(Integer idade, Integer peso, Double altura) {
			this.idade = idade;
			this.peso = peso;
			this.altura = altura;
		}

		public int getIdade() {
			return idade;
		}

		public double getAltura() {
			return altura;
		}

		public int getPeso() {
			return peso;
		}
	}
	
	public static void main(String[] args) {
		Exercise exercise = new Exercise4();
		exercise.setInputMap(Collections.singletonMap("input","PALMEIRAS={{19,81,1.73}|{21,81,1.72}|{17,99,1.62}|{17,81,1.72}|{17,81,1.72}|{17,60,1.72}|{23,81,1.72}|{17,81,1.92}|{16,81,1.72}|{17,76,1.82}|{17,76,1.82}};PIRACICABA={{19,81,1.73}|{21,81,1.72}|{17,99,1.62}|{17,81,1.72}|{17,81,1.72}|{17,60,1.72}|{23,81,1.72}|{17,81,1.92}|{16,81,1.72}|{17,76,1.82}|{17,76,1.82}};Sport={{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}};Brasiliense={{19,81,1.73}|{21,81,1.72}|{17,99,1.62}|{17,81,1.72}|{17,81,1.72}|{17,60,1.72}|{23,81,1.72}|{17,81,1.92}|{16,81,1.72}|{17,76,1.82}|{17,99,1.62}};Comercial do Piaui={{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}|{17,81,1.72}}"));
		exercise.solve();
		System.out.println(exercise.getResultDescription());
	}
}