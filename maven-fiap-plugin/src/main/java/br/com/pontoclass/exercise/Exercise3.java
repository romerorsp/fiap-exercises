package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise3 extends AbstractExercise {

	private Map<String, String> input;
	private String	result;

	public String getStatement() {
		return "Uma empresa possui dez funcionários com as seguintes características: código, número de horas trabalhadas no mês, turno de trabalho " +
			   "(M- matutino, V-Vespertino ou N- Noturno), categoria (O- operário ou G-Gerente), valor da hora trabalhada. Sabendo-se que essa empresa " +
			   "deseja informatizar sua folha de pagamento, faça um programa que:\n" + 
			   "\ta. Leia as informações dos funcionários, exceto o valor da hora trabalhada, não permitindo que sejam informados turnos nem categorias inexistentes. " +
			   "Trabalhe sempre com a digitação de letras maiúsculas.\n" + 
			   "\tb. Calcule o valor da hora trabalhada, conforme a tabela a seguir: Adote o valor de R$ 450,00 para o salário mínimo.\n" +
			   "\t\tCATEGORIA | TURNO  | VALOR/HORA TRABALHADA\n" +
			   "\t\t    G     |   N    |   18% sal. min.\n" +
			   "\t\t    G     | M ou V |   15% sal. min.\n" +
			   "\t\t    O     |   N    |   13% sal. min.\n" +
			   "\t\t    O     | M ou V |   10% sal. min.\n" +
			   "\tc. Calcule o salário inicial dos funcionários com base no valor da hora trabalhada e no número de horas trabalhadas.\n" + 
			   "\td. Calcule o valor do auxílio alimentação recebido por funcionário de acordo com seu salário inicial, conforme a tabela a seguir:\n" + 
			   "\t\t     SALÁRIO INICIAL        | AUXÍLIO ALIMENTAÇÃO\n" +
			   "\t\t      Até R$ 300,00         | 20% do salário inicial\n" +
			   "\t\tEntre R$ 300,00 e R$ 600,00 | 15% do salário inicial\n" +
			   "\t\t    Acima de R$ 600,00      |  5% do salário inicial\n" +
			   "\te. Mostre o código, número de horas trabalhadas, valor da hora trabalhada, salário inicial, auxílio alimentação e salário final (salário inicial + auxílio alimentação).\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Dados dos funcionários, no formato: FUNCIONARIO={COD_FUNCIONARIO,NUM_HORAS_TRABALHADAS,TURNO,CATEGORIA}.\nSepare-os por ponto-e-vírgula (;)");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return this.result;
	}

	public void solve() {
//		Pattern pattern = Pattern.compile("^((([\\s\\w]+\\=\\{(([0-9]+\\,){2}([0-9]+)?\\.?[0-9]+\\,[MVN]{1}\\,[OG]{1})\\})\\;){9}([\\s\\w]+\\=\\{(([0-9]+\\,){2}([0-9]+)?\\.?[0-9]+\\,[MVN]{1}\\,[OG]{1})\\}))$"),
		Pattern pattern = Pattern.compile("^((([\\s\\w]+\\=\\{(([0-9]+\\,){2}[MVN]{1}\\,[OG]{1})\\})\\;){9}([\\s\\w]+\\=\\{(([0-9]+\\,){2}[MVN]{1}\\,[OG]{1})\\}))$"),
				valuesPattern = Pattern.compile("(([0-9]+\\,){2}[MVN]{1}\\,[OG]{1})");
		Matcher matcher = pattern.matcher(input.values().stream().findFirst().get());
		Map<String, Empregado> map = new HashMap<>();
		if(matcher.find()) {
			String[] groups = matcher.group().split(";");
			for(String employee: groups) {
				String val = employee.substring(employee.lastIndexOf("=") + 1);
				employee = employee.substring(0, employee.lastIndexOf("="));
				Matcher matcher2 = valuesPattern.matcher(val);
				matcher2.find();
				String[] split = matcher2.group().split(",");
				map.put(employee, new Empregado(employee, 
												Integer.valueOf(split[0]),
												Integer.valueOf(split[1]),
												split[2].charAt(0),
												split[3].charAt(0)));
			}
			if(map.size() < 10) {
				this.result = "ERRO: Não tente me enganar, não repita nomes de funcionarios =)";
				return;
			}
		} else {
			this.result = String.format("ERRO: Dados de funcionários incorretos!");
			return;
		}
		
		this.result = String.format("Resultado do processamento: [%s]", map.values().toString());
	}
	
	private static class Empregado {
		private String	nome;
		private int	codigo;
		private int	horas;
		private char	categoria;
		private static final double SALARIO_MINIMO = 450.00;
		private char	turno;

		public Empregado(String nome, int codigo, int horas, char turno, char categoria) {
			this.nome = nome;
			this.codigo = codigo;
			this.horas = horas;
			this.turno = turno;
			this.categoria = categoria;
			
		}

		public int getCodigo() {
			return codigo;
		}

		public double getValorHora() {
			switch(categoria) {
				case 'G': {
					return ('N' == turno? 0.18: 0.15)*SALARIO_MINIMO;
				}
				default: {
					return ('N' == turno? 0.13: 0.10)*SALARIO_MINIMO;
				}
			}
		}
		
		public double getValorAuxilio() {
			double salario = getSalarioInicial();
			if(salario <= 300) {
				return salario * 0.20;
			} else if(salario <= 600) {
				return salario * 0.15;
			} else return salario * 0.05;
		}
		
		public double getSalarioInicial() {
			return this.getValorHora()*this.horas;
		}
		
		public double getSalarioFinal() {
			return this.getSalarioInicial() + getValorAuxilio();
		}
		
		private Object getHoras() {
			return this.horas;
		}
		
		@Override public String toString() {
			return String.format("%s={codigo=%d,valor/hora=%.2f,numero de horas trabalhadas=%d,salario inicial=%.2f,auxilio alimentacao=%.2f,salario final=%.2f}", 
					nome, getCodigo(), getValorHora(), getHoras(), getSalarioInicial(), getValorAuxilio(), getSalarioFinal());
		}
	}
	
	public static void main(String[] args) {
		Exercise exercise = new Exercise3();
		exercise.setInputMap(Collections.singletonMap("input","Nome1={123,160,M,O};Nome2={321,160,V,G};Nome3={1412,160,N,O};Nome4={543,160,M,G};Nome5={120,160,V,O};NOME6={1203,160,M,O};NOME7={12033,160,V,G};NOME8={12460,160,N,O};NOME9={12550,160,M,G};NOME10={17520,160,V,O}"));
		exercise.solve();
		System.out.println(exercise.getResultDescription());
	}
}
