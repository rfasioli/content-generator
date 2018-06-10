package br.com.rfasioli.ContentGenerator.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Fasioli
 */
public class RuleDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	private List<QueryDto> queries = new ArrayList<>();  

	public RuleDto() {
		super();		
	}
	
	public RuleDto(String description) {
		super();
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public List<QueryDto> getQueries() {
		return queries;
	}

	public void setQueries(List<QueryDto> queries) {
		this.queries = queries;
	}
	
}
