package br.com.rfasioli.ContentGenerator.dto;

import java.util.Arrays;

/**
 * @author Rodrigo Fasioli
 */
public class RuleDto {
	private String description;
	private QueryDto[] queries;  

	public RuleDto() {
		super();		
	}
	
	public RuleDto(
			String description, 
			QueryDto[] queries) {
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
	
	public QueryDto[] getQueries() {
		return queries;
	}
	
	public void setQueries(QueryDto[] queries) {
		this.queries = queries;
	}

	@Override
	public String toString() {
		return "Rule [description=" + description + ", queries=" + Arrays.toString(queries) + "]";
	}

}
