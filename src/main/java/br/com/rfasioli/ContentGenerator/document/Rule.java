package br.com.rfasioli.ContentGenerator.document;

import java.util.Arrays;

/**
 * @author rfasioli
 *
 */
public class Rule {
	private String description;
	private Query[] queries;  

	public Rule() {
		super();		
	}
	
	public Rule(
			String description, 
			Query[] queries) {
		super();
		this.description = description;
		this.queries = queries;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Query[] getQueries() {
		return queries;
	}
	
	public void setQueries(Query[] queries) {
		this.queries = queries;
	}

	@Override
	public String toString() {
		return "Rule [description=" + description + ", queries=" + Arrays.toString(queries) + "]";
	}

}
