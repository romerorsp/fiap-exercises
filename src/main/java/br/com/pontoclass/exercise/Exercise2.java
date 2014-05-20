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

public class Exercise2 extends AbstractExercise {

	private Map<String, String> input;
	private String	result;

	public String getStatement() {
		return "Foi feita uma estatística em cinco cidades brasileiras para coletar dados sobre acidentes de trânsito.\n" +
			   "Foram obtidos os seguintes dados:\n" + 
			   "\ta. Código da cidade\n" +
			   "\tb. Número de veículos de passeio (em 2007).\n" +
			   "\tc. Número de acidentes de trânsito com vítimas (em 2007).\n" + 
			   "Deseja-se saber:\n" +
			   "\ta. Qual o maior e menor índice de acidentes de trânsito e a que cidades pertencem;\n" + 
			   "\tb. Qual a média de veículos nas cinco cidades juntas;\n" +
			   "\tc. Qual a média de acidentes de trânsito nas cidades com menos de 2.000 veículos de passeio.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Dados Estatísticos no formato: CIDADE={COD_CIDADE,NUM_VEICULOS_PASSEIO,NUM_ACIDENTES_TRANSITO_COM_VITIMAS}.\nSe houver mais de uma cidade, separe por ponto-e-vírgula (;)");
	}

	public void setInputMap(Map<String, String> input) {
		System.out.println(input.values().stream().findFirst().get());
		this.input = input;
	}

	public String getResultDescription() {
		return result;
	}

	public void solve() {
		Pattern pattern = Pattern.compile("^((([\\s\\w]+\\=\\{(([0-9]+\\,){2}[0-9]+)\\})\\;){4}([\\s\\w]+\\=\\{(([0-9]+\\,){2}[0-9]+)\\}))$"),
				valuesPattern = Pattern.compile("(([0-9]+\\,){2}[0-9]+)");
		Matcher matcher = pattern.matcher(input.values().stream().findFirst().get());
		Map<String, int[]> map = new HashMap<>();
		if(matcher.find()) {
			String[] groups = matcher.group().split(";");
			for(String city: groups) {
				String val = city.substring(city.lastIndexOf("=") + 1);
				city = city.substring(0, city.lastIndexOf("="));
				Matcher matcher2 = valuesPattern.matcher(val);
				matcher2.find();
				String[] split = matcher2.group().split(",");
				int[] values = new int[3];
				int i = 0;
				for(String value: split) {
					values[i++] = Integer.valueOf(value);
				}
				map.put(city, values);
			}
			if(map.size() < 5) {
				this.result = "ERRO: Não tente me enganar, não repita nomes de cidades =)";
				return;
			}
		} else {
			this.result = String.format("ERRO: Dados estatísticos incorretos!");
			return;
		}
		
		String nomeMenorIndice, nomeMaiorIndice = nomeMenorIndice = "";
		int menorIndice = 0, maiorIndice = 0;
		double numVeiculos = 0, numAcidentes = 0;
		List<String> cidadesAcidentes = new ArrayList<>();
		
		Set<String> keys = map.keySet();
		for(String key: keys) {
			if(map.get(key)[1] < 2000) {
				cidadesAcidentes.add(String.format("%d - %s", map.get(key)[0], key));
				numAcidentes += map.get(key)[2]; 
			}
			if(menorIndice == 0 || map.get(key)[2] < menorIndice) {
				nomeMenorIndice = String.format("%d - %s", map.get(key)[0], key);
				menorIndice = map.get(key)[2];
			}
			
			if(map.get(key)[2] > maiorIndice) {
				nomeMaiorIndice = String.format("%d - %s", map.get(key)[0], key);
				maiorIndice = map.get(key)[2];
			}
			numVeiculos += map.get(key)[1];
		}
		String resp1 = String.format("O maior índice de acidentes de trânsito é na cidade [%s] com %d acidentes e o maior índica é na cidade [%s] com %d acidentes.", nomeMaiorIndice, maiorIndice, nomeMenorIndice, menorIndice),
			   resp2 = String.format("A média de veículos nas 5 cidades é de %.2f veículos.", numVeiculos/5f),
			   resp3;
		if(cidadesAcidentes.size() == 0) {
			resp3 = "Nenhuma das cidades possui menos de 2.000 veículos.";
		} else {
			resp3 = String.format("A média de acidentes de trânsito nas cidades com mais de 2.000 veículos (%s) é de %.2f.", cidadesAcidentes.toString(), numAcidentes/cidadesAcidentes.size()*1f);
		}
		this.result = String.format("%s\n%s\n%s", resp1, resp2, resp3);
	}
	
	public static void main(String[] args) {
		Exercise exercise = new Exercise2();
		exercise.setInputMap(Collections.singletonMap("input","Curitiba={123,99,2};Sao Paulo={321,2000,8};Picos={1412,1283,433};Vitoria de Santo Antao={543,123,43};Guarulhos={120,1926,2}"));
		exercise.solve();
		System.out.println(exercise.getResultDescription());
	}
}
