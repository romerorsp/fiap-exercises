package br.com.pontoclass.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Exercise8 extends AbstractExercise {

	private Map<String, String> input;
	private String	result;

	public String getStatement() {
		return "Faça um programa para ler o código, o sexo (M- masculino, F- feminino) e o número de horas/aula dadas mensalmente pelos professores de uma universidade, " +
	           "sabendo-se que cada hora/aula vale R$ 30,00. Emita uma listagem contendo o código, o salário bruto e o salário líquido (levando em consideração os descontos " +
			   "explicados a seguir) de todos os professores.\n" +
			   "Mostre também a média dos salários líquidos dos professores do sexo masculino e a média dos salários líquidos dos professores do sexo feminino. Considere:\n" +
			   "\tDesconto para homens, 10% e, para mulheres, 5%.\n" +
			   "\tAs informações terminarão quando for lido o código = 99999.\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Input Exercicio 8");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return result;
	}

	public void solve() {
		Pattern pattern = Pattern.compile("^((\\{[0-9]+\\,[MF]{1}\\,[0-9]+\\}\\;)+\\{[9]{5}\\})$"),
				childrenPattern = Pattern.compile("([0-9]+\\,[MF]{1}\\,[0-9]+)");
		Matcher matcher = pattern.matcher(input.values().stream().findFirst().get());
		List<Teacher> list = new ArrayList<>();
		if(matcher.find()) {
			String[] groups = matcher.group().split(";");
			for(String cri: groups) {
				Matcher matcher2 = childrenPattern.matcher(cri);
				if(matcher2.find()) {
					String[] split = matcher2.group().split(",");
					Teacher prof = TeacherFactory.createTeacher(Integer.valueOf(split[0]), split[1].charAt(0), Integer.valueOf(split[2]));
					list.add(prof);
				}
			}
		} else {
			this.result = String.format("ERRO: Dados de professores incorretos! Não se esqueça que a última entrada é {99999} =)");
			return;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("PROFESSORES: [%s]\n",list.toString()));
		list.stream()
			.collect(Collectors.groupingBy(Teacher::getSex))
			.entrySet()
			.stream()
			.forEach((entry)->{
				Double value = entry.getValue().stream().map(Teacher::getDiscountedSalary).collect(Collectors.averagingDouble((d)->d));
				buffer.append(String.format("MEDIA DE SALARIO %s: [%.2f]\n", entry.getKey() == 'M'?"MASCULINO": "FEMININO", value));
			});
		result = buffer.toString();
	}
	
	private static class TeacherFactory {
		public static Teacher createTeacher(int id, char sex, int hours) {
			switch(sex) {
				case 'M': return new Female(id, hours, sex);
				default: return new Male(id, hours, sex);
			}
		}
	}
	
	protected static abstract class Teacher {
		protected int	hours;
		protected int	id;
		protected static final double VALUE_PER_HOUR = 30;
		protected char	sex;

		public Teacher(int id, int hours, char sex) {
			this.id = id;
			this.hours = hours;
			this.sex = sex;
		}
		
		public int getId() {
			return this.id;
		}
		
		public int getHours() {
			return this.hours;
		}
		
		public char getSex() {
			return sex;
		}
		
		public double getTotalSalary() {
			return hours * VALUE_PER_HOUR;
		}
		
		public abstract double getDiscountedSalary();
		
		@Override public String toString() {
			return String.format("{CODIGO=%d,SALARIO BRUTO=%.2f,SALARIO LIQUIDO=%.2f}", getId(), getTotalSalary(), getDiscountedSalary());
		}
	}
	
	protected static class Male extends Teacher {
		public Male(int id, int hours, char sex) {
			super(id, hours, sex);
		}

		@Override public double getDiscountedSalary() {
			return getTotalSalary() * .9;
		}
	}
	
	protected static class Female extends Teacher {
		public Female(int id, int hours, char sex) {
			super(id, hours, sex);
		}

		@Override public double getDiscountedSalary() {
			return getTotalSalary() * .95;
		}
	}
	
	@SuppressWarnings("serial") public static void main(String[] args) {
		Exercise exercise = new Exercise8();
		exercise.setInputMap(new HashMap<String, String>() {{
				put("input","{123,M,150};{3123,M,156};{1423,F,155};{1423,M,155};{1423,F,155};{1423,F,155};{1235,M,170};{1623,F,160};{1623,F,160};{1623,F,160};{1623,F,160};{99999}");
			}
		});
		exercise.solve();
		System.out.println(exercise.getResultDescription());
	}
}