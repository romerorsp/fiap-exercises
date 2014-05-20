package br.com.pontoclass.exercise;

import java.util.List;
import java.util.Map;

public interface Exercise {
	public String getStatement();
	
	public List<String> getInputNames();
	
	public void setInputMap(Map<String, String> input);
	
	public String getResultDescription();
	
	public void solve();
}
