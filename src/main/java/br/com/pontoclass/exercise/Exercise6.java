package br.com.pontoclass.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exercise6 extends AbstractExercise {

	private Map<String, String> input;
	private String	result;
	private static final Map<Integer, Double> tabelaJuros;
	static {
		tabelaJuros = new HashMap<>();
		tabelaJuros.put(1, 0.0);
		tabelaJuros.put(3, 0.10);
		tabelaJuros.put(6, 0.15);
		tabelaJuros.put(9, 0.20);
		tabelaJuros.put(12, 0.25);
	}

	public String getStatement() {
		return "Faça um programa que receba o valor de uma dívida e mostre uma tabela com os seguintes dados:\n" +
			   "\t* Valor da dívida, valor do juros, quantidade de parcelas e valor da parcela.\n" +
			   "\t\tOs juros e a quantidade de parcelas seguem a tabela:\n" +
			   "\t\tQuantidade de Parcelas | % de juros sobre o valor inicial da dívida\n" +
			   "\t\t           1           |                     0\n" +
			   "\t\t           3           |                    10\n" +
			   "\t\t           6           |                    15\n" +
			   "\t\t           9           |                    20\n" +
			   "\t\t          12           |                    25\n" +
			   "\t\tExemplo de saída do programa:\n" +
			   "\t\tVALOR DA DÍVIDA | VALOR DOS JÚROS | QUANTIDADE DE PARCELAS | VALOR DA PARCELA\n" +
			   "\t\t  R$ 1.000,00   |        0        |           1            |   R$ 1.000,00\n" +
			   "\t\t  R$ 1.100,00   |       100       |           3            |    R$ 366,67\n" +
			   "\t\t  R$ 1.150,00   |       150       |           6            |    R$ 191,67\n";
	}

	public List<String> getInputNames() {
		return Arrays.asList("Valor da Dívida");
	}

	public void setInputMap(Map<String, String> input) {
		this.input = input;
	}

	public String getResultDescription() {
		return this.result;
	}

	public void solve() {
		double valor = Double.valueOf(input.values().stream().findFirst().get());
		Set<Integer> keys = tabelaJuros.keySet();
		StringBuffer buff = new StringBuffer();
		for(Integer key: keys) {
			buff.append(String.format("{\"VALOR DA DÍVIDA\": %.2f;\"VALOR DOS JÚROS\": %.2f,\"QUANTIDADE DE PARCELAS\": %d, \"VALOR DA PARCELA\": %.2f}\n",
					valor + (valor*tabelaJuros.get(key)), valor*tabelaJuros.get(key), key, (valor + (valor*tabelaJuros.get(key)))/(key*1f)));
		}
		this.result = buff.toString();
	}
	
	@SuppressWarnings("serial") public static void main(String[] args) {
		Exercise exercise = new Exercise6();
		exercise.setInputMap(new HashMap<String, String>() {{
				put("XX", "1000");
			}
		});
		exercise.solve();
		System.out.println(exercise.getResultDescription());
	}
}